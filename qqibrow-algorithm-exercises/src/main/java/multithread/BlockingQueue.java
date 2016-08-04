package multithread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lniu on 5/11/16.
 */
public class BlockingQueue<T> {
    private Queue<T> q;
    private int capacity;

    public BlockingQueue(int c) {
        q = new LinkedList<>();
        capacity = c;
    }

    public synchronized T get() {
        while(q.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = q.poll();
        notifyAll();
        return t;
    }

    public synchronized void put(T t) {
        while (q.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        q.offer(t);
        notifyAll();
        return;
    }

    public static void main(String[] args) throws InterruptedException {

        // test string split funciton.
        String[] strings = "n,123,n,".split(",");
        System.out.println(strings.length);
        /*
        BlockingQueue2<Integer> q = new BlockingQueue2<>(10);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; ++i) {
                    q.put(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while(true) {
                    int s = 0;
                    try {
                        s = q.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s);
                    count++;
                    System.out.println("count is " + count);
                }
            }
        });
        producer.start();
        Thread.sleep(500);
        consumer.start();

        producer.join();
        consumer.join();
        */
    }

}

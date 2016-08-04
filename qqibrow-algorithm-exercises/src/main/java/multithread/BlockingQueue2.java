package multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lniu on 5/11/16.
 */
public class BlockingQueue2<T> {
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    private final T[] items;
    private int takeIndex;
    private int putIndex;
    int count;

    private int increment(int x) {
        return x + 1 == items.length ? 0 : x + 1;
    }

    private void insert(T t) {
        items[putIndex] = t;
        putIndex = increment(putIndex);
        ++count;
        notEmpty.signal();
    }

    private T extract() {
        T t = items[takeIndex];
        items[takeIndex] = null;
        takeIndex = increment(takeIndex);
        --count;
        notFull.signal();
        return t;
    }

    public BlockingQueue2(int capacity) {
        this.lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
        count = 0;
        items = (T[]) new Object[capacity];
    }

    public void put(T t) {
        lock.lock();
        try {
            while(count == items.length) {
                notFull.await();
            }
            insert(t);
        } catch (InterruptedException e) {
            notFull.signal();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T get() throws InterruptedException{
        lock.lock();
        try {
            try {
                while(count == 0) {
                    notEmpty.await();
                }
            }catch (InterruptedException e) {
                notEmpty.signal();
                throw e;
            }
            return extract();
        } finally {
            lock.unlock();
        }
    }

    public boolean tryPut(T t) {
        return false;
    }
}

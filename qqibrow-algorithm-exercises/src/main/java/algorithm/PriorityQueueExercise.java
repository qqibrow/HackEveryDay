package algorithm;

import java.util.PriorityQueue;

/**
 * Created by lniu on 4/12/16.
 */
public class PriorityQueueExercise {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(5);
        queue.add(4);
        System.out.println(queue.peek());
    }

}

package datastructure;

import java.util.PriorityQueue;

/**
 * Created by lniu on 5/12/16.
 */
public class PriorityQueueExercise {
    public static class Point {
        int x;
        int y;
        public Point(int x_, int y_) {
            x = x_;
            y = y_;
        }
        int distance(Point other) {
            int xdiff = other.x - x;
            int ydiff = other.y - y;
            return xdiff*xdiff + ydiff*ydiff;
        }
    }
    public void shortedDistance() {
        Point givenPoint = new Point(5, 10);
        PriorityQueue<Point> q = new PriorityQueue<>(20, (p1, p2) -> p1.distance(givenPoint) - p2.distance(givenPoint));


    }

}

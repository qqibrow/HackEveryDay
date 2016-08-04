package algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by lniu on 5/3/16.
 */

public class MinMeetingRooms {
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        q.offer(intervals[0].end);
        int maxRoom = 1;
        for(int i = 1; i < intervals.length; ++i) {
            Interval curr = intervals[i];
            if(curr.start < q.peek()) {
                maxRoom++;
                q.offer(curr.end);
            } else {
                q.poll();
                q.offer(curr.end);
            }
        }
        return maxRoom;

    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(2, 5), new Interval(3, 7), new Interval(4, 8), new Interval(9, 15), new Interval(14, 17), new Interval(12, 20)};
        System.out.println(new MinMeetingRooms().minMeetingRooms(intervals));
    }
}

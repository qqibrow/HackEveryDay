package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lniu on 4/4/16.
 */
public class PerfectSquare {
        public int numSquares(int n) {
            int sqroot = (int)Math.sqrt(n);
            List<Integer> numbers = new ArrayList<>();
            for(int i = 1; i <= sqroot; ++i) {
                numbers.add(i*i);
            }
            int[] opts = new int[n+1];
            Arrays.fill(opts, Integer.MAX_VALUE);
            opts[0] = 0;
            for(int number : numbers) {
                opts[number] = 1;
            }
            for(int i = 1; i <=n; ++i) {
                for(int number: numbers) {
                    if(i > number) {
                        opts[i] = Math.min(opts[i-number], opts[i]) + 1;
                    }
                }
            }
            return opts[n];
        }
    public static void main(String[] args) {
        new PerfectSquare().numSquares(4);
        int[][] prerequisites = new int[][]{{0,1}, {1,0}};
        for(int[] x : prerequisites) {
            System.out.println(x[1]);
        }
    }
}

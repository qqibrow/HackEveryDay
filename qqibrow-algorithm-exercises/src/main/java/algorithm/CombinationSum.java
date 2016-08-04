package algorithm;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lniu on 2/6/16.
 */
public class CombinationSum {
    List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return conbinationSumRecursive(target, 0, candidates);
        //helper(new ArrayList<>(), target, candidates, 0);
        //return results;
    }
    public void helper(List<Integer> curr, int target, int[] candidates, int start) {
        // Preconditions.checkArgument(i >= 0 && i < candidates.length);
        // Preconditions.checkArgument(target >= 0);
        if(target == 0) {
           results.add(curr);
        } else {
            for(int index = start; index < candidates.length && candidates[index] <= target; ++index) {
                // skip the same number.
                if(index > start && candidates[index] == candidates[index - 1])
                    continue;

                List<Integer> res = new ArrayList<>(curr);
                res.add(candidates[index]);
                helper(res, target - candidates[index], candidates, index + 1);
            }
        }

    }

    /*
        bottom-up approach to solve combination sum, we have to pass additional flag to indicates that we did success find the match.
     */
    public List<List<Integer>> conbinationSumRecursive(int target, int indexStart, int[] candidiates) {
        // candidiates list is empty
        if(indexStart == candidiates.length || target < candidiates[indexStart]) {
            return new ArrayList<>();
        } else {
            int curr = candidiates[indexStart];
            if(target == curr) {
                return Arrays.asList(Arrays.asList(curr));
            } else {
                List<List<Integer>> combinationsUseCurrNumber =
                    conbinationSumRecursive(target - candidiates[indexStart], indexStart, candidiates);
                for(List<Integer> r : combinationsUseCurrNumber) {
                    r.add(curr);
                }
                List<List<Integer>> combinationNotUseCurr = conbinationSumRecursive(target, indexStart + 1, candidiates);
                return Stream.concat(combinationNotUseCurr.stream(), combinationNotUseCurr.stream()).collect(Collectors.toList());
            }
        }

    }

    private class Point {
        int target;
        int index;

        public Point(int target_, int index_){
            target = target_;
            index = index_;
        }
    }

    public List<List<Integer>> combinationIterate(int target, int[] candidates) {
        Stack<Point> s = new Stack<>();
        s.push(new Point(target, 0));
        while(!s.empty()) {
            Point p = s.peek();

        }


    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 7}, 7));
    }

}

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lniu on 2/6/16.
 */
public class CombinationSum {
    List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(new ArrayList<>(), target, candidates, 0);
        return results;
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
    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{10,1,2,7,6,1,5}, 8));
    }

}

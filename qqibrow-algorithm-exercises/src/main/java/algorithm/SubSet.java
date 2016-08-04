package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lniu on 5/22/16.
 */
public class SubSet {
    public List<List<Integer>> subSet(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return results;
        }
        Arrays.sort(nums);
        helper(0, nums, new ArrayList<>(), results);
        return results;
    }

    private void helper(int i, int[] nums, List<Integer> curr, List<List<Integer>> results) {
        for(int j = i; j < nums.length; ++j) {
            if(j != i && nums[j] == nums[j-1])
                continue;
            curr.add(nums[j]);
            results.add(new ArrayList<>(curr));
            helper(j + 1, nums, curr, results);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        new SubSet().subSet(new int[]{1, 2, 2});
    }

}

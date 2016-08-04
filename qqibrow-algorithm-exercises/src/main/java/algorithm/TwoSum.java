package algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by lniu on 12/23/15.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // index is required. index will change after sorting. so I cannot use sorting. Better understand the question!
        /*
        Arrays.sort(nums);
        int[] res = new int[2];
        int front = 0, back = nums.length - 1;
        while(front < back) {
            int temp = nums[front] + nums[back];
            if(temp == target) {
                res[0] = front + 1;
                res[1] = back + 1;
                front++;
                back--;
            } else if(temp < target) {
                front++;
            } else {
                back--;
            }
        }
        */
        int[] res = new int[2];

        return res;
    }
}

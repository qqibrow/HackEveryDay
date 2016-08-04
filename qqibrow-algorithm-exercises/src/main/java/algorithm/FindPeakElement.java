package algorithm;

import com.google.common.base.Preconditions;

/**
 * Created by lniu on 3/27/16.
 */

/*
    Wired output from judge.
    Input:
    [2,1,2]
    Output:
    1
    Expected:
    Special judge: No expected output available.
 */
public class FindPeakElement {
    private int getDirection(int[] nums, int pos) {
        Preconditions.checkArgument(pos >= 0 && pos < nums.length);
        int middle = nums[pos];
        if(pos - 1 >= 0 && pos + 1 < nums.length) {
            int left = nums[pos - 1];
            int right = nums[pos + 1];
            if(left < middle && middle < right)
                return 1;
            else if(left > middle && middle > right)
                return -1;
            else
                return 0;
        } else if(pos - 1 >= 0) {
            int left = nums[pos - 1];
            if(middle > left) {
                return 0;
            } else {
                return -1;
            }
        } else if(pos + 1 < nums.length) {
            int right = nums[pos + 1];
            if(middle > right) {
                return 0;
            } else {
                return 1;
            }

        } else {
            return 0;
        }
    }

    private int findPeakElementRecursive(int[] nums, int start, int end) {
        if(start > end)
            return -1;

        int mid = start + (end - start) / 2;
        int midGradient = getDirection(nums, mid);
        if(midGradient == 0) {
            return mid;
        } else {
            int startGradient = getDirection(nums, start);
            int endGradient = getDirection(nums, end);
            if(startGradient == 0) {
                return start;
            }
            if(endGradient == 0) {
                return end;
            }
            if(startGradient * endGradient < 0) {
                if(startGradient * midGradient > 0) {
                    return findPeakElementRecursive(nums, mid + 1, end);
                } else {
                    return findPeakElementRecursive(nums, start, mid - 1);
                }
            } else {
                // either way is OK.
                return findPeakElementRecursive(nums, mid + 1, end);
            }
        }
    }
    public int findPeakElement(int[] nums) {
        return findPeakElementRecursive(nums, 0, nums.length-1);
    }

    public static void main(String[] args) {
        System.out.println(new FindPeakElement().findPeakElement(new int[]{3, 2, 1}));
    }
}

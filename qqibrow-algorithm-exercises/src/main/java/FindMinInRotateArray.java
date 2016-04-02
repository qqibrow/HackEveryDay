/**
 * Created by lniu on 4/2/16.
 */
public class FindMinInRotateArray {
        public int findMin(int[] nums) {
            int len = nums.length;
            int left = 0;
            int right = len - 1;
            if(len == 1)
                return nums[0];

            while(left < right) {
                int mid = left + (right - left) / 2;
                if(nums[left] < nums[right]) {
                    return nums[left];
                } else {
                    if(nums[mid] > nums[left]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
            }
            return nums[left];
        }
    public static void main(String[] args) {
        System.out.println(new FindMinInRotateArray().findMin(new int[]{2, 1}));
    }

}

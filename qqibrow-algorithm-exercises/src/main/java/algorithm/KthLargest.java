package algorithm;

/**
 * Created by lniu on 4/9/16.
 */
public class KthLargest {
        public int findKthLargest(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return -1;
            }
            return findKthLargest(nums, k, 0, nums.length - 1);

        }

        private int findKthLargest(int[] nums, int k, int lo, int hi) {
            if(lo == hi)
                return nums[lo];

            int nthLargestIndex = partition(nums, lo, hi);
            if(nthLargestIndex  == k - 1) {
                return nums[nthLargestIndex];
            } else if(nthLargestIndex  > k - 1) {
                return findKthLargest(nums, k, lo, nthLargestIndex - 1);
            } else {
                return findKthLargest(nums, k , nthLargestIndex + 1, hi);
            }
        }

        private int partition(int[] nums, int lo, int hi) {
            int pivot = nums[hi];
            int i = lo;
            for(int j = i; j < hi; ++j) {
                if(nums[j] <= pivot) {
                    // swap j and i
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                }
            }
            nums[hi] = nums[i];
            nums[i] = pivot;
            return i;
        }

    public static void main(String[] args) {
        System.out.println(new KthLargest().findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 4));
    }

}

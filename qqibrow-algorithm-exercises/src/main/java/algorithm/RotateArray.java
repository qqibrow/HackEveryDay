package algorithm;

/**
 * Created by lniu on 4/9/16.
 */
public class RotateArray {
        private int prevPos(int index, int k, int length) {
            return (index + length - k) % length;
        }

        public void rotate(int[] nums, int k) {
            int length = nums.length;
            k = k % length;

            int index = 0;
            do {
                int prevIndex = prevPos(index, k, length);
                int temp = nums[index];
                nums[index] = nums[prevIndex];
                nums[prevIndex] = temp;
                index = prevIndex;
            } while(index != 0);
        }

    public static void main(String[] args) {
        //new algorithm.RotateArray().rotate(new int[]{1, 2}, 1);
        int[] arr = null;
        System.out.println(arr.length);
    }
}

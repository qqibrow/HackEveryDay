package algorithm;

/**
 * Created by lniu on 4/17/16.
 */
public class ClosestBinarySearchTreeValue {
    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
    public int closestValue(TreeNode root, double target) {
        double diff = Double.MAX_VALUE;
        TreeNode curr = root;
        int result = 0;
        while(curr != null) {
            int val = curr.val;
            double currDiff = Math.abs(target - val);
            if(diff > currDiff) {
                result = val;
                diff = currDiff;
            }
            if(target < val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new ClosestBinarySearchTreeValue().closestValue(new TreeNode(Integer.MAX_VALUE), 0.0);
    }

}

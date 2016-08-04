package algorithm;

/**
 * Created by lniu on 4/3/16.
 */
public class KthSmallestElementInBST {
        static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int v) {
                val = v;
            }
        }
        public TreeNode helper(TreeNode root, int k, int[] sum) {
            if(root == null || k <= 0) {
                sum[0] = 0;
                return null;
            } else if(root.left == null && root.right == null) {
                // is leaf
                if(k == 1) {
                    return root;
                } else {
                    sum[0] = 1;
                    return null;
                }
            } else {
                int[] leftSum = new int[1];
                TreeNode leftResult = helper(root.left, k, leftSum);
                if(leftResult != null) {
                    return leftResult;
                }
                if(k == leftSum[0] + 1) {
                    return root;
                }
                int[] rightSum = new int[1];
                TreeNode rightResult = helper(root.right, k - 1 - leftSum[0], rightSum);
                if(rightResult != null) {
                    return rightResult;
                }
                sum[0] = leftSum[0] + 1 + rightSum[0];
                return null;
            }
        }
        public int kthSmallest(TreeNode root, int k) {
            TreeNode t = helper(root, k, new int[1]);
            return t.val;
        }
    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        new KthSmallestElementInBST().kthSmallest(t, 2);


    }
}

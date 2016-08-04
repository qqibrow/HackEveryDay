package algorithm;

import java.util.Stack;

/**
 * Created by lniu on 5/7/16.
 */
public class RecoverBinarySearchTree {
    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
             public TreeNode(int x, TreeNode l, TreeNode r) {
                 val = x;
                 left = l;
                 right = r;
             }
         }
    public static class Solution {
        public void recoverTree(TreeNode root) {
            TreeNode prev = null;
            TreeNode curr = root;

            TreeNode firstNode = null;
            TreeNode secondNode = null;
            Stack<TreeNode> parents = new Stack<>();
            while(curr != null || !parents.isEmpty()) {
                if(curr == null) {
                    TreeNode top = parents.pop();
                    if(prev != null && firstNode == null && prev.val > top.val) {
                        firstNode = prev;
                    }
                    else if(prev != null && firstNode != null && secondNode == null && prev.val > top.val) {
                        secondNode = top;
                    }
                    prev = top;
                    curr = top.right;
                } else {
                    parents.push(curr);
                    curr = curr.left;
                }
            }
            int temp = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = temp;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.recoverTree(new TreeNode(2, new TreeNode(3), new TreeNode(1)));
    }

}

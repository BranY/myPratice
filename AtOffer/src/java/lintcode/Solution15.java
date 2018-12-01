package lintcode;

public class Solution15 {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (root == p || root == q) {
            //boolean rootright = true;
            return root;
        }
        if (left != null && right != null) {
            //boolean notempty = true;
            return root;
        }

        return right == null ? left : right;
    }

    public TreeNode lowestCommonAncestor_BST(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null || root == A || root == B) {
            return root;
        }
        TreeNode left = lowestCommonAncestor_BST(root.left, A, B);
        TreeNode right = lowestCommonAncestor_BST(root.right, A, B);

        return left == null ? right : (right == null ? left : root);
    }

}

package pkg.ywj.tree;

import java.util.Stack;

/**
 * Description link: https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * Tag: dfs, tree
 * 2020/7/26_18:23  BranY
 */

public class Problem129 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(0);
        root.left = left1;
        root.right = right1;
        TreeNode left2 = new TreeNode(5);
        TreeNode right2 = new TreeNode(1);
        left1.left = left2;
        left1.right = right2;

        Problem129 su = new Problem129();
        int sum =  su.sumNumbersWithStack(root);
        System.out.println(sum);
    }


    public int sumNumbers(TreeNode root) {
        int sum = dfs(root, 0);
        return sum;
    }
    private int dfs(TreeNode root, int i) {
        if (root == null) return 0;
        int tmp = i * 10 + root.val;
        if (root.left == null && root.right == null)
            return tmp;
        return dfs(root.left, tmp) + dfs(root.right, tmp);
    }

    /**
     * 先序非递归的代码我们知道是用 stack 来保存遍历过的元素。
     * 而因为本题要记录到叶节点的数字，所以需要一个额外的 stack 来记录数字。每次出 stack 之后，如果是叶子节点，那么加和；如果不是，那么就看左右子树，入 stack。
     */
    public int sumNumbersWithStack(TreeNode root) {
        int sum = 0;
        if (root == null) return sum;
        Stack<TreeNode> nodeST = new Stack<>();
        Stack<Integer> numST = new Stack<>();
        nodeST.add(root);
        numST.add(0);


        while (!nodeST.isEmpty()) {
            TreeNode cur = nodeST.pop();
            Integer tmp = numST.pop() * 10 + cur.val;
            if (cur.left == null && cur.right == null) sum += tmp;

            if (cur.left != null) {
                nodeST.add(cur.left);
                numST.add(tmp);
            }
            if (cur.right != null) {
                nodeST.add(cur.right);
                numST.add(tmp);
            }
        }
        return sum;
    }
}

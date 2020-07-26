package pkg.ywj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Description link:
 * Tag:
 * 2020/7/26_19:13  BranY
 */
public class Problem5474 {
    /**
     * root->val 没用，父节点和子节点的距离是 1
     * 对树后序遍历 ，需要返回这个节点到其下方所有叶子节点的距离
     * 这样就可以将这个节点的左子树所有叶子节点和右子树所有叶子节点都凑个对
     * 然后将所有叶子节点不超过距离的弄到一起返回
     *
     */

    int ans = 0;
    int distance = 0;
    public int countPairs(TreeNode root, int distance) {
        this.distance = distance;
        dfs(root, new ArrayList<>(), 0);
        return ans;
    }
    private void dfs(TreeNode root, List<Integer> records, int curDep) {
        if (root == null) return ;
        // 遍历到叶子节点，记录下该叶子节点的深度
        if (root.left == null && root.right == null) records.add(curDep);

        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();

        dfs(root.left, left, curDep + 1);
        dfs(root.right, right, curDep + 1);

        records.addAll(left);
        records.addAll(right);

        // 遍历当前左右子节点的深度，结合当前节点的深度判断是否符合条件
        for(Integer a: left) {
            for(Integer b: right) {
                if (a + b - curDep * 2 <= distance) {
                    ans += 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        root.left = left1;
        root.right = right1;

        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        left1.left = left2;
        left1.right = right2;

        TreeNode left3 = new TreeNode(6);
        TreeNode right3 = new TreeNode(7);
        right1.left = left3;
        right1.right = right3;

        Problem5474 su = new Problem5474();
        System.out.println(su.countPairs(root, 3));
    }
}

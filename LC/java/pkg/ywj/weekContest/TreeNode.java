package pkg.ywj.weekContest;

/**
 * Description link:
 * Tag:
 * 2020/7/26_18:43  BranY
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

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
    }
}

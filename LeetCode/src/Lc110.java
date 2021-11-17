/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc110
 * @Description: [110. Balanced Binary Tree 平衡树]
 * @Author: [clh]
 * @Date: 2021/11/17 21:48
 **/
public class Lc110 {
    //我自己想的
    public boolean isBalanced1(TreeNode root) {
        if(root == null)
            return true;
        //这个顺序就可100 60.50
        return (Math.abs(maxDepth1(root.left) - maxDepth1(root.right)) < 2) && isBalanced(root.left)
                && isBalanced1(root.right);
        //下面这个顺序就15%左右
        /*return  isBalanced(root.left) && isBalanced(root.right) &&
                (Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2);*/
    }

    private int maxDepth1(TreeNode node) {
        if(node == null)
            return 0;
        return 1 + Math.max(maxDepth1(node.left), maxDepth1(node.right));
    }

    //2 github 优化
    private boolean result = true;

    public boolean isBalanced2(TreeNode root) {
        maxDepth2(root);
        return result;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth2(root.left);
        int r = maxDepth2(root.right);
        if (Math.abs(l - r) > 1) result = false;
        return 1 + Math.max(l, r);
    }

    //3 leetcode-cn 对2的优化
    public boolean isBalanced3(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        //这里好绝！
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        //看评论区继续优化 如果左子树返回-1 就不用递归右子树了
            /*if ((leftHeight = balanced(node.left)) == -1
                    || (rightHeight = balanced(node.right)) == -1
                    || Math.abs(leftHeight - rightHeight) > 1)
                return -1;*/
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }



}

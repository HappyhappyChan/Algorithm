/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc543
 * @Description: [543. Diameter of Binary Tree (Easy) 两节点的最长路径]
 * @Author: [clh]
 * @Date: 2021/11/18 9:41
 **/
public class Lc543 {

    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        //为什么呢 l r是高度 而不是边的长度啊
        //哦哦 因为是左子树的高度 只有+1才到root的高度 所以l本身就是已经减了1了
        if(l + r >= max)
            max = l + r;
        return 1 + Math.max(l, r);
    }

}

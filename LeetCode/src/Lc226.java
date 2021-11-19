/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc226
 * @Description: [226. Invert Binary Tree (Easy) 翻转树]
 * @Author: [clh]
 * @Date: 2021/11/18 10:03
 **/
public class Lc226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return root;
        TreeNode oldleft = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(oldleft);
        return root;
    }
}

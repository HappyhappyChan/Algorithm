import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc235
 * @Description: [235. Lowest Common Ancestor of a Binary Search Tree (Easy) 二叉查找树的最近公共祖先]
 * @Author: [clh]
 * @Date: 2021/11/20 16:47
 **/
public class Lc235 {
    //1 我自己想的 递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;
        if(root.val == p.val || root.val == q.val)
            return root;
        if((p.val-root.val)*(q.val - root.val) < 0)
            return root;
        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        return lowestCommonAncestor(root.right, p, q);
    }

    //2 github更是简单粗暴
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor2(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor2(root.right, p, q);
        return root;
    }

    //3 leetcode-cn
    //1-time iteration
    //O(n) O(1)
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    //4 leetcode-cn
    //2-time iteration
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                //妙啊！
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }
}

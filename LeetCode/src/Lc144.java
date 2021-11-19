import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc144
 * @Description: [144. Binary Tree Preorder Traversal (Medium) 非递归实现二叉树的前序遍历]
 * @Author: [clh]
 * @Date: 2021/11/19 20:47
 **/
public class Lc144 {
    //1 recursion
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        preorder(root, ret);
        return ret;
    }

    private void preorder(TreeNode root, List<Integer> ret) {
        if(root == null)
            return;
        ret.add(root.val);
        preorder(root.left, ret);
        preorder(root.right, ret);
    }

    //2 without recursion
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null)
            return ret;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode cur = s.pop();
            ret.add(cur.val);
            if(cur.right != null)
                s.push(cur.right);
            if(cur.left != null)
                s.push(cur.left);
        }
        return ret;
    }

    //3 leetcode-cn mirrors遍历
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        TreeNode p1 = root, p2 = null;
        while(p1 != null){
            p2 = p1.left;
            if(p2 != null){
                while(p2.right != null && p2.right != p1){
                    p2 = p2.right;
                }
                if(p2.right == null){
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                }else{
                    p2.right = null;
                }
            }else{
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc94
 * @Description: [94. Binary Tree Inorder Traversal (Medium) 非递归实现二叉树的中序遍历]
 * @Author: [clh]
 * @Date: 2021/11/19 21:51
 **/
public class Lc94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null)
            return ret;
        inorder(root, ret);
        return ret;
    }

    private void inorder(TreeNode root, List<Integer> ret) {
        if(root == null)
            return;
        inorder(root.left, ret);
        ret.add(root.val);
        inorder(root.right, ret);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null)
            return ret;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            ret.add(cur.val);
            cur = cur.right;
        }
        return ret;
    }

    //3 leetcode
    //mirror
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }


}

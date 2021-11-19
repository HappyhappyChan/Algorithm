import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc145
 * @Description: [145. Binary Tree Postorder Traversal 非递归实现二叉树的中序遍历]
 * @Author: [clh]
 * @Date: 2021/11/19 21:34
 **/
public class Lc145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null)
            return ret;
        postorder(root, ret);
        return ret;
    }

    private void postorder(TreeNode root, List<Integer> ret) {
        if(root == null)
            return;
        postorder(root.left, ret);
        postorder(root.right, ret);
        ret.add(root.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null)
            return ret;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode cur = s.pop();
            if(cur == null)
                continue;
            ret.add(cur.val);
            s.push(cur.left);
            s.push(cur.right);
        }
        Collections.reverse(ret);
        return ret;
    }

}

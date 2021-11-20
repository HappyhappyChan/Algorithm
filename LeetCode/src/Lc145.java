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

    //3 leetcode-cn
    List<Integer> list;
    public void postMorris(TreeNode root){
        list = new ArrayList<>();
        if(root == null){
            return;
        }
        TreeNode p1 = root;
        TreeNode p2 = null;
        while(p1 != null){
            p2 = p1.left;
            if(p2 != null){
                while(p2.right != null && p2.right != p1){
                    p2 = p2.right;
                }
                if(p2.right == null){
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                }else{
                    p2.right = null;
                    postMorrisHelper(p1.left);
                }
            }
            p1 = p1.right;
        }
        postMorrisHelper(root);
    }
    //Morris 的核心是，将某节点的右子节点，看成是一条链表，进行反向遍历
    private void postMorrisHelper(TreeNode root) {
        //翻转链表
        TreeNode reverseNode = reverseList(root);
        //遍历链表
        TreeNode cur = reverseNode;
        while(cur != null){
            list.add(cur.val);
            cur = cur.right;
        }
        //翻转回来
        reverseList(reverseNode);
    }

    private TreeNode reverseList(TreeNode head) {
        TreeNode cur = head;
        TreeNode pre = null;
        while(cur != null){
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}

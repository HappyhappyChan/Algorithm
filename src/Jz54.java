import java.util.ArrayList;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz54
 * @Description: [二叉搜索树的第k个结点]
 * @Author: [clh]
 * @Date: 2021/10/22 9:35
 **/
public class Jz54 {
    //solution 1 from newcoder 中序遍历+栈
    TreeNode KthNode(TreeNode pRoot, int k) {
        if(pRoot == null || k <= 0)
            return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = pRoot;
        while(!s.isEmpty() || cur != null){
            if(cur != null){
                s.push(cur);
                cur = cur.left;
            }else{
                cur = s.pop();
                if(--k == 0){
                    return cur;
                }
                cur = cur.right;
            }
        }
        return null;
    }

    //solution 2 from newcoder 中序遍历+递归
    ArrayList<TreeNode> list = new ArrayList<TreeNode>();
    TreeNode solution2(TreeNode pRoot, int k){
        addNode(pRoot);
        if(k >= 1 && list.size() >= k){
            return list.get(k-1);
        }
        return null;
    }

    private void addNode(TreeNode cur) {
        if(cur != null){
            addNode(cur.left);
            list.add(cur);
            addNode(cur.right);
        }
    }

    //solution 3 from github
    private TreeNode ret;
    private int cnt = 0;
    TreeNode solution3(TreeNode root, int k){
        inOrder(root, k);
        return ret;
    }

    private void inOrder(TreeNode root, int k) {
        if(root == null || cnt >= k)
            return;
        inOrder(root.left, k);
        cnt++;
        if(cnt == k)
            ret = root;
        inOrder(root.right, k);
    }


}

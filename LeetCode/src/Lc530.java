import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc530
 * @Description: [530. Minimum Absolute Difference in BST (Easy) 在二叉查找树中查找两个节点之差的最小绝对值]
 * @Author: [clh]
 * @Date: 2021/11/22 15:05
 **/
public class Lc530 {
    //1 我自己写的 用栈
    public int getMinimumDifference(TreeNode root) {
        if(root == null)
            return 0;
        int ret = Integer.MAX_VALUE;
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<TreeNode>();
        //不能为root.val 否则可能返回root.val - root.val = 0
        //int pop = root.val;
        int pop = Integer.MAX_VALUE;
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            if(pop != Integer.MAX_VALUE)
                ret = Math.min(Math.abs(cur.val - pop), ret);
            if(ret == 0)
                break;
            pop = cur.val;
            cur = cur.right;
        }
        return ret;
    }

    //2 github 递归
    int min = Integer.MAX_VALUE;
    int pre = Integer.MAX_VALUE;
    public int getMinimumDifference2(TreeNode root){
        if(root == null)
            return min;
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if(root == null)
            return;
        inorder(root.left);
        min = Math.min(Math.abs(root.val - pre), min);
        pre = root.val;
        inorder(root.right);
    }

}

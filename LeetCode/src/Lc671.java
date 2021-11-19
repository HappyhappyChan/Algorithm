import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc671
 * @Description: [671. Second Minimum Node In a Binary Tree 找出二叉树中第二小的节点]
 * @Author: [clh]
 * @Date: 2021/11/19 17:21
 **/
public class Lc671 {

    //1 我自己写的
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return -1;
        //
        long min1 = Long.MAX_VALUE;
        long min2 = Long.MAX_VALUE;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            TreeNode cur = que.poll();
            if(cur.val < min1){
                min2 = min1;
                min1 = cur.val;
            }else if(cur.val < min2 && cur.val > min1){
                min2 = cur.val;
            }
            if(cur.left != null)
                que.offer(cur.left);
            if(cur.right != null)
                que.offer(cur.right);
        }
        return min2 == Long.MAX_VALUE ? -1 : (int)min2;

    }

    //2 leetcode
    //solution
    int min1;
    long ans = Long.MAX_VALUE;

    public void dfs(TreeNode root) {
        if (root != null) {
            if (min1 < root.val && root.val < ans) {
                ans = root.val;
            } else if (min1 == root.val) {
                dfs(root.left);
                dfs(root.right);
            }
        }
    }
    public int findSecondMinimumValue2(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }
}

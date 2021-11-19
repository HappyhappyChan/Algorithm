import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc101
 * @Description: [101. Symmetric Tree (Easy) 树的对称]
 * @Author: [clh]
 * @Date: 2021/11/19 9:46
 **/
public class Lc101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        if(root.left == null && root.right == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;
        if(left.val != right.val)
            return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    //2 leetcode-cn 迭代
    public boolean isSymmetric2(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode r1, TreeNode r2) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(r1);
        q.offer(r2);
        while(!q.isEmpty()){
            r1 = q.poll();
            r2 = q.poll();
            if(r1 == null && r2 == null)
                continue;
            if((r1 == null || r2 == null) || (r1.val != r2.val))
                return false;
            q.offer(r1.left);
            q.offer(r2.right);
            q.offer(r1.right);
            q.offer(r2.left);
        }
        return true;
    }


}

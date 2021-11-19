import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc112
 * @Description: [112. Path Sum 判断路径和是否等于一个数]
 * @Author: [clh]
 * @Date: 2021/11/18 15:43
 **/
public class Lc112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null && root.val == targetSum)
            return true;
        boolean f1 = hasPathSum(root.left, targetSum - root.val);
        boolean f2 = hasPathSum(root.right, targetSum - root.val);
        return  f1 || f2;
    }

    //2 leetcode-cn bfs
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null && root.val == sum)
            return true;
        Queue<TreeNode> que = new LinkedList<>();
        Queue<Integer> add = new LinkedList<>();
        que.add(root);
        add.add(root.val);
        while(!que.isEmpty()){
            int size = que.size();
            while(size-- > 0){
                TreeNode cur = que.poll();
                int pre = add.poll();
                if(cur.left == null && cur.right == null && pre == sum)
                    return true;
                if(cur.left != null){
                    que.offer(cur.left);
                    add.offer(cur.left.val + pre);
                }
                if(cur.right != null){
                    que.offer(cur.right);
                    add.offer(cur.right.val + pre);
                }
            }
        }
        return false;
    }
}

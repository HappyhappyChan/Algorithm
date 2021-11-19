import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc404
 * @Description: [404. Sum of Left Leaves (Easy) 统计左叶子节点的和]
 * @Author: [clh]
 * @Date: 2021/11/19 10:23
 **/
public class Lc404 {
    public int sumOfLeftLeaves(TreeNode root) {
        //一开始把sum当做类变量放在方法外面 结果就是错的 因为递归时候会不断累加
        int sum = 0;
        if(root == null)
            return sum;
        if(root.left != null && root.left.left == null && root.left.right == null)
            sum += root.left.val;
        int a = sumOfLeftLeaves(root.left);
        int b = sumOfLeftLeaves(root.right);
        return sum + a + b;
    }

    //2 github
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        if (isLeaf(root.left)) return root.left.val + sumOfLeftLeaves2(root.right);
        return sumOfLeftLeaves2(root.left) + sumOfLeftLeaves2(root.right);
    }

    private boolean isLeaf(TreeNode node){
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    //3 leetcode-cn bfs
    public int sumOfLeftLeaves3(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> que = new LinkedList<>();
        int sum = 0;
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            while(size-->0){
                TreeNode cur = que.poll();
                if(cur.left != null){
                    if(cur.left.left == null && cur.left.right == null){
                        sum += cur.left.val;
                    }else{
                        que.offer(cur.left);
                    }
                }
                if(cur.right != null){
                    que.offer(cur.right);
                }
            }
        }
        return sum;
    }
}

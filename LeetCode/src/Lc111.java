import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc111
 * @Description: [111. Minimum Depth of Binary Tree (Easy) 最小路径]
 * @Author: [clh]
 * @Date: 2021/11/19 10:02
 **/
public class Lc111 {

    //1 递归
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        if(root.left == null) {
            return 1 + minDepth(root.right);
        }
        if(root.right == null) {
            return 1 + minDepth(root.left);
        }
        /*if(root.left != null && root.right != null) {*/
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        /*}*/
    }

    //2 迭代 bfs 效率明显比递归快很多
    public int minDepth2(TreeNode root) {
        if(root == null)
            return 0;
        int level = 0;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            while(size > 0){
                size--;
                TreeNode cur = que.poll();
                if(cur.left == null && cur.right == null)
                    return level;
                if(cur.left == null)
                    que.offer(cur.right);
                if(cur.right == null)
                    que.offer(cur.left);
                if(cur.left != null && cur.right != null){
                    que.offer(cur.left);
                    que.offer(cur.right);
                }
            }
        }
        return level;
    }

    //3 github recursion代码 简洁很多
    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth3(root.left);
        int right = minDepth3(root.right);
        if (left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;
    }
}

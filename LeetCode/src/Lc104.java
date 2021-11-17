import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc104
 * @Description: [104. Maximum Depth of Binary Tree (Easy) 树的高度]
 * @Author: [clh]
 * @Date: 2021/11/17 21:20
 **/
public class Lc104 {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //2 leetcode-cn bfs
    public int maxDepth2(TreeNode root){
        if(root == null)
            return 0;
        int ans = 0;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            ans++;
            while(size-- > 0){
                TreeNode cur = que.poll();
                if(cur.left != null)
                    que.add(cur.left);
                if(cur.right != null)
                    que.add(cur.right);
            }
        }
        return ans;
    }
}

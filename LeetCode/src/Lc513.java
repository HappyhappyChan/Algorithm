import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc513
 * @Description: [513. Find Bottom Left Tree Value (Easy) 得到左下角的节点]
 * @Author: [clh]
 * @Date: 2021/11/19 20:28
 **/
public class Lc513 {
    public int findBottomLeftValue(TreeNode root) {
        if(root == null)
            return 0;
        int level = 0;
        int resL = 0;
        //[1] --> output = 1;
        int ret = root.val;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        while(!que.isEmpty()){
            level++;
            int size = que.size();
            while(size-- > 0){
                TreeNode cur = que.poll();
                //[0, null, -1]
                if(cur.left == null && cur.right == null){
                    if(level > resL){
                        resL = level;
                        ret = cur.val;
                    }
                }
                if(cur.left != null){
                    que.offer(cur.left);
                    //这个没必要 因为如果是同层的 左边的肯定先被加进去
                    /*if(level > resL){
                        resL = level;
                        ret = cur.left.val;
                    }*/
                }
                if(cur.right != null){
                    que.offer(cur.right);
                }
            }
        }
        return ret;
    }

    //2 leetcode
    //好绝！
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.add(root.right);
            if (root.left != null) queue.add(root.left);
        }
        return root.val;
    }
}

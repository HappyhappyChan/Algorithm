import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz38
 * @Description: [二叉树的深度]
 * @Author: [clh]
 * @Date: 2021/10/13 16:19
 **/

//从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
public class Jz55 {
    //solution 1
    public int TreeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }

    //solution 2 from newcoder 层次遍历
    public int solution2(TreeNode root){
        if(root == null)
            return 0;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        int high = 0;
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            while(size > 0){
                TreeNode cur = que.poll();
                if(cur.left != null) que.add(cur.left);
                if(cur.right != null) que.add(cur.right);
                size--;
            }
            high++;
        }
        return high;
    }

    //solution 3 from newcoder 深度遍历
    //感觉跟solution1没差
    public int solution3(TreeNode root){
        if(root == null)
            return 0;
        int l = solution3(root.left);
        int r = solution3(root.right);
        return Math.max(l, r) + 1;
    }
}

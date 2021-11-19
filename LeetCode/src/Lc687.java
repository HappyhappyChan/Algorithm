import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc687
 * @Description: [687. Longest Univalue Path (Easy) 相同节点值的最大路径长度]
 * @Author: [clh]
 * @Date: 2021/11/19 11:01
 **/
public class Lc687 {
    //我这个不行
    public int longestUnivaluePath(TreeNode root) {
        if(root == null)
            return 0;
        int max = 0;
        Queue<TreeNode> traverse = new LinkedList<>();
        traverse.add(root);
        while(!traverse.isEmpty()) {
            TreeNode node = traverse.poll();
            Queue<TreeNode> que = new LinkedList<>();
            que.add(node);
            int cnt = 0;
            if(node.left != null)
                traverse.offer(node.left);
            if(node.right != null)
                traverse.offer(node.right);
            while (!que.isEmpty()) {
                int size = que.size();
                while (size-- > 0) {
                    TreeNode cur = que.poll();
                    if (cur.left != null) {
                        if (cur.left.val == cur.val) {
                            cnt++;
                            que.offer(cur.left);
                        }
                    }
                    if (cur.right != null) {
                        if (cur.right.val == cur.val) {
                            cnt++;
                            que.offer(cur.right);
                        }
                    }
                }
            }
            max = cnt > max ? cnt : max;
        }
        return max;
    }

    //还是不行
    //[1,4,5,4,4,5]
    public int longestUnivaluePath2(TreeNode root){
        int cnt = 0;
        boolean f1 = false;
        boolean f2 = false;
        if(root == null)
            return 0;
        /*if(root !=null && root.left == null && root.right == null)
            return 1;*/
        int a = longestUnivaluePath2(root.left);
        if(root.left != null && root.left.val == root.val){
            cnt = 1;
            f1 = true;
        }
        int b = longestUnivaluePath2(root.right);
        if(root.right != null && root.right.val == root.val){
            cnt = 1;
            f2 = true;
        }
        int tmp = Math.max(a, b);
        a = f1 ? a+1 : 0;
        b = f2 ? b+1 : 0;
        return Math.max(a+b, tmp);
    }

    //3 github
    private int path = 0;

    public int longestUnivaluePath3(TreeNode root) {
        dfs(root);
        return path;
    }

    private int dfs(TreeNode root){
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftPath = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int rightPath = root.right != null && root.right.val == root.val ? right + 1 : 0;
        path = Math.max(path, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}

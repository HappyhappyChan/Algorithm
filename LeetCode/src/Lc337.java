import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc337
 * @Description: [337. House Robber III (Medium) 337. House Robber III (Medium)]
 * @Author: [clh]
 * @Date: 2021/11/19 16:15
 **/
public class Lc337 {
    //案例可以通过 但是会TLE
    public int rob(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return root.val;
        //偷root
        int case1 = root.val;
        if(root.left != null){
            case1 += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null){
            case1 += rob(root.right.left) + rob(root.right.right);
        }

        int case2 = rob(root.left) + rob(root.right);
        return Math.max(case1, case2);
    }
    //改了还是有误
    public int rob2(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return root.val;
        //偷root
        int case1 = root.val;
        int ll = 0, lr = 0, rl = 0, rr = 0;
        int val1 = 0, val2 = 0;
        if(root.left != null){
            val1 = root.left.val;
            ll = rob(root.left.left);
            lr = rob(root.left.right);
        }
        if(root.right != null){
            val2 = root.right.val;
            rl = rob(root.right.left);
            rr = rob(root.right.right);
        }
        //不偷root
        //TLE是因为这里的rob(root.left)又要分偷不偷root.left 重复调用rob(root.left.left)
        int case2 = Math.max(val1 + val2, ll+lr+rl+rr);
        return Math.max(case1+ll+lr+rl+rr, case2);
    }

    //3 leetcode-cn
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    public int rob3(TreeNode root){
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode root){
        if(root == null)
            return;
        dfs(root.left);
        dfs(root.right);
        f.put(root, root.val + g.getOrDefault(root.left, 0) + g.getOrDefault(root.right, 0));
        g.put(root, Math.max(f.getOrDefault(root.left, 0), g.getOrDefault(root.left, 0))
                + Math.max(f.getOrDefault(root.right, 0), g.getOrDefault(root.right, 0)));

    }

    //4 leetcode-cn
    //优化
    public int rob4(TreeNode root) {
        int[] rootStatus = dfs4(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs4(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs4(node.left);
        int[] r = dfs4(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    //对我TLE的代码+memo
    //加了memo后就通过了！ 感激涕零！
    public int rob5(TreeNode root){
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        return rob5Helper(root, map);
    }

    private int rob5Helper(TreeNode root, Map<TreeNode, Integer> memo) {
        if(root == null){
            memo.put(root, 0);
            return 0;
        }
        //可以改成 if (memo.containsKey(root)) return memo.get(root);
        if(memo.getOrDefault(root, 0) > 0)
            return memo.getOrDefault(root, 0);
        int case1 = root.val;
        if(root.left != null){
            case1 += rob5Helper(root.left.left, memo) + rob5Helper(root.left.right, memo);
        }
        if(root.right != null){
            case1 += rob5Helper(root.right.left, memo) + rob5Helper(root.right.right, memo);
        }

        int case2 = rob5Helper(root.left, memo) + rob5Helper(root.right, memo);
        memo.put(root, Math.max(case1, case2));
        return Math.max(case1, case2);
    }
}

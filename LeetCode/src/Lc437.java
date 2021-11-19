import java.util.HashMap;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc437
 * @Description: [437. Path Sum III 统计路径和等于一个数的路径数量]
 * @Author: [clh]
 * @Date: 2021/11/18 16:00
 **/
public class Lc437 {
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        int ret =  pathSumHelper(root, sum)
                + pathSum(root.left, sum)
                +pathSum(root.right, sum);
        return ret;
    }

    private int pathSumHelper(TreeNode root, int sum) {
        int ret = 0;
        if(root == null) return 0;
        if(root.val == sum)
            ret++;
        //因为后面的节点也有可能是负数 正数 相加也有可能=0啊
        ret += pathSumHelper(root.left, sum - root.val) + pathSumHelper(root.right, sum - root.val);
        return ret;
    }

    // 2 dfs
    //leetcode-cn
    public int pathSum2(TreeNode root, int sum) {
        HashMap<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, sum);
    }

    private int dfs(TreeNode root, HashMap<Long, Integer> prefix, long cur, int sum) {
        if(root == null)
            return 0;
        int ret = 0;
        cur += root.val;
        ret = prefix.getOrDefault(cur - sum, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        ret += dfs(root.left, prefix, cur, sum);
        ret += dfs(root.right, prefix, cur, sum);
        //删除当前前缀和
        //在回溯的时候复原，避免不同路径之间产生干扰
        prefix.put(cur, prefix.getOrDefault(cur, 0) -1);
        return ret;
    }

}

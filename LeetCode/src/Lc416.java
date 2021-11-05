/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc416
 * @Description: [416. Partition Equal Subset Sum 划分数组为和相等的两部分]
 * @Author: [clh]
 * @Date: 2021/11/5 16:46
 **/
public class Lc416 {
    //1 我用回溯的思想写的 可以通过案例 但是提交之后会TLE
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length < 2)
            return false;
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        if(sum % 2 != 0)
            return false;
        sum /= 2;
        //然后就转换为背包问题？
        int cur = 0;
        boolean[] seen = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(seen[i])
                continue;
            if(backtrack(nums, sum, i, seen))
                return true;
        }
        return false;
    }

    private static boolean backtrack(int[] nums, int sum, int start, boolean[] seen) {
        if(sum == 0)
            return true;
        if(sum < 0)
            return false;
        if(start >= nums.length)
            return false;
        for(int i = start; i < nums.length; i++){
            if(nums[i] > sum)
                continue;
            if(nums[i] == sum)
                return true;
            seen[i] = true;
            if(backtrack(nums, sum - nums[i], i+1, seen))
                return true;
            seen[i] = false;
        }
        return false;
    }

    //2 我的优化 是可以通过案例 但是还是TLE
    public static boolean canPartition2(int[] nums) {
        if(nums == null || nums.length < 2)
            return false;
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        if(sum % 2 != 0)
            return false;
        sum /= 2;
        //然后就转换为背包问题？
        int cur = 0;
        boolean[] seen = new boolean[nums.length];
        if(backtrack(nums, sum, 0, seen))
            return true;
        return false;
    }

    //3 github 0-1 背包问题
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int W = sum / 2;
        boolean[] dp = new boolean[W + 1];
        dp[0] = true;
        for (int num : nums) {                 // 0-1 背包一个物品只能用一次
            for (int i = W; i >= num; i--) {   // 从后往前，先计算 dp[i] 再计算 dp[i-num]
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[W];
    }

    //4 leetcode-cn
    public boolean canPartition4(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    //leetcode-cn 讨论dp[0][0]=true的合理性 结果也是可以通过的
    public boolean canPartition5(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];

        // 初始化成为 true 虽然不符合状态定义，但是从状态转移来说是完全可以的
        dp[0][0] = true;

        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }

            // 由于状态转移方程的特殊性，提前结束，可以认为是剪枝操作
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[len - 1][target];
    }

    //leetcode-cn 空间优化
    public boolean canPartition6(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args){
        int[] nums1 = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        int[] nums = {100, 100, 100, 100, 97, 99};
        boolean flag = canPartition2(nums);
        System.out.println(flag);
    }
}

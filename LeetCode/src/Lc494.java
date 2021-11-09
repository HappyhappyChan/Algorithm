import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc494
 * @Description: [494. Target Sum (Medium) 改变一组数的正负号使得它们的和为一给定数]
 * @Author: [clh]
 * @Date: 2021/11/8 9:21
 **/
public class Lc494 {
    //1 github
    //由于 \textit{dp}dp 的每一行的计算只和上一行有关，因此可以使用滚动数组的方式，去掉 dp 的第一个维度
    // 将空间复杂度优化到 O(neg)。
    //实现时，内层循环需采用倒序遍历的方式，这种方式保证转移来的是dp[i−1][] 中的元素值。
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int x: nums){
            sum += x;
        }
        if(sum < target || ((sum + target) & 1) == 1){
            return 0;
        }
        int w = (sum + target)/2;
        int[] dp = new int[w+1];
        dp[0] = 1;
        for(int x : nums){
            for(int i = w; i >= x; i--){
                dp[i] = dp[i] + dp[i-x];
            }
        }
        return dp[w];
    }

    //1 dp leetcode-cn改编
    public int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                //等号不能丢！
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }
    //2 leetcode recursive
    //下面这个样子是会报错的 因为cnt会一直都是0不会改变
    /*public int findTargetSumWays2(int[] nums, int s) {
//        int cnt = 0;
        calculate(nums, 0, 0, s, cnt);
        return cnt;
    }

    private void calculate(int[] nums, int i, int sum, int s, int cnt) {
        if(i == nums.length){
            if(sum == s){
                cnt++;
            }
        }else{
            calculate(nums, i+1, sum + nums[i], s, cnt);
            calculate(nums, i+1, sum - nums[i], s, cnt);
        }
    }*/
    int count = 0;

    public int findTargetSumWays2(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    // 3 recursion with memoization
    int total;

    public int findTargetSumWays3(int[] nums, int S) {
        total = Arrays.stream(nums).sum();

        int[][] memo = new int[nums.length][2 * total + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return calculate(nums, 0, 0, S, memo);
    }

    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (memo[i][sum + total] != Integer.MIN_VALUE) {
                return memo[i][sum + total];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + total] = add + subtract;
            return memo[i][sum + total];
        }
    }

    //solution 4 dynamic programming
    public int findTargetSumWays4(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * total + 1];
        dp[0][nums[0] + total] = 1;
        dp[0][-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int sum = -total; sum <= total; sum++) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
                }
            }
        }

        return Math.abs(S) > total ? 0 : dp[nums.length - 1][S + total];
    }

    //5 1D dp
    public int findTargetSumWays5(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[] dp = new int[2 * total + 1];
        dp[nums[0] + total] = 1;
        dp[-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2 * total + 1];
            for (int sum = -total; sum <= total; sum++) {
                if (dp[sum + total] > 0) {
                    next[sum + nums[i] + total] += dp[sum + total];
                    next[sum - nums[i] + total] += dp[sum + total];
                }
            }
            dp = next;
        }

        return Math.abs(S) > total ? 0 : dp[S + total];
    }

    //6 github dfs
    public int findTargetSumWays6(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }

    private int findTargetSumWays(int[] nums, int start, int S) {
        if (start == nums.length) {
            return S == 0 ? 1 : 0;
        }
        return findTargetSumWays(nums, start + 1, S + nums[start])
                + findTargetSumWays(nums, start + 1, S - nums[start]);
    }
}

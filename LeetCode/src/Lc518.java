/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc518
 * @Description: [518. Coin Change 2 找零钱的硬币数组合]
 * @Author: [clh]
 * @Date: 2021/11/8 16:30
 **/
public class Lc518 {
    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount+1];
        //这里要是1不能是0
        //只有当不选取任何硬币时，金额之和才为 0，因此只有 1 种硬币组合
        dp[0] = 1;
        //不能倒序！倒序是错的
        /*for(int coin: coins){
            for(int i = amount; i >= coin; i--){
                dp[i] = dp[i] + dp[i-coin];
            }
        }*/

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    //2 leetcode-cn
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        f[0][0] = 1;   // 使用0种货币，凑0元钱,也是一种方案
        for (int i = 1; i <= n; i++) {
            int v = coins[i - 1];
            for (int j = 0; j <= amount; j++)
                for (int k = 0; k * v <= j; k++)
                    f[i][j] += f[i - 1][j - k * v];  //状态计算方程
        }
        return f[n][amount];
    }

    //3 leetcode-cn
    public int change3(int amount, int[] coins) {
        int[] f = new int[amount + 1];
        f[0] = 1; //f[0][0] = 1;
        for(int i = 1; i <= coins.length; i++)
        {
            int v =coins[i - 1];
            for(int j = v; j <= amount; j++)
                f[j] += f[j - v];
        }
        return f[amount];
    }


}

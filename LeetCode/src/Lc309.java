/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc309
 * @Description: [309. Best Time to Buy and Sell Stock with Cooldown 需要冷却期的股票交易]
 * @Author: [clh]
 * @Date: 2021/11/9 10:32
 **/
public class Lc309 {
    //1 leetcode-cn
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        int n = prices.length;
        int[][] f = new int[n][3];
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        //别忘了初始化
        f[0][0] = -prices[0];
        for(int i = 1; i < n; i++){
            f[i][0] = Math.max(f[i-1][0], f[i-1][2]-prices[i]);
            f[i][1] = f[i-1][0] + prices[i];
            f[i][2] = Math.max(f[i-1][1], f[i-1][2]);
        }
        return Math.max(f[n-1][1],f[n-1][2]);
    }

    //2 空间优化
    public int maxProfit2(int[] prices) {
        if(prices.length == 0) return 0;
        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for(int i = 1; i < n; i++){
            int next0 = Math.max(f0, f2-prices[i]);
            int next1 = f0 + prices[i];
            int next2 = Math.max(f1, f2);
            f0 = next0;
            f1 = next1;
            f2 = next2;
        }
        return Math.max(f1, f2);
    }
    //3 github
    //这个代码就没看懂
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] s1 = new int[N];
        int[] sell = new int[N];
        int[] s2 = new int[N];
        s1[0] = buy[0] = -prices[0];
        sell[0] = s2[0] = 0;
        for (int i = 1; i < N; i++) {
            buy[i] = s2[i - 1] - prices[i];
            s1[i] = Math.max(buy[i - 1], s1[i - 1]);
            sell[i] = Math.max(buy[i - 1], s1[i - 1]) + prices[i];
            s2[i] = Math.max(s2[i - 1], sell[i - 1]);
        }
        return Math.max(sell[N - 1], s2[N - 1]);
    }

}

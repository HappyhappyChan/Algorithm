/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc714
 * @Description: [4. Best Time to Buy and Sell Stock with Transaction Fee ]
 * @Author: [clh]
 * @Date: 2021/11/9 15:31
 **/
public class Lc714 {
    //我自己写的 不知道哪里错了
    public int maxProfit(int[] prices, int fee) {
        if(prices.length < 2) return 0;
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][3] = -prices[0];
        //0--有股票 不卖
        //1 没股票 不买 2--有股票卖了 3--无股票，买了
        for(int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][3], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][2] = Math.max(dp[i-1][0]+prices[i]-fee, dp[i-1][3]+prices[i]-fee);
            dp[i][3] = Math.max(dp[i-1][1]-prices[i], dp[i-1][2]-prices[i]);
        }
        /*int temp1 = Math.max(dp[n-1][0], dp[n-1][1]);
        int temp2 = Math.max(dp[n-1][2], dp[n-1][3]);
        return Math.max(temp1, temp2);*/
        return Math.max(dp[n-1][1], dp[n-1][2]);
    }

    //2 leetcode
    public int maxProfit2(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    //3 leetcode 贪心算法
    public int maxProfit3(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}

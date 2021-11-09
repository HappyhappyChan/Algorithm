/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc123
 * @Description: [123. Best Time to Buy and Sell Stock III (Hard) 只能进行两次的股票交易]
 * @Author: [clh]
 * @Date: 2021/11/9 17:02
 **/
public class Lc123 {
    //来自哔哩哔哩的方法
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int len = prices.length;
        int[] l2r = new int[len]; // left -> right 找最大的 范围是[1,k]
        int[] r2l = new int[len]; // right -> left 找最大利润 范围是[k, n-2]

        int p1 = 0; // p1用来记录[1,k]范围内最大profit
        int min = prices[0];
        for(int i = 1; i < len; i++){
            if(prices[i] - min > p1){
                p1 = prices[i] - min;
            }
            l2r[i] = p1;
            if(prices[i] < min){
                min = prices[i];
            }
        }

        int p2 = 0;
        int max = prices[len-1];
        for(int i = len-2; i >= 0; i--){
            if(max - prices[i] > p2){
                p2 = max - prices[i];
            }
            r2l[i] = p2;
            if(prices[i] > max){
                max = prices[i];
            }
        }
        int p = 0;
        for(int i = 0; i < len; i++){
            if(l2r[i] + r2l[i] > p){
                p = l2r[i] + r2l[i];
            }
        }
        return p;
    }

    // 2 dp
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int[][][] dp = new int[prices.length][2][3];
        int MIN_VALUE = Integer.MIN_VALUE / 2;//因为最小值再减去1就是最大值Integer.MIN_VALUE-1=Integer.MAX_VALUE
        //初始化
        dp[0][0][0] = 0;//第一天休息
        dp[0][0][1] = dp[0][1][1] = MIN_VALUE;//不可能
        dp[0][0][2] = dp[0][1][2] = MIN_VALUE;//不可能
        dp[0][1][0] = -prices[0];//买股票
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = MIN_VALUE;
        }
        return Math.max(0, Math.max(dp[prices.length - 1][0][1], dp[prices.length - 1][0][2]));
    }

    //3 github
    public int maxProfit3(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int curPrice : prices) {
            if (firstBuy < -curPrice) {
                firstBuy = -curPrice;
            }
            if (firstSell < firstBuy + curPrice) {
                firstSell = firstBuy + curPrice;
            }
            if (secondBuy < firstSell - curPrice) {
                secondBuy = firstSell - curPrice;
            }
            if (secondSell < secondBuy + curPrice) {
                secondSell = secondBuy + curPrice;
            }
        }
        return secondSell;
    }
}

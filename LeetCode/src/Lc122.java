/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc122
 * @Description: [122. Best Time to Buy and Sell Stock II (Easy) 买卖股票的最大收益 II]
 * @Author: [clh]
 * @Date: 2021/10/28 16:44
 **/
public class Lc122 {

    // 1 github
    public int maxProfit(int[] prices) {
        int sum = 0;
        for(int i = 1; i < prices.length; i++){
            sum += prices[i] > prices[i-1] ? prices[i] - prices[i-1] : 0;
        }
        return sum;
    }

    //2 brute force
    //leetcode
    public int maxProfit2(int[] prices) {
        return calculate(prices, 0);
    }

    public int calculate(int prices[], int s) {
        if (s >= prices.length)
            return 0;
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }

    // 3 peak valley approach
    public int maxProfit3(int[] prices){
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while(i < prices.length - 1){
            //找山谷
            while(i < prices.length - 1 && prices[i] >= prices[i+1])
                i++;
            valley = prices[i];
            //找山峰
            while(i < prices.length - 1 && prices[i] <= prices[i+1]){
                i++;
            }
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}

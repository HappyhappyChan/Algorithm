/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc121
 * @Description: [121. Best Time to Buy and Sell Stock 买卖股票最大的收益]
 * @Author: [clh]
 * @Date: 2021/10/28 16:19
 **/
public class Lc121 {
    // 1 我自己想的暴力算法 但是这种方法会超时……
    public int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;
        int max = 0;
        for(int i = 0; i < prices.length; i++){
            for(int j = i + 1; j < prices.length; j++){
                int tmp = prices[j] - prices[i];
                max = Math.max(tmp, max);
            }
        }
        return max;
    }

    //2 github
    public int maxProfit2(int[] prices) {
        if(prices.length < 2)
            return 0;
//        int min = Integer.MAX_VALUE;
        int max = 0;
        int min = prices[0];
        for(int x: prices) {
            /*min = Math.min(min, x);
            max = Math.max(max, x - min);*/
            //对上面的优化
            if (min > x) min = x;
            else max = Math.max(max, x - min);
        }
        return max;
    }
}

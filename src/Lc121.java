/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: lc121
 * @Description: [Best Time to Buy and Sell Stock]
 * @Author: [clh]
 * @Date: 2021/10/22 20:53
 **/
public class Lc121 {

    //solution 1 github 贪心策略
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int sofarmin = prices[0];
        int max = 0;
        for(int i = 1; i < prices.length; i++){
            sofarmin = Math.min(sofarmin, prices[i]);
            max = Math.max(max, prices[i] - sofarmin);
        }
        return max;
    }

    //solution 2 lc brute force
    public int solution2(int[] prices){
        int max = 0;
        for(int i = 0; i < prices.length; i++){
            for(int j = i + 1; j < prices.length; j++){
                max = Math.max(prices[j] - prices[i], max);
            }
        }
        return max;
    }

    //solution 3 lc one pass
    public int solution3(int[] prices){
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < minprice)
                minprice = prices[i];
            else if(prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }


}

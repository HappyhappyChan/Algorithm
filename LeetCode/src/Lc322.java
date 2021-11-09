import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc322
 * @Description: [322. Coin Change  找零钱的最少硬币数]
 * @Author: [clh]
 * @Date: 2021/11/8 14:56
 **/
public class Lc322 {
    //1 bfs 最短路问题
    public int coinChange(int[] coins, int amount) {
        if(amount==0) return 0;
        Queue<Integer> que = new LinkedList<Integer>();
        boolean[] marked = new boolean[amount + 1];
        que.add(amount);
        marked[amount] = true;
        int level = 0;
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            while(size -- > 0){
                int cur = que.poll();
                for(int s : coins){
                    //只有两个结点之间的差是完全平方数才加进去
                    //这里的两个结点分别是cur next
                    int next = cur - s;
                    if(next < 0)
                        //跟279不同的是这里是continue 而不是break
                        continue;
                    if(next == 0)
                        return level;
                    //为啥啊
                    //因为如果一个数字出现过，有可能在同层前面已经遍历，那同一层的话最终得到的level也一样 不用再算
                    //如果是上一层出现，那肯定是按照上一层来计算level 使得level更小 所以也没必要计算这一个
                    if(marked[next]){
                        continue;
                    }
                    marked[next] = true;
                    que.add(next);
                }
            }
        }
        return -1;
    }

    //2 link279 用dp 错的
    public int coinChange2(int[] coins, int amount){
        int[] dp = new int[amount+1];
        for(int i = 1; i <= amount; i++){
            int min = Integer.MAX_VALUE;
            for(int coin : coins){
                if(coin > i)
                    continue;
                //[2] 3
                min = Math.min(min, (dp[i-coin] == Integer.MAX_VALUE ? -1 : (dp[i-coin] == -1? -1 : dp[i-coin]+1)));
            }
            dp[i] = min;
        }
        //[2] 1
        return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
    }

    //3 github 完全背包问题
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0 || coins == null) return 0;
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { //将逆序遍历改为正序遍历
                if (i == coin) {
                    dp[i] = 1;
                } else if (dp[i] == 0 && dp[i - coin] != 0) {
                    dp[i] = dp[i - coin] + 1;

                } else if (dp[i - coin] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    //4 leetcode cn 递归
    public int coinChange4(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange4(coins, amount, new int[amount]);
    }

    private int coinChange4(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange4(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    //5 leetcode cn dp
    public int coinChange5(int[] coins, int amount) {
        //设置这个Max是如何设置的呢？
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}

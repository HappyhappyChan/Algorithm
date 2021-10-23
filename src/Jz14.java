/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz14
 * @Description: [14 剪绳子]
 * @Author: [clh]
 * @Date: 2021/10/22 15:51
 **/
public class Jz14 {

    //solution 1: github 根据数学规律来
    public int cutRope(int target) {
        if(target < 2)
            return 0;
        if(target == 2)
            return target;
        if(target == 3)
            return 2;
        int timesof3 = target / 3;
        if(target - timesof3 * 3 == 1)
            timesof3--;
        int timesof2 = (target - timesof3*3) / 2;
        return (int)(Math.pow(3, timesof3)) * (int)(Math.pow(2, timesof2));
    }

    //solution 2: github dynamic program
    public int solution2(int n){
        //dp[i]表示长度为i的绳子分割的最大乘积
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
            }
        }
        return dp[n];
    }

    //from bilibili

    public int solution202(int n){
        //dp[i]表示长度为i的绳子分割的最大乘积
        int[] dp = new int[n + 1];
        if(n == 2)
            return 1;
        if(n == 3)
            return 2; // 1 2
        //dp【3】=3要放在二重循环里去理解，不能单独理解，
        // 二重循环里这句话代表的是截出来长度为3的绳子，
        // 同时要拼接上剩下的绳子求最优解，也就是说要使得总体最大，
        // dp【3】=3子绳子也要最大，长度为3和2 的绳子比较特殊，拆分会变小，
        // 所以要不能拆分，做特殊处理。
        //比如说：n = 5，那么当前5分成3和2的时候，dp【3】 = 3肯定就是作为一个整体去与长度为2的绳子频接，
        // 而不是将长度为3的绳子进行分割(分割成1和2，既dp【3】 = 1 * 2 = 2)
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int k = 1; k <= n; k++){
            for(int j = 1; j <= k/2; j++){
                dp[k] = Math.max(dp[k], dp[j]*dp[k-j]);
            }
        }
        return dp[n];
    }
    //solution 3: brute force from newcoder
    public int solution3(int target){
        if(target <= 1)
            return 1;
        if(target == 2) return 1;
        if(target == 3) return 2;
        int[] nums = new int[target + 1];
        //定义基本情况 nums[i]表示剩下i米时 最大乘积
        nums[0] = 1;
        nums[1] = 1;
        nums[2] = 2;
        nums[3] = 3;
        //用于记录最大值
        int max = 0;
        for(int i = 4; i <= target; i++){
            //防止重复计算 只算到i/2
            for(int j = 1; j <= i/2; j++){
                int tmp = nums[j] * nums[i-j];
                if(tmp > max){
                    max = tmp;
                    nums[i] = max;
                }
            }
        }
        return max;
    }

    //solution 4:递归 newcoder
    public int cutRope4(int target) {
        return cutRope4(target, 0);
    }
    public int cutRope4(int target, int max) {
        int maxValue = max;
        for(int i = 1; i < target; ++i){
            maxValue = Math.max(maxValue, i*cutRope4(target -i, target -i));
        }
        return maxValue;
    }
}

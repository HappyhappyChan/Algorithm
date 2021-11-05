/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc343
 * @Description: [343. Integer Break 分割整数的最大乘积]
 * @Author: [clh]
 * @Date: 2021/11/4 16:06
 **/
public class Lc343 {
    //1 leetcode discuss
    public int integerBreak(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int product = 1;
        while(n>4){
            product*=3;
            n-=3;
        }
        product*=n;
        return product;
    }

    //2 github dynamic programming
    public int integerBreak2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[n];
    }

    //3 leetcode cn dynamic programming优化
    public int integerBreak3(int n) {
        if(n < 4) return n-1;
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(Math.max(2*(i-2), 2*dp[i-2]), Math.max(3*(i-3), 3*dp[i-3]));
        }
        return dp[n];
    }

    //4 leetcode cn 利用数学
    public int integerBreak4(int n) {
        if(n <= 3) return n-1;
        int quotient = n / 3;
        int remainder = n % 3;
        if(remainder == 0){
            return (int)Math.pow(3, quotient);
        }else if(remainder == 1){
            return (int)Math.pow(3, quotient-1) * 4;
        }else{
            return (int)Math.pow(3, quotient) * 2;
        }
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc474
 * @Description: [474. Ones and Zeroes (Medium) 01 字符构成最多的字符串]
 * @Author: [clh]
 * @Date: 2021/11/8 12:17
 **/
public class Lc474 {
    //1 leetcode-cn 跟github一样
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len+1][m+1][n+1];
        for(int i = 1; i <= len; i++){
            String s = strs[i-1];
            char[] c = s.toCharArray();
            int zero = 0;
            int one = 0;
            for(char x : c){
                if(x=='0') zero++;
                if(x=='1') one++;
            }
            for(int j = 0; j <= m; j++){
                for(int k = 0; k <= n; k++){
                    dp[i][j][k] = dp[i-1][j][k];
                    if(j >= zero && k >= one){
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j-zero][k-one] +1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    //2 对上面的空间优化
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= len; i++) {
            String s = strs[i - 1];
            char[] c = s.toCharArray();
            int zero = 0;
            int one = 0;
            for (char x : c) {
                if (x == '0') zero++;
                if (x == '1') one++;
            }
            for(int j = m; j >= 0; j--){
                for(int k = n; k >= 0; k--){
                    dp[j][k] = dp[j][k];
                    if(j >= zero && k >= one){
                        dp[j][k] = Math.max(dp[j][k], dp[j-zero][k-one] +1);
                    }
                }
            }
        }
        return dp[m][n];
    }
}

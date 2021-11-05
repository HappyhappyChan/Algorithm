/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc1143
 * @Description: [1143. Longest Common Subsequence 最长公共子序列]
 * @Author: [clh]
 * @Date: 2021/11/5 15:49
 **/
public class Lc1143 {
    //1 我自己写 我根据github对最长公共子序列解析写的
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0)
            return 0;
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1][l2];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1: 0;
        for(int p2 = 1; p2 < l2; p2++){
            if(text1.charAt(0) == text2.charAt(p2)){
                dp[0][p2] = 1;
            }else{
                dp[0][p2] = dp[0][p2-1];
            }
        }
        for(int p1 = 1; p1 < l1; p1++){
            if(text2.charAt(0) == text1.charAt(p1)){
                dp[p1][0] = 1;
            }else{
                dp[p1][0] = dp[p1-1][0];
            }
        }
        for(int p1 = 1; p1 < l1; p1++){
            for(int p2 = 1; p2 < l2; p2++){
                if(text1.charAt(p1) == text2.charAt(p2)){
                    dp[p1][p2] = dp[p1-1][p2-1] + 1;
                }else{
                    dp[p1][p2] = Math.max(dp[p1-1][p2], dp[p1][p2-1]);
                }
            }
        }
        return dp[l1-1][l2-1];
    }

    //2 github
    public int longestCommonSubsequence2(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    //3 leetcode space optimization
    //Note: use k ^ 1 and k ^= 1 to switch between dp[0] (row 0) and dp[1] (row 1).
    public int longestCommonSubsequence3(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m < n)  return longestCommonSubsequence(s2, s1);
        int[][] dp = new int[2][n + 1];
        for (int i = 0, k = 1; i < m; ++i, k ^= 1)
            for (int j = 0; j < n; ++j)
                if (s1.charAt(i) == s2.charAt(j)) dp[k][j + 1] = 1 + dp[k ^ 1][j];
                else dp[k][j + 1] = Math.max(dp[k ^ 1][j + 1], dp[k][j]);
        return dp[m % 2][n];
    }

}

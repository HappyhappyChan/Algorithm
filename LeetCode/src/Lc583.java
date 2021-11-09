/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc583
 * @Description: [583. Delete Operation for Two Strings (Medium) 删除两个字符串的字符使它们相等]
 * @Author: [clh]
 * @Date: 2021/11/9 20:16
 **/
public class Lc583 {
    //1 recursion leetcode
    public int minDistance(String s1, String s2) {
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length());
    }

    //longest common sentences
    private int lcs(String s1, String s2, int m, int n) {
        if(m == 0 || n == 0)
            return 0;
        if(s1.charAt(m-1) == s2.charAt(n-1))
            return 1 + lcs(s1, s2, m-1, n-1);
        else
            return Math.max(lcs(s1, s2, m, n-1), lcs(s1, s2, m-1, n));
    }

    //2 lcs with memoization
    private int lcs(String s1, String s2, int m, int n, int[][] memo){
        if(m == 0 || n == 0)
            return 0;
        if(memo[m][n] > 0)
            return memo[m][n];
        if(s1.charAt(m-1) == s2.charAt(n-1))
            memo[m][n] = 1 + lcs(s1, s2, m-1, n-1, memo);
        else
            memo[m][n] = Math.max(lcs(s1, s2, m, n-1, memo), lcs(s1, s2, m-1, n, memo));
        return memo[m][n];
    }

    //3 leetcode dynamic programming
    public int minDistance3(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    continue;
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return s1.length() + s2.length() - 2 * dp[s1.length()][s2.length()];
    }

    //4 leetcode dynamic programming without lcs
    public int minDistance4(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = i + j;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
    //5 leetcode 1-d dp
    public int minDistance5(String s1, String s2) {
        int[] dp = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int[] temp=new int[s2.length()+1];
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    temp[j] = i + j;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    //dp[i - 1][j - 1]
                    temp[j] = dp[j - 1];
                else
                    //dp[i - 1][j], dp[i][j - 1]
                    temp[j] = 1 + Math.min(dp[j], temp[j - 1]);
            }
            //dp用来保存上次循环的结果 tmp保存单次循环的结果
            dp=temp;
        }
        return dp[s2.length()];
    }
}


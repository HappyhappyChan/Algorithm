/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc62
 * @Description: [62. Unique Paths 矩阵的总路径数]
 * @Author: [clh]
 * @Date: 2021/11/4 10:52
 **/
public class Lc62 {
    // 1 我写的dp
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j != 0){
                    dp[i][j] = 1;
                }else if(j == 0 && i != 0){
                    dp[i][j] = 1;
                }else if(i == 0 && j == 0){
                    dp[0][0] = 1;
                }else{
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
    // 2 递归
    public int uniquePaths2(int m, int n) {
        return helper(m-1, n-1);
    }

    private int helper(int r, int c) {
        //别忘了这种情况
        if(r < 0 || c < 0)
            return 0;
        if(r == 0 || c == 0)
            return 1;
        return helper(r, c-1) + helper(r-1, c);
    }

    //3 memorization
    public static int uniquePaths3(int m, int n) {
        return uniquePathsHelper(m - 1, n - 1, new int[m][n]);
    }
    //我也不明白为什么leetcode discussion里面设置的是new int[n][m]
    //将[n][m]改为[m][n]后也可以通过
    //有可能不管这么设置 递推的公式都是一样的
    private static int uniquePathsHelper(int m, int n, int[][] memo) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (m == 0 || n == 0) {
            return 1;
            //如果少了下面这一步就会time limit exceeded
        } else if (memo[m][n] > 0) {
            return memo[m][n];
        } else {
            memo[m][n] = uniquePathsHelper(m - 1, n, memo) + uniquePathsHelper(m, n - 1, memo);
            return memo[m][n];
        }
    }

    //4 math github
    public int uniquePaths4(int m, int n) {
        int S = m + n - 2;  // 总共的移动次数
        int D = m - 1;      // 向下的移动次数
        long ret = 1;
        for (int i = 1; i <= D; i++) {
            ret = ret * (S - D + i) / i;
        }
        return (int) ret;
    }

    public static void main(String[] args){
        int m = 3, n = 3;
        int res = uniquePaths3(m, n);
    }

}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz47
 * @Description: [礼物的最大价值]
 * @Author: [clh]
 * @Date: 2021/10/24 15:40
 **/
public class Jz47 {
    public int getMost(int[][] board) {
        // write code here
        int[][] dp = new int[6][6];
        dp[0][0] = board[0][0];
        for(int i = 1; i < 6; i++)
            dp[0][i] = dp[0][i-1]+board[0][i];
        for(int i = 1; i < 6; i++)
            dp[i][0] = dp[i-1][0]+board[i][0];
        for(int i = 1; i < 6; i++){
            for(int j = 1; j < 6; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + board[i][j];
            }
        }
        return dp[5][5];
    }
}

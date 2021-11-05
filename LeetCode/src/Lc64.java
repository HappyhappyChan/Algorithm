import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc64
 * @Description: [64. Minimum Path Sum 矩阵的最小路径和]
 * @Author: [clh]
 * @Date: 2021/11/3 23:45
 **/
public class Lc64 {
    //1 前面有说可以用bfs
    //不能用bfs！bfs只能用于无权图！
    // dp from github
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(j == 0){
                    //只能从上方走到这个位置
                    dp[j] = dp[j];
                }else if(i == 0){
                    //只能从左侧走到该位置
                    dp[j] = dp[j-1];
                }else{
                    dp[j] = Math.min(dp[j-1], dp[j]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[n-1];
    }

    //2 recursion from leetcode discuss
    public int minPathSum2(int[][] grid) {

        int height = grid.length;
        int width = grid[0].length;
        return min(grid, height - 1, width - 1);

    }

    public int min(int[][]grid, int row, int col){

        if(row == 0 && col == 0) return grid[row][col]; // this is the exit of the recursion
        if(row == 0) return grid[row][col] + min(grid, row, col - 1); /** when we reach the first row, we could only move horizontally.*/
        if(col == 0) return grid[row][col] + min(grid, row - 1, col); /** when we reach the first column, we could only move vertically.*/
        return grid[row][col] + Math.min(min(grid, row - 1, col), min(grid, row, col - 1)); /** we want the min sum path so we pick the cell with the less value */

    }

    //3 dp from leetcode discuss
    public int minPathSum3(int[][] grid){
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j != 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                }else if(i != 0 && j == 0){
                    grid[i][j] = grid[i][j] + grid[i-1][j];
                }else if(i == 0 && j == 0){
                    grid[i][j] = grid[i][j];
                }else{
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j-1], grid[i-1][j]);
                }
            }
        }
        return grid[m-1][n-1];
    }

}

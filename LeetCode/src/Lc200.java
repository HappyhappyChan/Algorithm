import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc200
 * @Description: [200. Number of Islands 矩阵中的连通分量数目]
 * @Author: [clh]
 * @Date: 2021/10/31 16:38
 **/
public class Lc200 {
    // 1 dfs recursive
    public int numIslands(char[][] grid) {
        int cnt = 0;
        if(grid.length == 0 || grid == null)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                cnt += dfs(grid, r, c);
            }
        }
        return cnt;
    }
    public int dfs(char[][] grid, int r, int c){
        int m = grid.length;
        int n = grid[0].length;
        if(r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0')
            return 0;
        grid[r][c] = '0';
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int shape = 1;
        for(int[] d: dir){
            shape += dfs(grid, r + d[0], c + d[1]);
        }
        return shape > 0 ? 1 : 0;
    }

    //2 dfs iterative
    public int numIslands2(char[][] grid) {
        if(grid.length == 0 || grid == null)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int cnt = 0;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(grid[r][c] == '0')
                    continue;
                Stack<int[]> stack = new Stack<>();
                stack.push(new int[]{r, c});
                grid[r][c] = '0';
                int shape = 1;
                while(!stack.isEmpty()){
                    int[] cur = stack.pop();
                    for(int[] d: dir){
                        int nr = cur[0] + d[0];
                        int nc = cur[1] + d[1];
                        if(nc < 0 || nr < 0 || nr >= m || nc >= n || grid[nr][nc] == '0')
                            continue;
                        grid[nr][nc] = '0';
                        shape++;
                        stack.push(new int[]{nr, nc});
                    }
                }
                if(shape > 0)
                    cnt ++;
            }
        }
        return cnt;
    }

    //3 bfs queue
    public int numIslands3(char[][] grid){
        if(grid.length == 0 || grid == null)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int cnt = 0;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if(grid[r][c] == '0')
                    continue;
                grid[r][c] = '0';
                int shape = 1;
                Queue<int[]> que = new LinkedList<>();
                que.add(new int[]{r, c});
                while(!que.isEmpty()){
                    int[] cur = que.poll();
                    for(int[] d: dir){
                        int nr = cur[0] + d[0];
                        int nc = cur[1] + d[1];
                        if(nc < 0 || nr < 0 || nr >= m || nc >= n || grid[nr][nc] == '0')
                            continue;
                        grid[nr][nc] = '0';
                        shape++;
                        que.add(new int[]{nr, nc});
                    }
                }
                if(shape > 0)
                    cnt ++;
            }
        }
        return cnt;
    }

    //4 github 也是dfs 但是代码简洁很多 省去了没必要的步骤
    public int numIslands4(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int islandsNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    //不用统计面积的话 直接将走过的标记即可
                    dfs4(grid, i, j);
                    islandsNum++;
                }
            }
        }
        return islandsNum;
    }

    private void dfs4(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : direction) {
            dfs4(grid, i + d[0], j + d[1]);
        }
    }
}

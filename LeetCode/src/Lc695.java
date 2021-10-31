import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc695
 * @Description: [695 Max Area of Island (Medium) 查找最大的连通面积]
 * @Author: [clh]
 * @Date: 2021/10/31 15:11
 **/
public class Lc695 {

    // 1 depth-first search recursive
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int maxarea = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                maxarea = Math.max(maxarea, dfs(grid, i, j));
            }
        }
        return maxarea;
    }

    private int dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        if(r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0){
            return 0;
        }
        grid[r][c] = 0;
        int area = 1;
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] d: direction){
            area += dfs(grid, r + d[0], c + d[1]);
        }
        return area;
    }

    //2 dfs leetcode recursive
    public int area(int[][] grid, boolean[][] seen, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
                seen[r][c] || grid[r][c] == 0)
            return 0;
        seen[r][c] = true;
        return (1 + area(grid, seen, r+1, c) + area(grid, seen,r-1, c)
                + area(grid, seen,r, c-1) + area(grid, seen,r, c+1));
    }

    public int maxAreaOfIsland2(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                ans = Math.max(ans, area(grid, seen, r, c));
            }
        }
        return ans;
    }

    //2 leetcode dfs iterative
    public int maxAreaOfIsland3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] seen = new boolean[m][n];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        int ans = 0;
        //sr == start row
        for(int sr = 0; sr < m; sr++){
            for(int sc = 0; sc < n; sc++){
                if(grid[sr][sc] == 1 && !seen[sr][sc]){
                    int shape = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{sr, sc});
                    seen[sr][sc] = true;
                    while(!stack.isEmpty()){
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        shape++;
                        for(int k = 0; k < 4; k++){
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if(nc < 0 || nc >= m || nr < 0 || nr >= n || seen[nr][nc])
                                continue;
                            stack.push(new int[]{nr, nc});
                            seen[nr][nc] = true;
                        }
                    }
                    ans = Math.max(ans, shape);
                }
            }
        }
        return ans;
    }

    // 3 leetcode discussion bfs
    public int maxAreaOfIsland4(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(grid[r][c] == 1)
                    ans = Math.max(ans, bfs(grid, r, c));
            }
        }
        return ans;
    }

    public int bfs(int[][] grid, int r, int c){
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dir = new int[]{0, 1, 0, -1, 0};
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{r, c});
        grid[r][c] = 0;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            ans++;
            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dir[i];
                int nc = cur[1] + dir[i+1];
                if(nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == 0)
                    continue;
                grid[nr][nc] = 0; // marked as visited
                que.offer(new int[]{nr, nc});
            }
        }
        return ans;
    }

}

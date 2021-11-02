import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc417
 * @Description: [417. Pacific Atlantic Water Flow 能到达的太平洋和大西洋的区域]
 * @Author: [clh]
 * @Date: 2021/11/1 9:14
 **/
public class Lc417 {
    //1 我的没写出来 写到一半发现要两边都能流 而不是太平洋留到大西洋
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ret = new ArrayList<>();
        if(heights == null || heights.length == 0)
            return ret;
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] flag = new boolean[m][n];
        flag[0][n - 1] = true;
        flag[m-1][0] = true;
        ret.add(new ArrayList<Integer>(){{
            add(0);
            add(n-1);
        }});
        ret.add(new ArrayList<Integer>(){{
            add(m-1);
            add(0);
        }});
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(flag[r][c])
                    continue;
                dfs(heights, flag, r, c, ret);
            }
        }
        return ret;
    }

    private void dfs(int[][] heights, boolean[][] flag, int r, int c, List<List<Integer>> ret) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dir = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
        if(flag[r][c])
            ret.add(new ArrayList<Integer>(){{
                add(r);
                add(c);
            }});
        for(int[] d : dir){
            int nr = r + d[0];
            int nc = c + d[1];
            if(nr < 0 || nc < 0 || nr >= m || nc >= n)
                continue;
            if(heights[r][c] < heights[nr][nc])
                continue;

        }
    }

    //2 github
    public List<List<Integer>> pacificAtlantic2(int[][] matrix){
        List<List<Integer>> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ret;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            //因为要流到太平洋 必须经过最上边或最左边
            dfs2(i, 0, canReachP, matrix);
            //因为要流到大西洋，必须经过最右边或最下边
            dfs2(i, n - 1, canReachA, matrix);
        }
        for (int i = 0; i < n; i++) {
            //因为要流到太平洋 必须经过最上边或最左边
            dfs2(0, i, canReachP, matrix);
            //因为要流到大西洋，必须经过最右边或最下边
            dfs2(m - 1, i, canReachA, matrix);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachP[i][j] && canReachA[i][j]) {
                    ret.add(Arrays.asList(i, j));
                }
            }
        }

        return ret;
    }
    private void dfs2(int r, int c, boolean[][] canReach, int[][] matrix) {
        //因为如果是true 说明已经探索过了
        if (canReach[r][c]) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        //只有能到达的才会继续调用dfs
        canReach[r][c] = true;
        for (int[] d : direction) {
            int nextR = d[0] + r;
            int nextC = d[1] + c;
            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n
                    || matrix[r][c] > matrix[nextR][nextC]) {
                //因为是从边界倒退 所以边界要小于里面才能让里面的水流出来
                continue;
            }
            //只有能到达的才会继续调用dfs
            dfs2(nextR, nextC, canReach, matrix);
        }
    }
}

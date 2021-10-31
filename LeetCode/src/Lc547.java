/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc547
 * @Description: [547. Number of Provinces 好友关系的连通分量数目]
 * @Author: [clh]
 * @Date: 2021/10/31 17:05
 **/
public class Lc547 {
    // 1 我自己写的
    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected.length == 0)
            return 0;
        int m = isConnected.length;
        int n = isConnected[0].length; // m == n 因为是邻接矩阵嘛
        int cnt = 0; //记录个数
        int[][] seen = new int[m][n]; //标记是否看过
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(isConnected[r][c] == 0)
                    continue;
                dfs(isConnected, r, c);
                cnt++;
            }
        }
        return cnt;
    }

    public void dfs(int[][] mat, int r, int c){
        int m = mat.length;
        int n = mat[0].length;
        if(mat[r][c] == 0)
            return;
        int[][] seen = new int[m][n]; //标记是否看过
        mat[r][c] = 0;
        for(int i = 0; i < m; i++){
            if(mat[r][i] == 1){
                mat[r][i] = 0;
                dfs(mat, i, r);
            }
        }
    }

    // 2 github
    public int findCircleNum2(int[][] M) {
        int n = M.length;
        int circleNum = 0;
        boolean[] hasVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!hasVisited[i]) {
                dfs2(M, i, hasVisited);
                circleNum++;
            }
        }
        return circleNum;
    }

    private void dfs2(int[][] M, int i, boolean[] hasVisited) {
        hasVisited[i] = true;
        int n = M.length;
        for (int k = 0; k < n; k++) {
            if (M[i][k] == 1 && !hasVisited[k]) {
                dfs2(M, k, hasVisited);
            }
        }
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc130
 * @Description: [130. Surrounded Regions 填充封闭区域]
 * @Author: [clh]
 * @Date: 2021/10/31 21:06
 **/
public class Lc130 {
    //1 我搞了贼久 终于通过了
    public void solve(char[][] board) {
        if(board == null || board.length == 0)
            return;
        int m = board.length;
        int n = board[0].length;
        boolean[][] seen = new boolean[m][n];
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(board[r][c]=='X')
                    seen[r][c] = true;
                if(seen[r][c])
                    continue;
                seen[r][c] = true;
                board[r][c] = '1';
                boolean flag = dfs(board, seen, r, c);
                change(board, flag);
            }
        }
    }

    private void change(char[][] board, boolean flag) {
        /*if(flag){
            for(char[] x : board){
                for(char c : x){
                    if(c == '1')
                    //这样只是给c赋值 不会改变board
                        c = 'O';
                }
            }
        }else{
            for(char[] x : board){
                for(char c : x){
                    if(c == '1')
                        c = 'X';
                }
            }
        }*/
        int m = board.length;
        int n = board[0].length;
        if(flag){
            for(int r = 0; r < m; r++){
                for(int c = 0; c < n; c++){
                    if(board[r][c] == '1')
                        board[r][c] = 'O';
                }
            }
        }else{
            for(int r = 0; r < m; r++){
                for(int c = 0; c < n; c++){
                    if(board[r][c] == '1')
                        board[r][c] = 'X';
                }
            }
        }
    }

    private boolean dfs(char[][] board, boolean[][] seen, int r, int c) {
        int m = board.length;
        boolean flag = false;
        int n = board[0].length;
        if(r < 0 || c < 0 || r >= m || c >= n)
            return flag;
        //别漏了判断起点！
        if(c == 0 || c == n - 1 || r == 0 || r == m - 1) {
            flag = true;
        }
        int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
        for(int[] d: dir){
            int nr = r + d[0];
            int nc = c + d[1];
            if(nr < 0 || nc < 0 || nr >= m || nc >= n || board[nr][nc] == 'X' || seen[nr][nc])
                continue;
            seen[nr][nc] = true;
            if(nc == 0 || nc == n - 1 || nr == 0 || nr == m - 1) {
                flag = true;
            }
            board[nr][nc] = '1'; //把符合条件的都改成1
            boolean tmp = dfs(board, seen, nr, nc);
            if(tmp) {
                flag = true;
            }
        }
        return flag;
    }

    //2 github
    public void solve2(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        //高 实在是高 直接从边际开始找O 将连接边际的O全部改成T
        for (int i = 0; i < m; i++) {
            dfs2(board, i, 0);
            dfs2(board, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs2(board, 0, i);
            dfs2(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs2(char[][] board, int r, int c) {
        int m = board.length;
        int n = board[0].length;
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'T';
        for (int[] d : direction) {
            dfs2(board, r + d[0], c + d[1]);
        }
    }
}

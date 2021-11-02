/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc79
 * @Description: [79. Word Search 在矩阵中寻找字符串]
 * @Author: [clh]
 * @Date: 2021/11/1 15:38
 **/
public class Lc79 {
    //debug半天终于通过！
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        int m = board.length;
        int n = board[0].length;
//        boolean flag = false;
        boolean[][] seen = new boolean[m][n];
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(board[r][c] != word.charAt(0)) {
                    continue;
                }
//                seen[r][c] = true;
                if(dfs(board, word, r, c, 1, seen))
                    return true;
//                seen[r][c] = false;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int cnt, boolean[][] seen) {
        int m = board.length;
        int n = board[0].length;
        int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
//        if(board[r][c] != word.charAt(cnt))
//            return false;
//        cnt++;
        seen[r][c] = true;
        if(cnt == word.length())
            return true;
        for(int[] d: dir){
            int nr = r + d[0];
            int nc = c + d[1];
            if(nc < 0 || nr < 0 || nr >= m || nc >= n || board[nr][nc] != word.charAt(cnt) || seen[nr][nc])
                continue;
            seen[nr][nc] = true;
            boolean tmp = dfs(board, word, nr, nc, cnt+1, seen);
            //这个很重要！
            if(tmp){
                return true;
            }
        }
        seen[r][c] = false;
        return false;
    }

    // 2 github
    public boolean exist2(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] hasVisited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (backtracking(0, r, c, hasVisited, board, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtracking(int curLen, int r, int c, boolean[][] visited, final char[][] board, final String word) {
        if (curLen == word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n
                || board[r][c] != word.charAt(curLen) || visited[r][c]) {

            return false;
        }

        visited[r][c] = true;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : direction) {
            if (backtracking(curLen + 1, r + d[0], c + d[1], visited, board, word)) {
                return true;
            }
        }

        visited[r][c] = false;

        return false;
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc37
 * @Description: [37. Sudoku Solver 数独]
 * @Author: [clh]
 * @Date: 2021/11/2 21:53
 **/
public class Lc37 {
    private boolean[][] rowsUsed = new boolean[9][10];
    private boolean[][] colsUsed = new boolean[9][10];
    private boolean[][] cubesUsed = new boolean[9][10];
    private char[][] board;
    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                rowsUsed[i][num] = true;
                colsUsed[j][num] = true;
                cubesUsed[cubeNum(i, j)][num] = true;
            }
        backtracking(0, 0);
    }
    private boolean backtracking(int row, int col) {
        //找到为.的那个格子开始填
        while (row < 9 && board[row][col] != '.') {
            //col = 8 说明要到下一行了
            row = col == 8 ? row + 1 : row;
            //col = 8 说明要到下一行第0列了
            col = col == 8 ? 0 : col + 1;
        }
        //说明遍历完了
        if (row == 9) {
            return true;
        }
        for (int num = 1; num <= 9; num++) {
            //只要有用过就不能用
            if (rowsUsed[row][num] || colsUsed[col][num] || cubesUsed[cubeNum(row, col)][num]) {
                continue;
            }
            //找到一个可以填的数 开始填
            rowsUsed[row][num] = colsUsed[col][num] = cubesUsed[cubeNum(row, col)][num] = true;
            //数字转成字符再填入
            board[row][col] = (char) (num + '0');
            if (backtracking(row, col)) {
                return true;
            }
            //回退
            board[row][col] = '.';
            rowsUsed[row][num] = colsUsed[col][num] = cubesUsed[cubeNum(row, col)][num] = false;
        }
        return false;
    }

    private int cubeNum(int i, int j) {
        //结合图像来理解
        int r = i / 3;
        int c = j / 3;
        return r * 3 + c;
    }
}

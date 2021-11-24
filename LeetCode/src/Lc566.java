/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc566
 * @Description: [566. Reshape the Matrix (Easy) 改变矩阵维度]
 * @Author: [clh]
 * @Date: 2021/11/24 19:53
 **/
public class Lc566 {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int[][] ans = new int[r][c];
        int m = mat.length, n = mat[0].length;
        if(r == m && c == n || (r*c != m*n))
            return mat;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int num = i*n + j;
                ans[num/c][num%c] = mat[i][j];
            }
        }
        return ans;
    }

    //2 github
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }
        int[][] reshapedNums = new int[r][c];
        int index = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshapedNums[i][j] = nums[index / n][index % n];
                index++;
            }
        }
        return reshapedNums;
    }
}

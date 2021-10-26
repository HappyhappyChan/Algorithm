import java.util.ArrayList;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz19
 * @Description: [顺时针打印矩阵]
 * @Author: [clh]
 * @Date: 2021/10/13 21:18
 **/
public class Jz19 {
    //mine
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int row = matrix.length;
        if(row == 0) return new ArrayList();
        int col = matrix[0].length;
        if(col == 0) return new ArrayList();
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int up = 0;
        int down = row - 1;
        int left = 0;
        int right = col - 1;
        while(up <= down && left <= right){
            for(int i = left; i <= right; i++){
                ret.add(matrix[up][i]);
            }
            up++;
            //不加break就会错！因为有可能转不完一圈就违反了
            if(up > down) break;
            for(int i = up; i <= down; i++){
                ret.add(matrix[i][right]);
            }
            right--;
            if(left > right) break;
            for(int i = right; i >= left; i--){
                ret.add(matrix[down][i]);
            }
            down--;
            if(up > down) break;
            for(int i = down; i >= up; i--){
                ret.add(matrix[i][left]);
            }
            left++;
            if(left > right) break;
        }
        return ret;
    }

    //solution from github
    //思路差不多 但是这样写占用内存少
    public ArrayList<Integer> printMatrix2(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            // 上
            for (int i = c1; i <= c2; i++)
                ret.add(matrix[r1][i]);
            // 右
            for (int i = r1 + 1; i <= r2; i++)
                ret.add(matrix[i][c2]);
            if (r1 != r2)
                // 下
                for (int i = c2 - 1; i >= c1; i--)
                    ret.add(matrix[r2][i]);
            if (c1 != c2)
                // 左
                for (int i = r2 - 1; i > r1; i--)
                    ret.add(matrix[i][c1]);
            r1++; r2--; c1++; c2--;
        }
        return ret;
    }

}

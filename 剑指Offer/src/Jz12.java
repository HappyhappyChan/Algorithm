/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz12
 * @Description: [矩阵中的路径]
 * @Author: [clh]
 * @Date: 2021/10/23 10:15
 **/
public class Jz12 {

    //newcoder
    public boolean hasPath (String matrix, int rows, int cols, String str) {
        // write code here
        //初始化一个标志位数组
        boolean[] visited = new boolean[rows*cols];
        char[] mat = matrix.toCharArray();
        char[] strs = str.toCharArray();
        //遍历寻找最开始的一个匹配
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(judge(mat, strs, visited, rows, cols, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    //k代表当前匹配到第几个了
    public boolean judge(char[] mat, char[] strs, boolean[] visited, int rows, int cols, int i, int j, int k){
        //计算将要访问的字母的下标
        int is_visited_pos = i * cols + j;
        if(i<0||i>=rows||j<0||j>=cols||visited[is_visited_pos]==true||mat[is_visited_pos]!=strs[k]){
            return false;
        }
        //如果第k个满足上面的条件，并且k等于要匹配的长度
        if(k == strs.length - 1) return true;
        //如果符合以上的条件即把当前位置标记为已经访问
        visited[is_visited_pos] = true;
        //上下左右搜索
        if(judge(mat,strs,visited,rows,cols,i-1,j,k+1)||
                judge(mat,strs,visited,rows,cols,i+1,j,k+1)||
                judge(mat,strs,visited,rows,cols,i,j-1,k+1)||
                judge(mat,strs,visited,rows,cols,i,j+1,k+1)
        )
            //通过is_visited_pos寻找到了
            return true;
        //如果没有通过is_visited_pos寻找到，说明is_visited_pos不通
        //将其重新置为false
        visited[is_visited_pos] = false;
        return false;
    }
}

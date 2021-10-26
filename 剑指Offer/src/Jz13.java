import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz13
 * @Description: [机器人的运动范围]
 * @Author: [clh]
 * @Date: 2021/10/23 11:20
 **/
public class Jz13 {
    //solution 1 newcoder
    //dfs
    public int movingCount(int threshold, int rows, int cols){
        //visited记录格子是否被访问过
        boolean[][] visited = new boolean[rows][cols];
        return dfs(0, 0, rows, cols, threshold, visited);
    }

    public int dfs(int i, int j, int rows, int cols, int threshold, boolean[][] visited){
        if(i >= rows || j >= cols || threshold < sum(i, j) || visited[i][j])
            return 0;
        visited[i][j] = true;
        return 1 + dfs(i+1, j, rows, cols, threshold, visited)
                + dfs(i, j+1, rows, cols, threshold, visited);
    }

    private int sum(int i, int j) {
        int sum = 0;
        while(i != 0){
            sum += i % 10;
            i /= 10;
        }
        while(j != 0){
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }

    //solution 2 bfs
    //newcoder
    public int movingCount2(int threshold, int rows, int cols){
        boolean[][] visited = new boolean[rows][cols];
        int res = 0;
        //创建一个队列，保存的是访问到的格子坐标 是一个二维数组
        Queue<int[]> que = new LinkedList<>();
        //从左上角坐标[0,0]开始访问，add方式把坐标加入到队列队尾
        //二维数组初始化网上：int a[][]={{1,2,3},{4,5,6}};
        que.add(new int[]{0,0});
        while(!que.isEmpty()){
            int[] x = que.poll();
            int i = x[0], j = x[1];
            if(i >= rows || j >= cols || threshold < sum(i, j) || visited[i][j])
                continue;
            visited[i][j] = true;
            res++;
            que.add(new int[]{i+1, j});
            que.add(new int[]{i, j+1});
        }
        return res;
    }
}

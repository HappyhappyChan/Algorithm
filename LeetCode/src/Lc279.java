import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc279
 * @Description: [279. Perfect Squares 组成整数的最小平方数数量]
 * @Author: [clh]
 * @Date: 2021/10/30 17:43
 **/
public class Lc279 {
    //1 github
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> que = new LinkedList<Integer>();
        boolean[] marked = new boolean[n + 1];
        que.add(n);
        marked[n] = true;
        int level = 0;
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            while(size -- > 0){
                int cur = que.poll();
                for(int s : squares){
                    //只有两个结点之间的差是完全平方数才加进去
                    //这里的两个结点分别是cur next
                    int next = cur - s;
                    if(next < 0)
                        break;
                    if(next == 0)
                        return level;
                    //为啥啊
                    //因为如果一个数字出现过，有可能在同层前面已经遍历，那同一层的话最终得到的level也一样 不用再算
                    //如果是上一层出现，那肯定是按照上一层来计算level 使得level更小 所以也没必要计算这一个
                    if(marked[next]){
                        continue;
                    }
                    marked[next] = true;
                    que.add(next);
                }
            }
        }
        return n;
    }
    //生产小于n的平方数序列 1, 4, 9, 16, 25...
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<Integer>();
        int square = 1;
        int diff = 3;
        while(square <= n){
            squares.add(square);
            //只能说是数学规律了
            //1 4 9 16
            square += diff;
            //3 5 7 9
            diff += 2;
        }
        return squares;
    }


}

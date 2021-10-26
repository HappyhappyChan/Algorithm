/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz62
 * @Description: [圆圈中最后剩下的数]
 * @Author: [clh]
 * @Date: 2021/10/25 10:18
 **/
public class Jz62 {
    // 2 github 递归
    public int LastRemaining_Solution(int n, int m) {
        if(n == 0)
            return -1;
        if(n == 1)
            return 0;
        return (LastRemaining_Solution(n-1, m) + m) % n;
    }

    // 3 nowcoder 迭代
    public int solution3(int n, int m){
        if(n <= 0) return -1;
        int ind = 0;
        for(int i = 2; i <= n; i++){
            ind = (ind + m) % i;
        }
        return ind;
    }

    // 1 模拟 哔哩哔哩
    public int solution1(int n, int m){
        if(n == 0 || m == 0)
            return -1;
        //用来记录是否被移除
        boolean[] vis = new boolean[n];
        //记录当前已经移除的人数总和
        int sum = 0;
        //记录某一次循环的过程中已经计数人数的个数
        int res = 0;
        int ind = 0;
        while(sum < n - 1){
            res = 0;
            //如果当前的节点是已经被移除的 那要往下走一个
            while(vis[ind]){
                ind = (ind + 1) % n;
            }
            //一直报数直到m
            while(res < m){
                //只要这个数没被移出
                if(!vis[ind]){
                    res++;
                }
                //继续往下走
                ind = (ind + 1) % n;
            }
            //退出循环 说明已经报数到m
            //从1开始报数，ind 是第m+1个， 要将ind的前一个标记为true
            vis[(ind + n - 1) % n] = true;
            sum++;
        }
        while(vis[ind]){
            ind = (ind + 1) % n;
        }
        return ind;
    }


}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz66
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/10/24 18:19
 **/
public class Jz66 {
    public int[] multiply(int[] A) {
        if(A == null || A.length < 2)
            return null;
        int[] b = new int[A.length];
        for(int i = 0; i < b.length; i++){
            int tmp = 1;
            for(int j = 0; j < b.length; j++){
                if(i == j)
                    continue;
                tmp *= A[j];
            }
            b[i] = tmp;
        }
        return b;
    }

    //solution 2 nowcoder 表格分区，双向遍历
    //结合哔哩哔哩的视频看更容易懂
    public int[] multiply2(int[] A) {
        if(A == null || A.length == 0) {
            return new int[0];
        }
        int[] B = new int[A.length];
        // 初始化数组
        B[0] = 1;
        // 先算左下角部分，此时B[i] = A[0]到A[i-1]的乘积
        for(int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        // 从右下角开始，保存每次循环内累乘的右上部分的结果
        int temp = 1;
        // 再算右上角部分，此时temp = A[i-1]到A[n]的乘积,B[i]=A[0]到A[n-1]乘积
        for(int i = A.length - 1; i >= 0; i--) {
            // 两部分连接，从B[n-1]到B[0]连接上面的乘积结果
            B[i] *= temp;
            // 右下角开始，保存每次循环内累乘的右上部分的结果
            temp *= A[i];
        }
        return B;
    }

    //solution 3 nowcoder dp
    //结合ipad笔记想懂
    public int[] multiply3(int[] A) {
        if(A == null || A.length == 0) {
            return new int[0];
        }
        // dp[i] 表示A[0]到A[i]所对应的乘积
        int[] dp = new int[A.length];
        // 状态初始化
        dp[0] = A[1];
        dp[1] = A[0];
        // temp 相当于保存左下角的乘积
        int temp = dp[0] * dp[1];
        // 从dp[2]开始动态规划更新
        for(int i=2; i<A.length; i++){
            // 1 则跳过计算，乘积结果也不变
            if(A[i]!=1){
                // 实现 dp = dp * A[i];
                // 相当于在表格分区中，dp[j]对A[i]列累乘
                for(int j=0; j<i; j++){
                    //我觉得这里才是拼接的地方
                    //这里让上一次没有拼接到右边的 如果<i 说明还要拼接右边
                    //所以才会*A[i]
                    dp[j] *= A[i];
                }
            }
            // 拼接乘积左下和右上部分的乘积
            //这里相当于拼左下角
            dp[i] = temp;
            // temp 相当于左下角的乘积
            temp *= A[i];
        }
        return dp;
    }
}

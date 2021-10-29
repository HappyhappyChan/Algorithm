/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc53
 * @Description: [53. Maximum Subarray 子数组最大的和]
 * @Author: [clh]
 * @Date: 2021/10/29 10:26
 **/
public class Lc53 {
    //我用dp
    public int maxSubArray(int[] A) {
        if(A == null) return Integer.MIN_VALUE;
        int pre = A[0]; //用来记录f(n-1)
        int max = A[0]; //用来记录最大的sum
        for(int i = 1; i < A.length; i++){
            pre = A[i] + (pre > 0 ? pre : 0);
            max = max > pre ? max : pre;
        }
        return max;
    }

    //2 我好久之前写的
    public int maxSubArray2(int[] A) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < 0)
                sum = A[i];
            else
                sum += A[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }
}

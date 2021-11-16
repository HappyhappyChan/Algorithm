import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc628
 * @Description: [628. Maximum Product of Three Numbers 找出数组中的乘积最大的三个数]
 * @Author: [clh]
 * @Date: 2021/11/16 15:48
 **/
public class Lc628 {
    //终于通过了
    public int maximumProduct(int[] nums) {
        //忽略了两个负数*一个正数 [-100, -98, -1, 2, 3, 4]
        if(nums == null || nums.length < 3)
            return 0;
        int n = nums.length;
        Arrays.sort(nums);
        //[1,2,3] [-1,0,3] [-2,-1,0]
        //[-100,-98,0,1,2,3]
        //全都是正 or 全都是负
        if(nums[n-1]*nums[n-3] >= 0 && nums[n-1]*nums[0] >= 0) {
            return nums[n-1]*nums[n-2]*nums[n-3];
        }
        int res1 = nums[n-1]*nums[n-2]*nums[n-3];
        //2个负数+1个正数
        //有正有负
        if(nums[0]*nums[1] >= 0){
            int res2 = nums[n-1]*nums[0]*nums[1];
            return res1 > res2 ? res1 : res2;
        }
        return res1;
        /*if(n >= 4){
            int res2 = nums[n-2]*nums[n-3]*nums[n-4];
            return res1 > res2 ? res1 : res2;
        }else{
            return res1;
        }*/
    }

    //暴力法
    //TLE
    public int maximumProduct2(int[] nums){
        if(nums == null || nums.length < 3)
            return 0;
        int res = Integer.MIN_VALUE;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j != i && j < n; j++){
                for(int k = 0; k != j && k != i && k < n; k++){
                    res = Math.max(nums[i]*nums[j]*nums[k], res);
                }
            }
        }
        return res;
    }

    //优化上面代码
    //结果变成了Memory Limit Exceeded
    public int maximumProduct3(int[] nums){
        if(nums == null || nums.length < 3)
            return 0;
        int res = Integer.MIN_VALUE;
        int n = nums.length;
        int[][][] memo = new int[n][n][n];
        for(int[][] x: memo){
            for(int[] y: x){
                Arrays.fill(y, res);
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j != i && j < n; j++){
                for(int k = 0; k != j && k != i && k < n; k++){
                    if(memo[i][j][k] > res)
                        continue;
                    memo[i][j][k] = nums[i]*nums[j]*nums[k];
                }
            }
        }
        for(int[][] x: memo){
            for(int[] y: x){
                for(int z: y){
                    res = Math.max(res, z);
                }
            }
        }
        return res;
    }

    //leetcode
    public int maximumProduct4(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    //leetcode
    public int maximumProduct5(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for(int n : nums){
            if(n <= min1){
                min2 = min1;
                min1 = n;
            }else if(n <= min2){
                min2 = n;
            }
            if(n >= max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            }else if(n >= max2){
                max3 = max2;
                max2 = n;
            }else if(n >= max3){
                max3 = n;
            }
        }
        return Math.max(min1*min2*max1, max1*max2*max3);
    }
}

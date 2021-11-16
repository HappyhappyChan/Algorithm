import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc238
 * @Description: [238. Product of Array Except Self 乘积数组]
 * @Author: [clh]
 * @Date: 2021/11/16 15:25
 **/
public class Lc238 {
    //1 全部乘起来然后挨个除
    public int[] productExceptSelf(int[] nums) {
        int total = 1;
        //小心有0啊
        int isZero = 1;
        int cnt = 0;
        int[] res = new int[nums.length];
        for(int x: nums){
            if(x != 0)
                total *= x;
            else {
                isZero = 0;
                cnt++;
            }
        }
        //[0,4,0] [1,0]
        //[0,0]
        //[0,0,6,6]
        if(cnt == nums.length || (cnt >= 2 && nums.length > 2)){
            Arrays.fill(res, 0);
            return res;
        }

        if(isZero != 0){
            for(int i = 0; i < nums.length; i++){
                res[i] = total / nums[i];
            }
        }else{
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != 0){
                    res[i] = 0;
                }else{
                    res[i] = total;
                }
            }
        }

        return res;
    }

    //2 github 题目要求不能用除法
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] products = new int[n];
        Arrays.fill(products, 1);
        int left = 1;
        for (int i = 1; i < n; i++) {
            left *= nums[i - 1];
            products[i] *= left;
        }
        int right = 1;
        for (int i = n - 2; i >= 0; i--) {
            right *= nums[i + 1];
            products[i] *= right;
        }
        return products;
    }
}

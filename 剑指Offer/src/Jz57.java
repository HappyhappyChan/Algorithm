import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz42
 * @Description: [和为S的两个数字,在有序数组中找出两个数，使得和为给定的数 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。]
 * @Author: [clh]
 * @Date: 2021/10/10 22:41
 **/
public class Jz57 {
    public ArrayList<Integer> FindNumbersWithSum(int[] nums, int target){
        //边界条件
        if(nums == null || nums.length <= 1){
            return new ArrayList<>();
        }

        //设定两个指针 从两边开始遍历
        int i = 0, j = nums.length - 1;
        while(i < j){
            //最外层的乘积最小，别被题目误导
            //假设m小于n (m+1)(n-1)>=mn
            int cur = nums[i] + nums[j];
            if(cur == target)
                return new ArrayList<>(Arrays.asList(nums[i],nums[j]));
            if(cur > target){
                j--;
            }else{
                i++;
            }
        }
        return new ArrayList<>();
    }

}

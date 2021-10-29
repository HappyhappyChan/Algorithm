/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc153
 * @Description: [153. Find Minimum in Rotated Sorted Array 旋转数组的最小数字]
 * @Author: [clh]
 * @Date: 2021/10/29 21:03
 **/
public class Lc153 {
    //1 二分法 看ipad上面笔记 也是分情况讨论
    //代码有误啊
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        if(h == 0) return nums[0];
        if(h == 1) return Math.min(nums[0],nums[1]);
        int m = l + (h - l)/2;
        while(l < h){
            m = l + (h - l)/2;
            if(nums[m] > nums[m+1]){
                if(m > 0 && nums[m] > nums[m-1]){
                    //一开始写成return m+1 怎么都过不去 hhh
                    return nums[m+1];
                }else{
                    l = m + 1;
                }
            }
            if(nums[m] < nums[m+1]){
                if(m > 0 && nums[m] < nums[m-1]){
                    return nums[m];
                }else{
                    //当 m-1 < m < m+1 的时候 有可能是123 在左边也有可能是 2341在右边
                    //这个地方有误
                    l = m + 1;
                }
            }
        }
        return nums[l];
    }

    //2 github
    //没想懂
    public int findMin2(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] <= nums[h]) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }

}

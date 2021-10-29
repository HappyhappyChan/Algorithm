/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc34
 * @Description: [34. Find First and Last Position of Element in Sorted Array 查找区间]
 * @Author: [clh]
 * @Date: 2021/10/29 22:01
 **/
public class Lc34 {
    //搞了好久……
    //终于通过了！要记住模板
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[2];
        int l = 0;
        int h = nums.length;
        int m = l + (h - l)/2;
        //先找最左边
        while(l < h){
            m = l + (h - l)/2;
            if(nums[m] > target){
                h = m - 1;
            }else{
                l = m;
            }
        }
        //防止越界
        if(h >= 0 && h < nums.length && nums[h] == target){
            ret[0] = h;
        }else{
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }
        //再找最右边
        l = h;
        //判断越界条件要放在前面！
        while(l < nums.length && nums[l] == target){
            l++;
        }
        ret[1] = l - 1;
        return ret;
    }

    //2 github
    public int[] searchRange2(int[] nums, int target) {
        int first = findFirst(nums, target);
        //因为是按顺序的 所以不用再重写找最右的 而是找target+1
        int last = findFirst(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }

    private int findFirst(int[] nums, int target) {
        int l = 0, h = nums.length; // 注意 h 的初始值
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}

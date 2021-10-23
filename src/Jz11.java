/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz11
 * @Description: [旋转数组的最小数字]
 * @Author: [clh]
 * @Date: 2021/10/22 21:21
 **/
public class Jz11 {
    //暴力解法
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0)
            return 0;
        //这里不能设0 因为元素范围是1到10000
        //也可以改成array[1] 然后下面的for从1开始遍历
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++){
            min = Math.min(min, array[i]);
        }
        return min;
    }

    //solution 2 bilibili
    //3 4 5 1 2
    //利用二分查找
    public int solution2(int[] array){
        if(array.length <= 0)
            return 0;
        int l = 0;
        int r = array.length - 1;
        //因为l=r-1的时候就要跳出循环了
        while(l < r - 1){
            //其实就是/2 不过位运算更快一些
            int mid = (l + r) >> 1;
            if(array[mid] >= array[l]){
                l = mid; //说明mid在左部
            }else if(array[mid] <= array[r]){
                //这里要用else if 不是两个if!
                //因为防止 1 0 1 1 这种情况
                //额 还是不行 哔哩哔哩的代码有误 1 0 1 1 这种情况还是会报错
                r = mid; //说明mid在右部
            }
        }
        return array[r];
    }

    //solution 3 from newcoder
    public int minNumberInRotateArray3(int[] nums) {
        if (nums.length == 0)
            return 0;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            //其实两种方法都可以 我也不懂为什么github上要选l+h-l/2这种
//            int m = (l+h) >> 1;
            int m = l + (h - l) / 2;
            if (nums[l] == nums[m] && nums[m] == nums[h])
                return minNumber(nums, l, h);
            else if (nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }

    private int minNumber(int[] nums, int l, int h) {
        for (int i = l; i < h; i++)
            if (nums[i] > nums[i + 1])
                return nums[i + 1];
        return nums[l];
    }

    //solution 3 newcoder
    public int solution3(int[] arr){
        if(arr.length == 0)
            return 0;
        int i = 0, j = arr.length - 1;
        while(i < j){
            int m = (i + j) / 2;
            if(arr[m] < arr[j]){
                j = m;
            }else if(arr[m] > arr[j]){
                i = m + 1;
            }else{
                // 缩小范围继续判断
                j--;
            }
        }
        return arr[i];
    }

}

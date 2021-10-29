/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc75
 * @Description: [75. Sort Colors 按颜色进行排序]
 * @Author: [clh]
 * @Date: 2021/10/27 21:04
 **/
public class Lc75 {
    // 1 我写的 快速排序
    public void sortColors(int[] nums) {
        if(nums.length < 2) return;
        int l = 0, r = nums.length - 1;
        partition(nums, l, r);
    }

    private void partition(int[] nums, int l, int r) {
        //这个很重要！！！
        if(l > r)
            return;
        int i = l;
        int j = r;
        while(i < j){
            while(nums[j] >= nums[l] && j > l){
                j--;
            }
            while(nums[i] <= nums[l] && i < j){
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        partition(nums, l, j - 1);
        partition(nums, j + 1, r);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 2 leetcode-cn
    //因为只有3种颜色
    //link md里面指的LeetCode-cn讲解
    //one-pass 一次遍历
    public void sortColors2(int[] nums) {
        if(nums.length < 2) return;
        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
        // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
        // 所以下面遍历到 0 的时候，先交换，再加
        int zero = 0;

        // 为了保证初始化的时候 [two, len - 1] 为空，设置 two = len
        // 所以下面遍历到 2 的时候，先减，再交换
        int two = nums.length;
        int i = 0;
        // 当 i == two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件是 i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }


    // 3 github
    //one -pass
    public void sortColors3(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0, zero] = 0
        // all in (zero, i) = 1
        // all in (two, len - 1] = 2

        // 为了保证初始化的时候 [0, zero] 为空，设置 zero = -1，
        // 所以下面遍历到 0 的时候，先加，再交换
        int zero = -1;

        // 为了保证初始化的时候 (two, len - 1] 为空，设置 two = len - 1
        // 所以下面遍历到 2 的时候，先交换，再减
        int two = len - 1;
        int i = 0;
        // 当 i == two 的时候，还有一个元素还没有看，
        // 因此，循环可以继续的条件是 i <= two
        while (i <= two) {
            if (nums[i] == 0) {
                zero++;
                swap(nums, i, zero);
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, two);
                two--;
            }
        }
    }

    //4 leetcode
    // 2 pass 2次遍历
    public void sortColors4(int[] nums) {
        // 2-pass
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {count0++;}
            if (nums[i] == 1) {count1++;}
            if (nums[i] == 2) {count2++;}
        }
        for(int i = 0; i < nums.length; i++) {
            if (i < count0) {nums[i] = 0;}
            else if (i < count0 + count1) {nums[i] = 1;}
            else {nums[i] = 2;}
        }
    }
}

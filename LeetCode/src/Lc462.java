import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc462
 * @Description: [462. Minimum Moves to Equal Array Elements II (Medium) 改变数组元素使所有的数组元素都相等]
 * @Author: [clh]
 * @Date: 2021/11/16 10:33
 **/
public class Lc462 {
    //1 我先尝试用平均数
    //平均数不行 [1,0,0,8,6] ans = 14 my = 16
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int avg = sum / n;
        int res = 0;
        for(int x : nums){
            res += x - avg > 0 ? x - avg : avg - x;
        }
        return res;
    }

    //2 中位数
    public int minMoves22(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length-1;
        int mid = nums[n/2];
        int res = 0;
        for(int x: nums){
            res += x - mid > 0 ? x - mid : mid - x;
        }
        return res;
    }

    //3 github
    //时间复杂度：O(NlogN)
    public int minMoves23(int[] nums) {
        Arrays.sort(nums);
        int move = 0;
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            move += nums[h] - nums[l];
            l++;
            h--;
        }
        return move;
    }

    //4 快速选择
    public int minMoves24(int[] nums) {
        int move = 0;
        int median = findKthSmallest(nums, nums.length / 2);
        for (int num : nums) {
            move += Math.abs(num - median);
        }
        return move;
    }

    private int findKthSmallest(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            }
            if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < h) ;
            while (nums[--j] > nums[l] && j > l) ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

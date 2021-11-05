import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc300
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/11/4 21:43
 **/
public class Lc300 {
    //1 我自己写的
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1; i < len; i++){
            //因为可以不连续抽出元素构成subsequence
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }else{
                    dp[i] = Math.max(dp[i], 1);
                }
            }
        }
        int max = 0;
        for(int x: dp){
            max = max > x? max : x;
        }
        return max;
    }

    //2 github
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    //3 github 二分查找
    public static int lengthOfLIS3(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int num : nums) {
            int index = binarySearch(tails, len, num);
            tails[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private static int binarySearch(int[] tails, int len, int key) {
        int l = 0, h = len;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (tails[mid] == key) {
                return mid;
            } else if (tails[mid] > key) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args){
        int[] nums = {4,3,6,5};
        int res = lengthOfLIS3(nums);
    }
}

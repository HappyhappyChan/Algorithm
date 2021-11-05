/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc376
 * @Description: [376. Wiggle Subsequence 最长摆动子序列]
 * @Author: [clh]
 * @Date: 2021/11/5 10:07
 **/
public class Lc376 {
    //我自己写的 有个很长的案例过不了……找不到bug
    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        if(len == 1) return 1;
        if(len == 2 && nums[0] != nums[1]) return 2;
        if(len == 2 && nums[0] == nums[1]) return 1;
        int[] dp = new int[len];
        dp[0] = 1;
        dp[1] = nums[1] > nums[0] ? 2 : (nums[1] < nums[0] ? -2 : 0);
        //防止 123456
        int ret = Math.max(1, Math.abs(dp[1]));
        for(int i = 2; i < len; i++){
            int max = 1;
            for(int j = 0; j < i; j++){
                int diff = nums[i] - nums[j];
                if(diff * dp[j] > 0){
                    continue;
                }
                if(diff == 0 ){
                    if(Math.abs(dp[j]) > Math.abs(max))
                        max = dp[j];
                    continue;
                }
                //1 1 7 4 9 2 5
                if(dp[j] == 0  && diff != 0){
                    if(Math.abs(max) < 2)
                        if(diff < 0) {
                            max = -2;
                        }else{
                            max = 2;
                        }
                    continue;
                }
                if(Math.abs(max) < Math.abs(dp[j])+1){
                    max = diff < 0 ? -(Math.abs(dp[j])+1):Math.abs(dp[j])+1;
                }
                /*max = Math.max(max, Math.abs(dp[j])+1);
                if(diff < 0) max = -max;*/
            }
            dp[i] = max;
            ret = Math.max(ret, Math.abs(max));
        }
        return ret;
    }

    //2 github
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    // 3 leetcode brute force
    public int wiggleMaxLength3(int[] nums) {
        if(nums.length < 2)
            return nums.length;
        return 1 + Math.max(calculate(nums, 0, true), calculate(nums, 0, false));
    }

    private int calculate(int[] nums, int index, boolean isUp) {
        int maxcnt = 0;
        for(int i = index + 1; i < nums.length; i++){
            if((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index]))
                maxcnt = Math.max(maxcnt, 1 + calculate(nums, i, !isUp));
        }
        return maxcnt;
    }

    //4 leetcode dynamic program
    public int wiggleMaxLength4(int[] nums) {
        if(nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    up[i] = Math.max(up[i], down[j] + 1);
                }else if(nums[i] < nums[j]){
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length-1]);
    }

    //5 leetcode linear dynamic programming
    public int wiggleMaxLength5(int[] nums) {
        if(nums.length < 2) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i-1]){
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            }else if(nums[i] < nums[i - 1]){
                down[i] = up[i-1]+1;
                up[i] = up[i-1];
            }else{
                down[i] = down[i - 1];
                up[i] = up[i-1];
            }
        }
        return Math.max(up[nums.length - 1], down[nums.length-1]);
    }

    //6 leetcode space-optimization dynamic programming
    public int wiggleMaxLength6(int[] nums){
        if(nums.length < 2) return nums.length;
        int up = 1, down = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i-1])
                up = down + 1;
            else if(nums[i] < nums[i-1])
                down = up + 1;
        }
        return Math.max(up, down);
    }
}

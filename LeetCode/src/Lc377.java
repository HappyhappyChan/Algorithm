/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc377
 * @Description: [377. Combination Sum IV (Medium) ]
 * @Author: [clh]
 * @Date: 2021/11/9 10:24
 **/
public class Lc377 {
    //1 leetcode-cn
    //完全背包+考虑排列顺序
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target+1];
        //dp[i]表示和为i的num组合有dp[i]种
        dp[0] = 1;
        for(int i = 1; i <= target; i++){
            for(int j = 0; j < n ; j++){
                if(nums[j] <= i){
                    dp[i] = dp[i] + dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}

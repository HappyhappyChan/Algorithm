/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc485
 * @Description: [485. Max Consecutive Ones (Easy) 找出数组中最长的连续 1]
 * @Author: [clh]
 * @Date: 2021/11/24 21:22
 **/
public class Lc485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int ans = 0;
        int cnt = 0;
        for(int x: nums){
            if(x == 1)
                cnt++;
            else{
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        //[1,1,0,1,1,1]
        return Math.max(ans, cnt);
    }

    //2 github
    public int findMaxConsecutiveOnes2(int[] nums) {
        int max = 0, cur = 0;
        for (int x : nums) {
            cur = x == 0 ? 0 : cur + 1;
            max = Math.max(max, cur);
        }
        return max;
    }
}

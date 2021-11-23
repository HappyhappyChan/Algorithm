import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc128
 * @Description: [128. Longest Consecutive Sequence (Hard) 最长连续序列]
 * @Author: [clh]
 * @Date: 2021/11/23 19:04
 **/
public class Lc128 {
    // 5.00% 5.63%
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int ans = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int cnt = 1;
            set.add(nums[i]);
            int j = 1;
            while(set.contains(nums[i]-j)){
                cnt++;
                j++;
            }
            j = 1;
            while(set.contains(nums[i]+j)){
                cnt++;
                j++;
            }
            ans = Math.max(cnt, ans);
        }
        return ans;
    }

    //TLE
    public int longestConsecutive2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int x : nums){
            set.add(x);
        }
        int ans = 0;
//        int n = set.size();
        for(int v : set ){
            int cnt = 1;
            while(set.contains(v + cnt)){
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    //我优化后的
    public int longestConsecutive3(int[] nums){
        if(nums == null || nums.length < 1)
            return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int x: nums){
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        Arrays.sort(nums);
        int i = 0;
        int n = nums.length;
        int ans = 1;
        while(i < n){
            int cnt = 1;
            int carry = 0;
            while(map.containsKey(nums[i]+cnt)){
                carry += map.get(nums[i]+cnt);
                cnt++;
            }
            ans = Math.max(ans, cnt);
            i += carry + 1;
        }
        return ans;
    }

    //4 leetcode brute force
    private boolean arrayContains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }

        return false;
    }
    public int longestConsecutive4(int[] nums) {
        int longestStreak = 0;

        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;

            while (arrayContains(nums, currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }

    //5 leetcode sorting
    //好强！
    public int longestConsecutive5(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }

    //6 leetcode hashset and intelligent sequence building
    public int longestConsecutive6(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            //妙啊 通过这种方式跳过前面遍历过的
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

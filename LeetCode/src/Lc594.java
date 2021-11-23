import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc594
 * @Description: [594. Longest Harmonious Subsequence (Easy) 最长和谐序列]
 * @Author: [clh]
 * @Date: 2021/11/23 16:42
 **/
public class Lc594 {
    //1 有bug
    public int findLHS(int[] nums) {
        if(nums == null || nums.length < 2)
            return 0;
        int min;
        int max;
        int cnt;
        int ans = 0;
        int n = nums.length;
        for(int i = 0; i < n-1; i++){
            min = nums[i];
            max = nums[i];
            cnt = 1;
            for(int j = i+1; j < n; j++){
                if(nums[j] > max && nums[j] > min + 1)
                    continue;
                if(nums[j] < min && nums[j] < max - 1)
                    continue;
                //[2,2,3,1,1]
                //这种情况3先出现 就会变成找=2/3的 但实际上应该找=2/1的
                if(nums[j] == min + 1){
                    cnt++;
                    max = nums[j];
                }else if(nums[j] == max - 1){
                    cnt++;
                    min = nums[j];
                }else if(nums[j] == min || nums[j] == max){
                    cnt++;
                }
            }
            if(max == min){
                cnt = 0;
            }else{
                ans = Math.max(ans, cnt);
            }
        }
        //[1,1,1]
        return ans > 1 ? ans : 0;
    }

    //2 github
    public int findLHS2(int[] nums) {
        Map<Integer, Integer> countForNum = new HashMap<>();
        for (int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : countForNum.keySet()) {
            if (countForNum.containsKey(num + 1)) {
                longest = Math.max(longest, countForNum.get(num + 1) + countForNum.get(num));
            }
        }
        return longest;
    }

    //3 leetcode brute force
    public int findLHS3(int[] nums){
        int res = 0;
        for(int i = 0; i < (1 << nums.length); i++){
            int cnt = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for(int j = 0; j < nums.length; j++){
                //1<<j 是将各个位置依次置为1 然后和i & 看是不是j这个位置上的
                if((i & ( 1 << j)) != 0){
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                    cnt++;
                }
            }
            if(max - min == 1)
                res = Math.max(res, cnt);
        }
        return res;
    }

    //4 leetcode-cn 排序
    public int findLHS4(int[] nums){
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        for(int end = 0; end < nums.length; end++){
            while(nums[end] - nums[begin] > 1){
                begin++;
            }
            if(nums[end] - nums[begin] == 1){
                res = Math.max(res, end - begin +1);
            }
        }
        return res;
    }

    //5 leetcode better brute force
    public int findLHS5(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i])
                    count++;
                else if (nums[j] + 1 == nums[i]) {
                    count++;
                    flag = true;
                }
            }
            if (flag)
                res = Math.max(count, res);
        }
        return res;
    }

    //6 leetcode in single loop
    public int findLHS6(int[] nums) {
        HashMap < Integer, Integer > map = new HashMap < > ();
        int res = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num + 1))
                res = Math.max(res, map.get(num) + map.get(num + 1));
            if (map.containsKey(num - 1))
                res = Math.max(res, map.get(num) + map.get(num - 1));
        }
        return res;
    }
}

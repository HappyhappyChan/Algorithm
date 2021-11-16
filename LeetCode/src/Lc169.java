import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc169
 * @Description: [169. Majority Element 数组中出现次数多于 n / 2 的元素]
 * @Author: [clh]
 * @Date: 2021/11/16 10:51
 **/
public class Lc169 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    //2 github Boyer-Mooer Majority Vote
    public int majorityElement2(int[] nums) {
        int cnt = 0, maj = nums[0];
        for(int num : nums){
            maj = (cnt == 0)? num : maj;
            cnt = (maj == num)? cnt+1 : cnt-1;
        }
        return maj;
    }

    //3 brute force
    public int majorityElement3(int[] nums) {
        int max = nums.length/2;
        for(int num: nums){
            int cnt = 0;
            for(int x : nums){
                if(x == num){
                    cnt++;
                }
                if(cnt > max){
                    return x;
                }
            }
        }
        return -1;
    }
}

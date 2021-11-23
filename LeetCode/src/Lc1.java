import java.util.Arrays;
import java.util.HashMap;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc1
 * @Description: [1. Two Sum (Easy) 数组中两个数的和为给定值]
 * @Author: [clh]
 * @Date: 2021/11/23 15:43
 **/
public class Lc1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ans = new int[2];
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int cur = nums[i];
            if(map.containsKey(target - cur)){
                ans[0] = i;
                ans[1] = map.get(target - cur);
                return ans;
            }
            map.put(cur, i);
        }
        //其实如果找不到应该 return null;
        return ans;
    }

    //2 双指针
    //有误啊 这样返回的是排序完的索引 不是排序前的索引
    //所以还是要引入hashmap来存储排序前的索引
    //这样的话就还不如我上面写的算法了
    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while(i < j){
            if(nums[i] + nums[j] < target){
                i++;
            }else if(nums[i] + nums[j] > target){
                j--;
            }else{
                return new int[]{i, j};
            }
        }
        return null;
    }
}

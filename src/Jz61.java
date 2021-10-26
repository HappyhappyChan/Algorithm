import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz61
 * @Description: [61 扑克牌顺子]
 * @Author: [clh]
 * @Date: 2021/10/26 15:00
 **/
public class Jz61 {
    //1 排序后查漏
    public boolean solution1(int[] nums){
        if(nums.length < 5)
            return false;
        Arrays.sort(nums);

        int cnt = 0;
        for(int x: nums){
            if(x == 0)
                cnt++;
        }

        for(int i = cnt; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1])
                return false;
            cnt -= nums[i + 1] - nums[i] - 1;
        }
        return cnt >= 0;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc90
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/11/2 20:11
 **/
public class Lc90 {
    //1 我自己写的
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        boolean[] seen = new boolean[nums.length];
        Arrays.sort(nums);
        for(int k = 1; k <= nums.length; k++){
            backtrack(ret, cur, nums, k, 0, seen);
        }
        //因为要包括空集
        ret.add(new ArrayList<>());
        return ret;
    }

    private void backtrack(List<List<Integer>> ret, List<Integer> cur, int[] nums, int k, int start, boolean[] seen) {
        if(k == 0){
            ret.add(new ArrayList<>(cur));
            return;
        }
        for(int i = start; i < nums.length; i++){
            if(i != 0 && nums[i] == nums[i-1] && !seen[i-1])
                continue;
            seen[i] = true;
            cur.add(nums[i]);
            backtrack(ret, cur, nums, k-1, i+1, seen);
            //不能少了这一步
            seen[i] = false;
            cur.remove(cur.size() -1 );
        }
    }
}

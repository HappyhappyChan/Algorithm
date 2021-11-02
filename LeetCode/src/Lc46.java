import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc46
 * @Description: [46. Permutations 排列]
 * @Author: [clh]
 * @Date: 2021/11/1 19:43
 **/
public class Lc46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return ret;
        List<Integer> tmp = new ArrayList<>();
        boolean[] seen = new boolean[nums.length];
        backtracking(nums, ret, tmp, seen);
        return ret;
    }

    private void backtracking(int[] nums, List<List<Integer>> ret, List<Integer> tmp, boolean[] seen) {
        if(tmp.size() == nums.length){
            //只有这样才可以 直接ret.add(tmp)会报错
            ret.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = 0; i < seen.length; i++){
            if(seen[i])
                continue;
            seen[i] = true;
            tmp.add(nums[i]);
            backtracking(nums, ret, tmp, seen);
            tmp.remove(tmp.size() - 1);
            seen[i] = false;
        }
    }
}

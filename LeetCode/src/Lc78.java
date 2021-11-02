import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc78
 * @Description: [78. Subsets 子集]
 * @Author: [clh]
 * @Date: 2021/11/2 16:28
 **/
public class Lc78 {
    // 1 我自己写的
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for(int k = 1; k <= nums.length; k++){
            backtrack(ret, cur, nums, k, 0);
        }
        //因为要包括空集
        ret.add(new ArrayList<>());
        return ret;
    }

    private void backtrack(List<List<Integer>> ret,
                           List<Integer> cur,
                           int[] nums,
                           int k, int start) {
        if(k == 0){
            ret.add(new ArrayList<>(cur));
            return;
        }

        for(int i = start; i < nums.length; i++){
            cur.add(nums[i]);
            backtrack(ret, cur, nums, k-1, i+1);
            cur.remove(cur.size() - 1);
        }
    }

    // 2 leetcode cascading
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }

    // 3 backtracking
    //leetcode和我写的和github写的思路一样 就不放了

    //4 Lexicographic (Binary Sorted) Subsets
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;

        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }

}

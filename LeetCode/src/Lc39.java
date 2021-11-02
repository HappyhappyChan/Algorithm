import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc39
 * @Description: [39. Combination Sum 组合求和]
 * @Author: [clh]
 * @Date: 2021/11/2 15:04
 **/
public class Lc39 {
    //1 我自己写的
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        backtrack(ret, ans, candidates, target);
        return ret;
    }

    private void backtrack(List<List<Integer>> ret, List<Integer> ans, int[] candidates, int target) {
        //之前一直报错，直到加了这句话，一次性通过
        if(target < 0) return;
        if(target == 0){
            ret.add(new ArrayList<>((ans)));
            return;
        }
        int prenum = ans.size() > 0 ? ans.get(ans.size() - 1) : 0;
        for(int i = 0; i < candidates.length; i++){
            if(candidates[i] < prenum)
                continue;
            ans.add(candidates[i]);
            backtrack(ret, ans, candidates, target - candidates[i]);
            ans.remove(ans.size() - 1);
        }
    }

    //2 github
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        backtracking(new ArrayList<>(), combinations, 0, target, candidates);
        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
                              int start, int target, final int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                tempCombination.add(candidates[i]);
                backtracking(tempCombination, combinations, i, target - candidates[i], candidates);
                tempCombination.remove(tempCombination.size() - 1);
            }
        }
    }
}

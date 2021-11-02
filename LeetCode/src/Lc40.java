import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc40
 * @Description: [40. Combination Sum II 含有相同元素的组合求和]
 * @Author: [clh]
 * @Date: 2021/11/2 15:23
 **/
public class Lc40 {
    //1 根据lc39 github代码修改
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        boolean[] seen = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtrack(ret, cur, candidates, 0, target, seen);
        return ret;
    }

    private void backtrack(List<List<Integer>> ret, List<Integer> cur, int[] candidates, int start, int target, boolean[] seen) {
        if(target == 0){
            ret.add(new ArrayList<>(cur));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            // 1 1 2 5 target=7 避免第二个1再来搞
            if(i != 0 && candidates[i] == candidates[i-1] && !seen[i-1])
                continue;
            if(candidates[i] <= target){
                seen[i] = true;
                cur.add(candidates[i]);
                backtrack(ret, cur, candidates, i + 1, target - candidates[i], seen);
                cur.remove(cur.size() - 1);
                seen[i] = false;
            }
        }
    }

    //2 leetcode backtrack+ counter
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // container to hold the final combinations
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            if (counter.containsKey(candidate))
                counter.put(candidate, counter.get(candidate) + 1);
            else
                counter.put(candidate, 1);
        }

        // convert the counter table to a list of (num, count) tuples
        List<int[]> counterList = new ArrayList<>();
        counter.forEach((key, value) -> {
            counterList.add(new int[]{key, value});
        });

        backtrack(comb, target, 0, counterList, results);
        return results;
    }

    private void backtrack(LinkedList<Integer> comb,
                           int remain, int curr,
                           List<int[]> counter,
                           List<List<Integer>> results) {

        if (remain <= 0) {
            if (remain == 0) {
                // make a deep copy of the current combination.
                results.add(new ArrayList<Integer>(comb));
            }
            return;
        }

        for (int nextCurr = curr; nextCurr < counter.size(); ++nextCurr) {
            int[] entry = counter.get(nextCurr);
            Integer candidate = entry[0], freq = entry[1];

            if (freq <= 0)
                continue;

            // add a new element to the current combination
            comb.addLast(candidate);
            counter.set(nextCurr, new int[]{candidate, freq - 1});

            // continue the exploration with the updated combination
            backtrack(comb, remain - candidate, nextCurr, counter, results);

            // backtrack the changes, so that we can try another candidate
            counter.set(nextCurr, new int[]{candidate, freq});
            comb.removeLast();
        }
    }

    // 3 leetcode backtracking + index
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        Arrays.sort(candidates);

        backtrack3(candidates, comb, target, 0, results);
        return results;
    }

    private void backtrack3(int[] candidates, LinkedList<Integer> comb,
                           Integer remain, Integer curr,
                           List<List<Integer>> results) {
        if (remain == 0) {
            // copy the current combination to the final list.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (int nextCurr = curr; nextCurr < candidates.length; ++nextCurr) {
            if (nextCurr > curr && candidates[nextCurr] == candidates[nextCurr - 1])
                continue;

            Integer pick = candidates[nextCurr];
            // optimization: early stopping
            if (remain - pick < 0)
                break;

            comb.addLast(pick);
            backtrack3(candidates, comb, remain - pick, nextCurr + 1, results);
            comb.removeLast();
        }
    }

}

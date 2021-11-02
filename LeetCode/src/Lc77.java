import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc77
 * @Description: [77. Combinations (Medium) 组合]
 * @Author: [clh]
 * @Date: 2021/11/1 21:35
 **/
public class Lc77 {
    //1 我自己想的
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        backtrack(ret, cur, n, k);
        return ret;
    }

    private void backtrack(List<List<Integer>> ret, List<Integer> cur, int n, int k) {
        if(cur.size() == k){
            ret.add(new ArrayList<Integer>(cur));
            return;
        }
        //因为后面出现的数不能比前面出现的数大 否则就会重复
        int prenum = cur.size() > 0? cur.get(cur.size() - 1) : 0;
        for(int i = 1; i <= n; i++){
            if(i <= prenum)
                continue;
            cur.add(i);
//            prenum = i;
            backtrack(ret, cur, n, k);
            cur.remove(cur.size()-1);
        }
    }

    //2 github
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combineList = new ArrayList<>();
        backtracking(combineList, combinations, 1, k, n);
        return combinations;
    }

    private void backtracking(List<Integer> combineList, List<List<Integer>> combinations, int start, int k, final int n) {
        if (k == 0) {
            combinations.add(new ArrayList<>(combineList));
            return;
        }
        //n = 4 k = 2 i <= 4-2+1 = 3 因为大于这个数作为combineList开头就会重复 比如 2,4  4,2
        for (int i = start; i <= n - k + 1; i++) {  // 剪枝
            combineList.add(i);
            //因为已经有1个数 后面就只用k-1个数 要取的话也是从i+1 开始取
            backtracking(combineList, combinations, i + 1, k - 1, n);
            combineList.remove(combineList.size() - 1);
        }
    }

}

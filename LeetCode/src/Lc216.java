import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc216
 * @Description: [216. Combination Sum III 1-9 数字的组合求和]
 * @Author: [clh]
 * @Date: 2021/11/2 16:14
 **/
public class Lc216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        backtrack(k, n, cur, ret, 1);
        return ret;
    }

    private void backtrack(int k, int n, List<Integer> cur, List<List<Integer>> ret, int start) {
        if(k == 0 || n== 0){
            if(k == 0 && n == 0){
                ret.add(new ArrayList<>(cur));
            }
            return;
        }
        // 如果不在1-9之间 而是1-n之间都可以就是下面这样
//        for(int i = start; i <= n; i++){
        //如果只能在1-9之间
        for(int i = start; i <= n && i <= 9; i++){
            if(i <= n){
                cur.add(i);
                backtrack(k-1, n - i, cur, ret, i+1);
                cur.remove(cur.size() - 1);
            }
        }
    }

}

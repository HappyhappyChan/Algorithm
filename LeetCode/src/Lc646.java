import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc646
 * @Description: [646. Maximum Length of Pair Chain 一组整数对能够构成的最长链]
 * @Author: [clh]
 * @Date: 2021/11/5 9:33
 **/
public class Lc646 {
    //1 我自己写的
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0 || pairs[0].length == 0)
            return 0;
        //我按照a[1]排和github答案按照a[0]排没差 为什么？
        Arrays.sort(pairs, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if (o1[1] > o2[1])
                    return 1;
                if(o1[1] == o2[1]){
                    if(o1[0] < o2[0]){
                        return -1;
                    }else if(o1[0] > o2[0]){
                        return 1;
                    }else{
                        return 0;
                    }
                }
                return -1;
            }
        });
        int n = pairs.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int ret = 1;
        for(int i = 1; i < n; i++){
            int max = 1;
            int[] cur = pairs[i];
            for(int j = 0; j < i; j++){
                int[] tmp = pairs[j];
                if(cur[0] <= tmp[1]){
                    continue;
                }
                max = Math.max(max, dp[j] + 1);
            }
            dp[i] = max;
            ret = Math.max(ret, max);
        }
        return ret;
    }

    //2 github
    public int findLongestChain2(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    //3 leetcode greedy so brilliant!
    public int findLongestChain3(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair: pairs) if (cur < pair[0]) {
            cur = pair[1];
            ans++;
        }
        return ans;
    }
    public static void main(String[] args){
        int[][] pairs = {{-3,9},{-5,0},{6,7},{2,6},{-9,-3},{-5,-5},
                {-7,7}, {-2,10},{7,8},{-1,10}};
        Arrays.sort(pairs, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if (o1[1] > o2[1])
                    return 1;
                if(o1[1] == o2[1]){
                    if(o1[0] < o2[0]){
                        return -1;
                    }else if(o1[0] > o2[0]){
                        return 1;
                    }else{
                        return 0;
                    }
                }
                return -1;
            }
        });
        int[][] pair2 = pairs;
    }

}

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc131
 * @Description: [131. Palindrome Partitioning 分割字符串使得每个部分都是回文数]
 * @Author: [clh]
 * @Date: 2021/11/2 20:52
 **/
public class Lc131 {
    //我自己写的 思路和github一样
    public static List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        backtrack(s, ret, cur);
        return ret;
    }

    private static void backtrack(String s, List<List<String>> ret, List<String> cur) {
        //""和null是不一样的！
//        if(s == null){
        if(s.length()==0){
            ret.add(new ArrayList<>(cur));
            return;
        }
//        for(int i = 0; i < s.length(); i++){
        int i = 0;
        for(int j = 1; j <= s.length(); j++){
            String pre = s.substring(i, j);
            if(isPalindrome(pre)){
                cur.add(pre);
                //分清楚pre和next 一开始把next和pre混在一起了
                String next = s.substring(j);
                backtrack(next, ret, cur);
                cur.remove(cur.size()-1);
            }
        }
//        }

    }

    private static boolean isPalindrome(String next) {
        if(next == null || next.length() < 2)
            return true;
        int i = 0, j = next.length() - 1;
        while(i < j){
            if(next.charAt(i) != next.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args){
        String s = "aab";
        List<List<String>> res = partition(s);
    }

    //2 leetcode backtracking + dynamic programming
    public List<List<String>> partition2(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}

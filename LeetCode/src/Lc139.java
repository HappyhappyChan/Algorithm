import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc139
 * @Description: [139. Word Break ]
 * @Author: [clh]
 * @Date: 2021/11/9 9:56
 **/
public class Lc139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n  = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 1; i <= n; i++){
            //对物品的迭代放在最里层
            for(String word: wordDict){
                int len = word.length();
                // leetcode [leet, code]
                if(len <= i && word.equals(s.substring(i-len, i))){
                    //dp[i]表示 s 的前 i 位是否可以用 wordDict中的单词表示。
                    dp[i] = dp[i] || dp[i-len];
                }
            }
        }
        return dp[n];
    }

    //2 leetcode-cn hashset
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        //dp[i] 表示字符串 s 前 i 个字符组成的字符串
        // s[0..i-1]是否能被空格拆分成若干个字典中出现的单词
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                //遍历到i的时候j已经遍历过了
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}

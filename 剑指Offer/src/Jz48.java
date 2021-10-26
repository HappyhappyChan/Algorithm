import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz48
 * @Description: [最长不含重复字符的子字符串]
 * @Author: [clh]
 * @Date: 2021/10/24 15:52
 **/
public class Jz48 {
    //我自己写的 暴力解法
    public int longestSubStringWithoutDuplication(String str) {
        int len = str.length();
        if(str == null || len == 0) {
            return 0;
        }
        int ret = 0;
        char[] a = str.toCharArray();
        for(int i = 0; i < len; i++){
            HashSet<Character> map = new HashSet<>();
            map.add(a[i]);
            for(int j = i + 1; j < len; j++){
                if(map.contains(a[j])){
                    ret = ret > map.size() - 1? ret : map.size() - 1;
                    break;
                }
            }
        }
        return ret;
    }

    //solution 2 github
    //没看懂
    public int solution2(String str){
        int curLen = 0;
        int maxLen = 0;
        //用来标记26个字母
        int[] preIndexs = new int[26];
        //数组初始化为-1
        Arrays.fill(preIndexs, -1);
        //遍历字符串
        for (int curI = 0; curI < str.length(); curI++) {
            //为了获得对应的索引 c为preIndex中对应的索引
            int c = str.charAt(curI) - 'a';
            int preI = preIndexs[c];
            //preI = -1说明之前没出现过
            if (preI == -1 || curI - preI > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curI - preI;
            }
            //将当前元素的索引存入preIndex
            preIndexs[c] = curI;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

    //solution 3 滑动窗口 leetcode
    public int solution3(String s){
        int n = s.length();
        if(n < 1) return n;
        int maxlen = 1;

        int l = 0, r = 0;
        Set<Character> set = new HashSet<Character>();
        while(r < n){
            char a = s.charAt(r);
            while(set.contains(a)){
                set.remove(s.charAt(l));
                l++;
            }
            maxlen = Math.max(maxlen, r - l + 1);
            set.add(a);
            r++;
        }
        return maxlen;
    }

    //solution 4 dp leetcode
    public int solution4(String s){
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            //这里的tmp其实就是dp[j-1]
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }
}

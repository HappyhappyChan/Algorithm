/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc392
 * @Description: [392. Is Subsequence 判断是否为子序列]
 * @Author: [clh]
 * @Date: 2021/10/28 19:27
 **/
public class Lc392 {
    // 1 双指针遍历
    public boolean isSubsequence(String s, String t) {
        if(t == null) return false;
        int len1 = s.length();
        int len2 = t.length();
        if(len1 > len2) return false;
        int p1 = 0; // 遍历s
        int p2 = 0; // 遍历t
        while(p1 < len1 && p2 < len2){
            char a = s.charAt(p1);
            char b = t.charAt(p2);
            if(a == b){
                p1++;
                p2++;
            }else{
                p2++;
            }
        }
        return p1 == len1 && p2 <= len2;
    }

    // 2 github 利用indexOf
    public boolean isSubsequence2(String s, String t){
        int ind = -1;
        for(char c : s.toCharArray()){
            ind = t.indexOf(c, ind +1);
            if(ind == -1)
                return false;
        }
        return true;
    }
}

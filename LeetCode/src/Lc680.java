/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc680
 * @Description: [680. Valid Palindrome II 回文字符串]
 * @Author: [clh]
 * @Date: 2021/10/27 9:35
 **/
public class Lc680 {
    //我觉得我的思路没错啊 就是不知道为什么一直通不过……
    //我知道我错哪了 即使s[i+1] != s[j] s[i] != s[j-1] 这个时候也不能随意r-- l++
    //比如abcca 这个时候删除b就是回文 但如果删除c就不是
    public boolean validPalindrome(String s) {
        if(s.length() <= 2)
            return true;
        int l = 0;
        int r = s.length() - 1;
        int cnt = 1; //至多可以删除一个
        while(l < r){
            if(s.charAt(l) != s.charAt(r)) {
                //不能随意r-- l++
                if (cnt > 0) {
                    cnt--;
                    if(s.charAt(l+1) == s.charAt(r)){
                        l++;
                    }else{
                        r--;
                    }
                    continue;
                } else {
                    return false;
                }
            }
            l++;
            r--;
        }
        return true;
    }


    // 2 github
    public boolean validPalindrome2(String s){
        for(int i = 0, j = s.length() - 1; i < j; i++, j--){
            if(s.charAt(i) != s.charAt(j))
                return isPalindrom(s, i+1, j) || isPalindrom(s, i , j - 1);
        }
        return true;
    }

    private boolean isPalindrom(String s, int i, int j) {
        while(i < j){
            if(s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

    //3 github discuss看见的对我的想法的改进
    public boolean validPalindrome3(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j)) {
                int i1 = i, j1 = j - 1, i2 = i + 1, j2 = j;
                while (i1 < j1 && s.charAt(i1) == s.charAt(j1)) {i1++; j1--;};
                while (i2 < j2 && s.charAt(i2) == s.charAt(j2)) {i2++; j2--;};
                //如果这两个条件都不符合 说明没遍历完就已经有不相等的了
                return i1 >= j1 || i2 >= j2;
            }
        return true;
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc409
 * @Description: [409. Longest Palindrome (Easy) 计算一组字符集合可以组成的回文字符串的最大长度]
 * @Author: [clh]
 * @Date: 2021/11/24 10:01
 **/
public class Lc409 {
    public static int longestPalindrome(String s) {
        if(s == null || s.length() == 0)
            return 0;
        if(s.length() < 2)
            return 1;
        int[] cnts = new int[52];
        char[] chs = s.toCharArray();
        for(char c : chs){
            if(c >= 65 && c <= 90){
                cnts[c - 'A']++;
            }else{
                cnts[c - 'A' - 6]++;
            }
        }
        int ans = 0;
        int odd = 0; //[aaaaa] 虽然是奇数 但也可以拆出偶数
        for(int cnt : cnts){
            if(cnt >= 2){
                //说明是偶数
                if((cnt & 1) == 0)
                    ans += cnt;
                else {
                    ans += cnt - 1;
                    odd++;
                }
            }else if(cnt == 1){
                odd++;
            }
        }
        odd = odd > 0? 1 : 0;
        return ans + odd;
    }

    //2 github
    public int longestPalindrome2(String s) {
        int[] cnts = new int[256];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int palindrome = 0;
        for (int cnt : cnts) {
            //这样就不用讨论奇偶了
            palindrome += (cnt / 2) * 2;
        }
        if (palindrome < s.length()) {
            palindrome++;   // 这个条件下 s 中一定有单个未使用的字符存在，可以把这个字符放到回文的最中间
        }
        return palindrome;
    }

    //3 leetcode solution
    public int longestPalindrome3(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            //一旦是奇数 说明已经加过1了 就不用再加了
            if (ans % 2 == 0 && v % 2 == 1)
                ans++;
        }
        return ans;
    }

    public static void main(String[] args){
        String s = "abccccdd";
        int ans = longestPalindrome(s);
    }
}

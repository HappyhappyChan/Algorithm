/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc91
 * @Description: [91. Decode Ways 分割整数构成字母字符串]
 * @Author: [clh]
 * @Date: 2021/11/4 17:02
 **/
public class Lc91 {
    //1 我自己写的
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)
            return 0;
        /*if(s.length() == 1) {
            int cur = Integer.valueOf(s);
            // 如果 s = "0" 返回0
            if(cur < 1) return 0;
            return 1;
        }*/
        if(s.charAt(0) == '0') return 0;
        int n = s.length();
        //s = "1" 的时候不能用substring
        if(n == 1) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int cur = Integer.valueOf(s.substring(0,2));
        //防止 10
        if( cur < 27 && cur > 0 && s.charAt(1) != '0'){
            dp[1] = 2;
            //防止301
        }else if(s.charAt(1) == '0' && cur > 26){
            return 0;
        }else{
            dp[1] = 1;
        }
        //还要小心06 011这种开头为1的情况啊
        for(int i = 2; i < n; i++){
            cur = Integer.valueOf(s.substring(i-1, i+1));
            if(s.charAt(i-1) != '0' && s.charAt(i) != '0' && cur < 27 && cur > 0){
                dp[i] = dp[i-1] + dp[i-2];
                //230 100 301
            }else if((s.charAt(i-1) == '0' && s.charAt(i) == '0') || (s.charAt(i) == '0' && cur > 26)){
                return 0;
            }else if(s.charAt(i-1) != '0' && s.charAt(i) == '0' && cur < 27 && cur > 9){
                dp[i] = dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }
        return dp[n-1];
    }

    //2 github
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            //因为字符串下标从0开始，所以这里要i-1 才能与字符串对上
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            //如果前一个字符是0 不可能组合
            if (s.charAt(i - 2) == '0') {
                continue;
            }
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    //3 leetcode-cn 对上面的优化
    public int numDecodings3(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c=f[i]
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }

}

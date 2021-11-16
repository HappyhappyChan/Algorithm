/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc172
 * @Description: [172. Factorial Trailing Zeroes (Easy)]
 * @Author: [clh]
 * @Date: 2021/11/15 21:18
 **/
public class Lc172 {
    //我自己写的 不知道哪里错了 30应该是7 但是我代码给的是0
    public int trailingZeroes(int n) {
        if(n == 0) return 0;
        long res = n;
        for(int i = n-1; i > 0; i--){
            res *= i;
        }
        String s = String.valueOf(res);
        int len = s.length();
        int ans = 0;
        //因为求的是尾部
        for(int i = len-1; i >= 0; i--){
            if(s.charAt(i)=='0')
                ans++;
            else
                break;
        }
        return ans;
    }

    //2 github
    public int trailingZeroes2(int n) {
        if(n == 0) return 0;
        return n/5 + trailingZeroes2(n/5);
    }
}

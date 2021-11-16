/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc326
 * @Description: [326. Power of Three (Easy) 3 的 n 次方]
 * @Author: [clh]
 * @Date: 2021/11/16 14:54
 **/
public class Lc326 {
    //我自己想的 beats 99.98% 89.98%
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        //n=1 是3的0次方也ok
        while(n > 1){
            if(n % 3 != 0)
                return false;
            n /= 3;
        }
        return true;
    }

    //2 github
    public boolean isPowerOfThree2(int n) {
        return n > 0 && (1162261467 % n == 0);
    }

    //3 leetcode
    //base conversion
    public boolean isPowerOfThree3(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    //4 leetcode
    //mathematics
    public boolean isPowerOfThree4(int n) {
        return (Math.log10(n)/ Math.log10(3)) % 1 == 0;
        //return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }

    //5 leetcode
    //integer limitations
    public boolean isPowerOfThree5(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}

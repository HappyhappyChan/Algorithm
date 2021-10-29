/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc633
 * @Description: [ 两数平方和  Sum of Square Numbers]
 * @Author: [clh]
 * @Date: 2021/10/26 19:58
 **/
public class Lc633 {
    //我写的 一直报错
    //终于找到错误的原因的 这里的l r不能是int 要是long！
    public boolean judgeSquareSum(int c) {
        if(c < 0)
            return false;
        int mid = (int)(Math.sqrt(c));
        if(mid*mid == c)
            return true;
        //因为 a2 b2 必须都小于等于c
//        int l = 0, r = mid;
        long l = 0, r = mid;
        //因为 2 = 1 + 1也对
        while(l <= r){
            //tmp也要改成long
            long tmp = l * l + r * r;
            if(tmp == c)
                return true;
            if(tmp > c){
                r--;
                continue;
            }
            if(tmp < c){
                l++;
                continue;
            }
        }
        return false;
    }

    //1 brute force
    public boolean judgeSquareSum2(int c) {
        for (long a = 0; a * a <= c; a++) {
            for (long b = 0; b * b <= c; b++) {
                if (a * a + b * b == c)
                    return true;
            }
        }
        return false;
    }

    //2 better brute force
    //我自己写的
    public boolean judgeSquareSum3(int c){
        for(long a = 0; a * a <= c; a++){
            long b = c - a*a;
            int tmp = (int)Math.sqrt(b);
            if(tmp * tmp == b)
                return true;
        }
        return false;
    }

    //2 leetcode
    //利用数学
    //这种利用加法可以但是会超时
    public boolean judgeSquareSum4(int c){
        for(long a = 0; a * a <= c; a++){
            int b = c - (int)(a*a);
            int i = 1, sum = 0;
            while(sum < b){
                sum += i;
                i += 2;
            }
            if(sum == b)
                return true;
        }
        return false;
    }

    //3 leetcode
    //binary search
    public boolean judge5(int c){
        for(long a = 0; a * a <= c; a++){
            int b = c - (int)(a*a);
            if(binarysearch(0, b, b))
                return true;
        }
        return false;
    }

    private boolean binarysearch(long s, long e, int n) {
        if(s > e)
            return false;
        long mid = s + (e - s)/2;
        if(mid * mid == n)
            return true;
        if(mid * mid > n)
            return binarysearch(s, mid - 1, n);
        return binarysearch(mid + 1, e, n);
    }

    // 4 费马定理
    // leetcode
    public boolean judgeSquareSum5(int c) {
        for (int i = 2; i * i <= c; i++) {
            int count = 0;
            if (c % i == 0) {
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                if (i % 4 == 3 && count % 2 != 0)
                    return false;
            }
        }
        return c % 4 != 3;
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz16
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/10/23 10:03
 **/
public class Jz16 {
    //我自己写的暴力解法 一开始忽略了指数为负的情况
    public double Power(double base, int exponent) {
        double sum = 1;
        int cnt = Math.abs(exponent);
        while(cnt > 0){
            sum *= base;
            cnt--;
        }
        return exponent < 0 ? 1/sum :  sum;
    }

    //solution 2 github 分治
    public double solution2(double x, int n){
        boolean isNegative = false;
        if(n < 0){
            n = -n;
            isNegative = true;
        }
        double res = pow(x, n);
        return isNegative ? 1 / res : res;
    }

    private double pow(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        double res = pow(x, n/2);
        res *= res;
        if(n % 2 != 0) res = res * x;
        return res;
    }


}

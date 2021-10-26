/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz10
 * @Description: [斐波那契数列]
 * @Author: [clh]
 * @Date: 2021/10/24 9:54
 **/
public class Jz10 {
    //solution 1 github 递归
    public int Fibonacci(int n) {
        if(n == 1 || n == 2)
            return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    //solution 2 github dp
    public int solution2(int n){
        if(n <= 1)
            return n;
        int pre2 = 0, pre1 = 1;
        int fib = 0;
        for(int i = 2; i <= n; i++){
            fib = pre2 + pre1;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }

    //solution 3 github dp优化 因为n<40
    private int[] fib = new int[40]
    public int solution3(int n){
        fib[1] = 1;
        for(int i = 2; i < 40; i++){
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}

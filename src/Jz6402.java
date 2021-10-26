import static java.lang.Math.pow;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz6402
 * @Description: [JZ64 求1+2+3+...+n]
 * @Author: [clh]
 * @Date: 2021/10/26 15:40
 **/
public class Jz6402 {
    // 1 我自己想的
    public int Sum_Solution(int n) {
        if(n <= 0)
            return 0;
        int sum = n;
        return sum + Sum_Solution(n-1);
    }

    // 2 github
    public int solution2(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += solution2(n - 1)) > 0);
        return sum;
    }

    // 3 nowcoder
    int Sum_Solution3(int n) {
        return ((int)pow(n, 2) + n) >> 1;
    }
}

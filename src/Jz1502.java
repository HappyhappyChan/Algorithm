/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1502
 * @Description: [15 二进制中1的个数]
 * @Author: [clh]
 * @Date: 2021/10/25 17:06
 **/
public class Jz1502 {
    //1 github 位运算
    public int NumberOf1(int n) {
        int cnt = 0;
        while( n != 0){
            cnt++;
            n = n & (n-1);
        }
        return cnt;
    }

    //2 nowcoder 位右移
    public int solution2(int n){
        int cnt = 0;
        while(n != 0){
            //判断n&1是否为0
            if( (n & 1) != 0 ) cnt++;
            //将n进行无符号右移
            n = n >>> 1;
        }
        return cnt;
    }

}

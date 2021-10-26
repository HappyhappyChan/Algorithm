/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz65
 * @Description: [65 不用加减乘除做加法]
 * @Author: [clh]
 * @Date: 2021/10/26 15:48
 **/
public class Jz65 {
    //1 nowcoder
    public int Add(int num1,int num2) {
        while(num2 != 0){//进位为0时，跳出循环
            int sum = num1^num2;//非进位求和
            int temp = (num1&num2)<<1; //计算出进位；
            num1 = sum; //非进位和
            num2 = temp; //进位；
        }
        return num1;
    }
}

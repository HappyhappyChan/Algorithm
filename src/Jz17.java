/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz17
 * @Description: [打印从 1 到最大的 n 位数]
 * @Author: [clh]
 * @Date: 2021/10/25 20:29
 **/
public class Jz17 {

    //1 github backtracking
    public void print1ToMaxOfNDigits(int n){
        if(n <= 0)
            return;
        char[] num = new char[n];
        print1ToMaxOfNDigits(num, 0);
    }

    /**
     *
     * @param num 存储各个位置数字的数组
     * @param digit 第几位
     */
    private void print1ToMaxOfNDigits(char[] num, int digit){
        if(digit == num.length){
            printNum(num);
            return;
        }
        //从左边第一位开始依次遍历
        for(int i = 0; i < 10; i++){
            num[digit] = (char)(i + '0');
            print1ToMaxOfNDigits(num, digit + 1);
        }
    }

    private void printNum(char[] num){
        int index = 0;
        //要定位到第一个不为0的数
        while(index < num.length && num[index] == '0'){
            index++;
        }
        while(index < num.length){
            System.out.print(num[index++]);
        }
        System.out.println();
    }
}

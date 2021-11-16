/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc367
 * @Description: [367. Valid Perfect Square 平方数]
 * @Author: [clh]
 * @Date: 2021/11/16 14:44
 **/
public class Lc367 {
    //1 library function
    public boolean isPerfectSquare(int num) {
        if(num < 2)
            return true;
        if((int)(Math.sqrt(num))*(int)(Math.sqrt(num)) == num)
            return true;
        return false;
    }

    //brute force
    public boolean isPerfectSquare2(int num) {
        if(num < 2)
            return true;
        //将int 改成 long就可以AC了
        long diff = 3;
        long cur = 1;
        while(cur <= num){
            if(cur == num){
                return true;
            }
            cur += diff;
            diff += 2;
        }
        return false;
    }

    //github 为什么这样效率就比我高很多……这个达到100% 我才14%左右？
    public boolean isPerfectSquare3(int num) {
        int subNum = 1;
        while (num > 0) {
            num -= subNum;
            subNum += 2;
        }
        return num == 0;
    }

}

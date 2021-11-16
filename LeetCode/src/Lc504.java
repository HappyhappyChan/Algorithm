/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc504
 * @Description: [504. Base 7 (Easy) 7 进制]
 * @Author: [clh]
 * @Date: 2021/11/15 19:00
 **/
public class Lc504 {
    public String convertToBase7(int num) {
        if(num == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        int cur = num > 0? num : -num;
        while(cur != 0){
            int res = cur / 7;
            sb.insert(0,String.valueOf(cur % 7));
            cur = res;
        }
        if(num < 0) sb.insert(0,'-');
        return sb.toString();
    }
}

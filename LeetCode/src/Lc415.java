/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc415
 * @Description: [415. Add Strings (Easy) 字符串加法]
 * @Author: [clh]
 * @Date: 2021/11/16 10:16
 **/
public class Lc415 {
    //1
    //不管是Integer.parseInt 还是Long.parseLong 都会有更大更长的数
    public String addStrings(String num1, String num2) {
        long a = Long.parseLong(num1);
        long b = Long.parseLong(num2);
        long res = a + b;
        return res+"";
    }

    //2 模拟
    public String addStrings2(String num1, String num2) {
        int g = 0;
        StringBuilder sb = new StringBuilder();
        int ia = num1.length()-1;
        int ib = num2.length()-1;
        while(ia >= 0 || ib >= 0){
            if(ia >= 0 && ia < num1.length()){
                g += num1.charAt(ia--)-'0';
            }
            if(ib >= 0 && ib < num2.length()){
                g += num2.charAt(ib--)-'0';
            }
            sb.append(g % 10);
            g = g / 10;
        }
        if(g == 1)
            sb.append('1');
        return sb.reverse().toString();
    }
}

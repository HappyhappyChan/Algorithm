/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc67
 * @Description: [67. Add Binary (Easy) 二进制加法]
 * @Author: [clh]
 * @Date: 2021/11/16 9:27
 **/
public class Lc67 {
    //我好久之前写的
    public String addBinary1(String a, String b) {
        StringBuilder sum = new StringBuilder();

        int g = 0;
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        while (ia >= 0 || ib >= 0) {
            if (ia >= 0) g += a.charAt(ia--) - '0';
            if (ib >= 0) g += b.charAt(ib--) - '0';
            sum.append(Integer.toString(g % 2));
            g = g / 2;
        }
        if(g != 0) sum.append('1');
        return sum.reverse().toString();
    }

    //太长会报错
    public static String addBinary2(String a, String b) {
        int len1 = a.length();
        int n1 = 0;
        for(int i = 0; i < len1; i++){
            if(i == len1 - 1) {
                n1 += (a.charAt(i) - '0') * 1;
                continue;
            }
            if(i == 0){
                n1 += (a.charAt(i)-'0')*2;
                continue;
            }
            n1 = (n1+(a.charAt(i)-'0')*1)*2;
        }
        System.out.println(n1);
        int len2 = b.length();
        int n2 = 0;
        for(int i = 0; i < len2; i++){
            if(i == len2 - 1) {
                n2 += (b.charAt(i) - '0') * 1;
                continue;
            }
            if(i == 0){
                n2 += (b.charAt(i)-'0')*2;
                continue;
            }
            n2 = (n2+(b.charAt(i)-'0')*1)*2;
        }
        System.out.println(n2);
        int sum = n1 + n2;
        System.out.println(sum);
        return Integer.toBinaryString(sum);
    }

    //3 leetcode-cn
    public static String addBinary3(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b,2));
    }

    //4 leetcode-cn 位运算
    //但这种如果数字太长也是会报错
    public static String addBinary4(String a, String b) {
        int x = Integer.parseInt(a,2);
        int y = Integer.parseInt(b,2);
        while(y != 0){
            int ans  = x ^ y;
            int carry = (x & y) << 1;
            x = ans;
            y = carry;
        }
        return Integer.toBinaryString(x);
    }
    public static void  main(String[] args){
        String a = "11";
        String b = "1";
        String res = addBinary2(a,b);
    }
}

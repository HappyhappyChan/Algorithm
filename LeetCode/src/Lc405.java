/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc405
 * @Description: [405. Convert a Number to Hexadecimal (Easy) 16 进制]
 * @Author: [clh]
 * @Date: 2021/11/15 19:13
 **/
public class Lc405 {
    //1 利用java函数
    //但是不能满足负数按照补码形式输出
    public String toHex(int num) {
        return Integer.toString(num, 16);
    }

    //2
    //leetcode-cn discuss
    public String toHex2(int num) {
        if(num == 0) return "0";
        String[] alpha = {"a","b","c","d","e","f"};
        long cur = num > 0 ? num : (long)(Math.pow(2, 32)+num);
        StringBuilder sb = new StringBuilder();
        while(cur != 0){
            long res = cur / 16;
            long  remainder = cur % 16;
            sb.append(remainder > 9 ? (char)(remainder-10+'a') : ""+remainder);
            cur = res;
        }
        return sb.reverse().toString();
    }

    //3 leetcode-cn discuss
    public String toHex3(int num) {
        if(num == 0 ) return "0";
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            int u = num & 15;
            char c = (char)(u+'0');
            if(u >= 10)
                c = (char)(u-10+'a');
            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }

    //4 leetcode-cn official
    public String toHex4(int num){
        if(num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for(int i = 7; i >= 0; i--){
            int val = (num >> (4 * i)) & 0xf;
            if(sb.length() > 0 || val > 0){
                char digit = val < 10? (char)(val+'0') : (char)(val-10+'a');
                sb.append(digit);
            }
        }
        return sb.toString();
    }
}

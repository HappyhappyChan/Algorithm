/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz43
 * @Description: [左旋转字符串]
 * @Author: [clh]
 * @Date: 2021/10/11 11:05
 **/
public class Jz43 {
    //解法1 from newcoder 利用字符串操作
    public String LeftRotateString(String str, int n) {
        if(str == null || n > str.length())
            return str;
        return str.substring(n) + str.substring(0, n);
    }

    //解法2 from github 利用字符串翻转
    //先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"
    // 然后再把整个字符串翻转得到 "XYZdefabc"。

    public String solution2(String str, int n){
        if(n >= str.length())
            return str;
        char[] c = str.toCharArray();
        reverse(c, 0, n - 1);
        reverse(c, n, c.length - 1);
        reverse(c, 0, c.length - 1);

        // 居然还有这操作 将字符数组变成字符串
        return new String(c);
    }

    private void reverse(char[] c, int i, int j) {
        while(i < j){
            char t = c[i];
            c[i] = c[j];
            c[j] = t;
            i++;
            j--;
        }
    }


}

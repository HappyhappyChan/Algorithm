/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc168
 * @Description: [168. Excel Sheet Column Title (Easy) 26 进制]
 * @Author: [clh]
 * @Date: 2021/11/15 20:44
 **/
public class Lc168 {
    //1 我好早之前写的
    public String convertToTitle(int n) {
        char[] c = new char[27];
        for(int i = 1; i < 27;i++)
        {
            c [i] = (char)(65+ i-1);
        }
        StringBuilder s = new StringBuilder();
        while(n > 0)
        {
            if(n == 26) return "Z";
            if(n > 26)
            {
                int i = n / 26;
                s.append(c[i]);
            }
            int i= n % 26;
            if(i != 0)
            {
                s. append(c[i]);
            }else {
                s.append('Z');
            }
            n = (n/26-i)/26;
        }
        return s.toString();
    }

    //2 我自己想的 搞了半天通过了！
    public String convertToTitle2(int n) {
        if(n == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while(n != 0){
            if(n % 26 == 0){
                sb.append('Z');
                n /= 27;
            }else{
                sb.append((char)(n%26+'A'-1));
                n /= 26;
            }
        }
        return sb.reverse().toString();
    }

    //3 github
    public String convertToTitle3(int n) {
        if (n == 0) {
            return "";
        }
        n--;
        return convertToTitle3(n / 26) + (char) (n % 26 + 'A');
    }
}

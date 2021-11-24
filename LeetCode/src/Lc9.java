/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc9
 * @Description: [9. Palindrome Number (Easy) 判断一个整数是否是回文数]
 * @Author: [clh]
 * @Date: 2021/11/24 16:18
 **/
public class Lc9 {
    //0 我好早之前写的
    public boolean isPalindrome0(int x) {
        boolean flag = true;
        int dig = 0;
        int temp = x;
        int r, l;
        while(x/10 != 0)
        {
            x /= 10;
            dig ++;
        }
        System.out.println(dig);
        while(temp != 0)
        {
            r = temp % 10;
            l = temp /(int)(Math.pow(10, dig));
            if(r != l)
            {
                flag = false;
                break;
            }
            temp = (temp - l*(int)(Math.pow(10, dig)))/10;
            dig = dig - 2;
        }


        if( x < 0 ) flag = false;
        return flag;
    }

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int l = 0, r = s.length();
        while(l < r){
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static boolean isPalindrome2(int x){
        if(x < 0) return false; //-121
        int digit = 0;
        int temp = x;
        while(temp != 0){
            temp /= 10;
            digit++;
        }
        int tmp = x;
        while(tmp != 0 && digit > 1){
            //这样得到的结果是100 但是不是100而是2进制 相当于是4啊
            //int div = 1 << (digit - 1);
            int div = (int)Math.pow(10, digit-1);
            int l = tmp / div;
            int r = tmp % 10;
            if(l != r)
                return false;
            tmp = (tmp - l * div)/10;
            digit -= 2;
        }
        return true;
    }

    //3 revert half of the number
    public boolean isPalindrome3(int x) {
        if(x == 0) return true;
        if(x < 0 || x % 10 == 0)
            return false;
        int right = 0;
        while(x > right){
            right = right * 10 + x % 10;
            x /= 10;
        }
        return x == right || x == right/10;
    }

    public static void main(String[] args){
        int x = 121;
        boolean f = isPalindrome2(x);
    }
}

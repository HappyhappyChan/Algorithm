/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz67
 * @Description: [67 把字符串转换成整数]
 * @Author: [clh]
 * @Date: 2021/10/26 16:04
 **/
public class Jz67 {
    public int StrToInt(String str) {
        if(str == null || str.length() < 1)
            return 0;
        //用来判断是正负数
        int flag = 1;
        if(str.charAt(0) == '+'){
            str = str.substring(1);
        }else if(str.charAt(0) == '-'){
            str = str.substring(1);
            flag = -1;
        }
        int sum = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c >= '0' && c <= '9'){
                sum = sum * 10 + c - '0';
            }else{
                return 0;
            }
        }
//        return flag == 1? sum : sum * (-1);
        //没想到还可以直接return -sum
        return flag == 1 ? sum : -sum;
    }

}

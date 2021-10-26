import java.util.regex.Pattern;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz2002
 * @Description: [20 表示数值的字符串]
 * @Author: [clh]
 * @Date: 2021/10/26 9:53
 **/
public class Jz2002 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param str string字符串
     * @return bool布尔型
     */

    // 1 github regular expression
    //这种方法通不过啊
    public boolean isNumeric (String str) {
        // write code here
        if (str == null || str.length() == 0)
            return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    //2 nowcoder

    public boolean isNumeric2 (String str) {
        int n = str.length();
        int index = 0;
        //是否有数字
        //是否有e
        boolean hasNum = false, hasE = false;
        //是否有正负符号
        //是否有点
        boolean hasSign = false, hasDot = false;
        //处理开头的空格 index相应的后移
        while(index < n && str.charAt(index) == ' ')
            index++;
        //进入循环 遍历字符串
        while(index < n)
        {
            //判断字符串是否为数字
            while(index < n && str.charAt(index) >= '0' && str.charAt(index) <= '9')
            {
                index++;
                hasNum = true;
            }
            //遍历到结尾则退出
            if(index == n)
            {
                break;
            }
            //如果遇到不是数字的 判断是否为e E
            char c = str.charAt(index);
            if(c == 'e' || c == 'E')
            {
                //如果e已经出现或者当前e之前没有出现过数字，返回fasle
                if(hasE || !hasNum)
                {
                    return false;
                }
                //并且将其他3个flag全部置为false，因为要开始遍历e后面的新数字了
                // eg -1E-16 5e2
                hasE = true;
                hasNum = false;
                hasSign = false;
                hasDot = false;
            }else if(c == '+' || c == '-') {
                //如果当前字符c是+或-：如果已经出现过+或-或者已经出现过数字或者已经出现过'.'，返回flase
                if(hasSign || hasNum || hasDot) {
                    return false;
                }
                hasSign = true;
            }else if(c == '.'){
                //如果已经出现过'.'或者已经出现过'e'或'E'，返回false
                if(hasDot || hasE)
                {
                    return false;
                }
                hasDot = true;
            }else if(c == ' ') {
                    break;
            } else {
                return false;
            }
            index++;
        }
        //处理空格，index相应的后移
        while(index < n && str.charAt(index) == ' ')
            index++;
        //但是还要满足hasNum为true才可以最终返回true，因为如果字符串里全是符号没有数字的话是不行的，
        // 而且e后面没有数字也是不行的，
        // 但是没有符号是可以的，所以4个flag里只要判断一下hasNum就行；
        return hasNum && index == n;
    }

    //3 nowcoder
    public boolean isNumeric3(String str){
        //        ^表示开头 $ 表示结尾  java中两个\\ 代表一个\
        //        * 零次或多次匹配前面的字符或子表达式
        //        ？零次或一次匹配前面的字符或子表达式
        //        + 一次或多次匹配前面的字符或子表达式
        //        [] 字符集。匹配包含的任一字符
        //        (:? )匹配 pattern 但不捕获该匹配的子表达式，即它是一个非捕获匹配
        //还是不能通过啊
        String p = "^[+-]?(\\d*\\.\\d+|\\d+(\\.\\d*)?)(?:[eE][+-]?\\d+)?$";
        return Pattern.matches(p,str);
    }

    //4 bilibili
    //在nowcoder上通过了！
    public boolean solution4(String s){
        try{
            Double.parseDouble(s);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    //在上面s4在LeetCode是不行的
    //报错例子 95.94f 这个时候他会把f也算进字符串里面 double函数会自动识别
    //但案例中是不允许出现这样的
    public boolean solution5(String s){
        if(s.endsWith("f") || s.endsWith("d") || s.endsWith("D")){
            return false;
        }
        try{
            Double.parseDouble(s);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}

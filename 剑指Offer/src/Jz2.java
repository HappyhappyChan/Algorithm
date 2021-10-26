/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz2
 * @Description: [替换空格]
 * @Author: [clh]
 * @Date: 2021/10/13 20:45
 **/
public class Jz2 {
    //不能用split 因为有可能出现"   " 全都是空格的情况。
    public String replaceSpace (String s) {
        if(s.length() == 0)
            return s;
        StringBuilder ret = new StringBuilder();
        char[] tmp = s.toCharArray();
        for(int i = 0; i < tmp.length; i++){
            if(tmp[i]==' ')
                ret.append("%20");
            else
                ret.append(tmp[i]);
        }
        return ret.toString();
    }

    //solution 2 from github 用两个指针插入
    public String replaceSpace2 (String s) {
        if(s.length() == 0)
            return s;
        StringBuffer ret = new StringBuffer(s);
        int p1 = 0;
        int p2 = s.length() - 1;
        while(p1 < s.length()){
            if(s.charAt(p1)==' '){
                //把1个空格换成3个符 只是多2个位置而已
                ret.append("  ");
                p2 += 2;
            }
            p1++;
        }
        p1 = s.length() - 1;
        while(p1>=0 && p1 != p2){
            if(ret.charAt(p1) == ' '){
                ret.setCharAt(p2--, '0');
                ret.setCharAt(p2--, '2');
                ret.setCharAt(p2--, '%');
                p1--;
            }else{
                ret.setCharAt(p2--, ret.charAt(p1--));
            }
        }
        return ret.toString();
    }
}

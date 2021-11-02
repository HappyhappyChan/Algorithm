import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc93
 * @Description: [93. Restore IP Addresses]
 * @Author: [clh]
 * @Date: 2021/11/1 10:55
 **/
public class Lc93 {
    //1 我写的 写的乱七八糟 别看了
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        if(s == null || s.length() == 0)
            return ret;
        doCombination(new StringBuilder(), ret, s, 0, 0);
        return ret;
    }

    private void doCombination(StringBuilder prefix, List<String> ret, String s, int left, int right) {
        if(prefix.length() + 3 == s.length() && right == s.length()-1){
            ret.add(prefix.toString());
            return;
        }
        int tmp = Integer.parseInt(prefix.toString().substring(left, right+1));
        if(tmp >= 0 && tmp <= 255){
            prefix.append(".");
            left++;
            right = left;
            doCombination(prefix, ret, s, left, right);
            prefix.deleteCharAt(prefix.length() - 1);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    //2 github
    public List<String> restoreIpAddresses2(String s) {
        List<String> addresses = new ArrayList<>();
        StringBuilder tempAddress = new StringBuilder();

        doRestore(0, tempAddress, addresses, s);
        return addresses;
    }

    /**
     *
     * @param k 第几段
     * @param tempAddress 当前字符串
     * @param addresses 用来存储需要返回的结果
     * @param s 题目给的字符串
     */
    private void doRestore(int k, StringBuilder tempAddress, List<String> addresses, String s) {
        if (k == 4 || s.length() == 0) {
            //如果已经有4段，而且s都用完了
            if (k == 4 && s.length() == 0) {
                addresses.add(tempAddress.toString());
            }
            //如果k=4 但是s.len > 0 说明没用完
            //如果k < 4 但s.len = 0 说明不够用
            return;
        }
        //一段最多3个字符
        for (int i = 0; i < s.length() && i <= 2; i++) {
            //每一段有3中取法
            //1 只取一位数 则0-9均可
            //2 取2位数 则要求首位不能为0
            //3 取3位数 要求首位不能为0 而且这3位数构成的不能大于255
            if (i != 0 && s.charAt(0) == '0') {
                break;
            }
            //一种情况一种情况来取
            String part = s.substring(0, i + 1);
            //上面提到的123都要求不大于255
            if (Integer.valueOf(part) <= 255) {
                //每一次都是先加。再加数字
                //如果=空，说明是首段 首段前面不用加点
                if (tempAddress.length() != 0) {
                    part = "." + part;
                }
                tempAddress.append(part);
                //进行完上面说明一段已经完成k++
                //s要从i+1后面取
                doRestore(k + 1, tempAddress, addresses, s.substring(i + 1));
                //递归完回溯 删除加入的part
                tempAddress.delete(tempAddress.length() - part.length(), tempAddress.length());
            }
        }
    }
}

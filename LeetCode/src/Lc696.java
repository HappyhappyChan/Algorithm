import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc696
 * @Description: [696. Count Binary Substrings (Easy) 统计二进制字符串中连续 1 和连续 0 数量相同的子字符串个数]
 * @Author: [clh]
 * @Date: 2021/11/24 16:56
 **/
public class Lc696 {
    //TLE
    public static int countBinarySubstrings(String s) {
        if(s == null || s.length() < 2)
            return 0;
        int ans = 0;
//        Set<String> set = new HashSet<>();
        int n = s.length();
        for(int i = 0; i < n-1; i++){
            for(int j = i + 1; j < n; j = j + 2){
                String tmp = s.substring(i, j+1);
                if(countBinary(tmp))
//                    set.add(tmp);
                    ans++;
            }
        }
        return ans;
    }

    private static boolean countBinary(String s) {
        int len = s.length();
        int cnt = 0;
        int idx1 = s.indexOf('1');
        int idx0 = s.indexOf('0');
        int i = 0;
        while( i < len){
            if(s.charAt(i) == '1'){
                if((idx1 > idx0 && i < idx0) || (idx1 < idx0 && i > idx0))
                    return false;
                cnt++;
            }else{
                if((idx0>idx1 && i < idx1) || (idx0 < idx1 && i > idx1))
                    return false;
            }
            i++;
        }
        return cnt == len/2;
    }

    public static  void main(String[] args){
        String s= "00110011";
        int cnt = countBinarySubstrings(s);
    }

    //2 github
    public static int countBinarySubstrings2(String s){
        int preLen = 0, curLen = 1, cnt = 0;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == s.charAt(i-1)){
                curLen++;
            }else{
                preLen = curLen;
                curLen = 1;
            }
            if(preLen >= curLen)
                cnt++;
        }
        return cnt;
    }

    //3 leetcode group by character
    public int countBinarySubstrings3(String s){
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == s.charAt(i-1))
                groups[t]++;
            else{
                t++;
                groups[t] = 1;
            }
        }
        int ans = 0;
        for(int i = 1; i <= t; i++){
            ans += Math.min(groups[i-1], groups[i]);
        }
        return ans;
    }
}

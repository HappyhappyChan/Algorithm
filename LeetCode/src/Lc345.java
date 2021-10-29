import java.util.Arrays;
import java.util.HashSet;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc345
 * @Description: [345. Reverse Vowels of a String 反转字符串中的元音字符]
 * @Author: [clh]
 * @Date: 2021/10/27 9:12
 **/
public class Lc345 {
    //1 我自己写的
    public String reverseVowels(String s) {
        if(s.length() < 2)
            return s;
        char[] c = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        while(l < r){
            char cl = c[l];
            char cr = c[r];
            if(check(cl) && check(cr)){
                char tmp = cl;
                c[l] = c[r];
                c[r] = tmp;
                l++;
                r--;
                continue;
            }
            if(check(cl) && !check(cr)){
                r--;
                continue;
            }
            if(!check(cl) && check(cr)){
                l++;
                continue;
            }
            if(!check(cl) && !check(cr)){
                l++;
                r--;
                continue;
            }
        }
        return new String(c);
    }

    private boolean check(char c) {
        //小心大小写！ a A是不一样的 都属于元音
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    //2 github 双指针+hashset
    private final static HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a','e','i','o', 'u', 'A', 'E', 'I', 'O', 'U'));
    public String solution2(String s){
        if(s == null) return null;
        int i = 0, j = s.length() - 1;
        char[] c = new char[s.length()];
        while(i < j){
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if(!vowels.contains(ci)){
                c[i] = ci;
                i++;
            }else if(!vowels.contains(cj)){
                c[j] = cj;
                j++;
            }else{
                c[i] = cj;
                c[j] = ci;
                i++;
                j--;
            }
        }
        return new String(c);
    }
}

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc242
 * @Description: [242. Valid Anagram (Easy) 两个字符串包含的字符是否完全相同]
 * @Author: [clh]
 * @Date: 2021/11/23 21:08
 **/
public class Lc242 {
    //有一个很长的案例一直通不过，我也不知道是为什么
    public boolean isAnagram(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < len1; i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();
        for(int i = 0; i < len2; i++){
            char c = t.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0)+1);
        }
        if(map.size() != map2.size())
            return false;
        for(Character c: map.keySet()){
            if(map.get(c) != map2.get(c))
                return false;
        }
        return true;
    }

    //2 github
    //如果只是由英文26个小写字母构成，就不用HashMap 用长度为26的数组即可
    public boolean isAnagram2(String s, String t){
        int[] cnts = new int[26];
        for(char c: s.toCharArray()){
            cnts[c-'a']++;
        }
        for(char c: t.toCharArray()){
            cnts[c - 'a'] --;
        }
        for(int cnt: cnts){
            if(cnt != 0){
                return false;
            }
        }
        return true;
    }

    //3 排序 leetcode-cn
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }


}

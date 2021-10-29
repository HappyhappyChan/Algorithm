import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc524
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/10/27 15:02
 **/
public class Lc524 {
    //我自己写的 虽然很长 但是还是通过了！！！
    public String findLongestWord(String s, List<String> dictionary) {
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < s.length(); i++){
            char a = s.charAt(i);
            if(!set.contains(a))
                set.add(a);
        }
        int p1 = 0;
        int p2 = 0; //用来遍历字典里字符串的位置
        int ret = 0; //用来记录索引
        int max = 0; //记录字符串长度
        for(int i = 0; i < dictionary.size(); i++){
            String tmp = dictionary.get(i);
            p2 = 0;
            p1 = 0;
            while(p2 < tmp.length() && p1 < s.length()){
                char a1 = s.charAt(p1);
                char a2 = tmp.charAt(p2);
                if(!set.contains(a2)){
                    break;
                }
                if(a1 == a2){
                    p2++;
                    p1++;
                }else{
                    p1++;
                }
            }
            if(p2 == tmp.length()){
                if(tmp.length() > max){
                    max = tmp.length();
                    ret = i;
                }else if(tmp.length() == max){
                    ret = dictionary.get(ret).compareTo(tmp) < 0? ret : i;
                }
            }
        }
        return max == 0 ? new String() : dictionary.get(ret);
    }

    // 2 github
    public String findLongestWord2(String s, List<String> d) {
        String longestWord = "";
        for(String target: d){
            int l1 = longestWord.length(), l2 = target.length();
            if(l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)){
                continue;
            }
            if(isSubstr(s, target)){
                longestWord = target;
            }
        }
        return longestWord;
    }

    private boolean isSubstr(String s, String target) {
        int i = 0, j = 0;
        while(i < s.length() && j < target.length()){
            if(s.charAt(i) == target.charAt(j)){
                j++;
            }
            i++;
        }
        return j == target.length();
    }

    // 3 leetcode 先排序后遍历
    //然后判断是否是子串也可以用solution 2里面的方法isSubstr
    public String findLongestWord3(String s, List<String> d){
        Collections.sort(d, new Comparator<String>() {
            public int compare(String s1, String s2){
                return s2.length() != s1.length() ? s2.length() - s1.length() : s1.compareTo(s2);
            }
        });
        for(String str: d){
            if(isSubstr(s, str))
                return str;
        }
        return "";
    }


}

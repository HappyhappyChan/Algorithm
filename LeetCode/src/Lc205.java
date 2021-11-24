import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc205
 * @Description: [205. Isomorphic Strings (Easy) 字符串同构]
 * @Author: [clh]
 * @Date: 2021/11/24 10:37
 **/
public class Lc205 {
    //有bug
    //[badc, baba]
    //debug 半天终于没问题了
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null)
            return false;
        int len1 = s.length(), len2 = t.length();
        if(len1 != len2){
            return false;
        }
        int p1 = 0, p2 = 0;
        int[] map = new int[256];
        int[] flag = new int[256]; //用来检查是否被映射过
        Arrays.fill(map, -1);
        Arrays.fill(flag, -1);
        while(p1 < len1){
            char c1 = s.charAt(p1);
            char c2 = t.charAt(p2);
            if(c1 != c2){
                int replace = map[c1];
                if(replace != -1){
                    if(replace != (int)(c2))
                        return false;
                }else{
                    if(flag[c2] != -1){
                        return false;
                    }else{
                        map[c1] = (int)(c2);
                        flag[c2] = c1;
                    }

                }
            }else{
                //[egcd] [adfd]
                //[a][a]
                //[aaaa] [aaaa]
                if(flag[c1] != -1 && flag[c1] != c1)
                    return false;
                if(map[c1] == -1) {
                    map[c1] = (int) c1;
                    flag[c1] = c1;
                }
                else{
                    if(map[c1] != c2)
                        return false;
                }
            }
            p1++;
            p2++;
        }
        return true;
    }

    //2 leetcode solution
    public boolean isIsomorphic2(String s, String t) {

        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }

            // Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both
            else if (!(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)) {
                return false;
            }
        }

        return true;
    }

    //3 leetcode solution 2 transformation
    private String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (!indexMapping.containsKey(c1)) {
                indexMapping.put(c1, i);
            }

            builder.append(Integer.toString(indexMapping.get(c1)));
            builder.append(" ");
        }
        return builder.toString();
    }

    public boolean isIsomorphic3(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    //leetcode comment
    public boolean isIsomorphic4(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> assignedValues = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
            if (!map.containsKey(s.charAt(i)) && assignedValues.contains(t.charAt(i))) {
                return false;
            }
            map.put(s.charAt(i), t.charAt(i));
            assignedValues.add(t.charAt(i));
        }
        return true;
    }
}

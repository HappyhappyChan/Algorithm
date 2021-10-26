import java.util.BitSet;
import java.util.HashMap;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz34
 * @Description: [第一个只出现一次的字符]
 * @Author: [clh]
 * @Date: 2021/10/13 21:46
 **/
public class Jz34 {

    //mine 运用hashmap
    public int FirstNotRepeatingChar(String str) {
        if(str == null)
            return -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int cnt = map.getOrDefault(c,0);
            if(cnt > 0){
                map.replace(c, cnt, cnt+1);
            }else{
                map.put(c, 1);
            }
        }
        int index = 0;
        /**
         * keySet() 这个不是进入map的顺序不能这样
        for(char i: map.keySet()){
            if(map.get(i) == 1)
                return index;
            index++;
        }
         */
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int cnt = map.get(c);
            if(cnt == 1)
                return i;
        }
        return -1;
    }

    //solution 2 from github 用ASCII码
    public int FirstNotRepeatingChar2(String str) {
        int[] cnts = new int[128];
        for (int i = 0; i < str.length(); i++)
            cnts[str.charAt(i)]++;
        for (int i = 0; i < str.length(); i++)
            if (cnts[str.charAt(i)] == 1)
                return i;
        return -1;
    }

    //solution 3 from github 比特位
    public int FirstNotRepeatingChar3(String str) {
        //需要两个比特位 所以创建两个BitSet
        BitSet bs1 = new BitSet(128);
        BitSet bs2 = new BitSet(128);
        for (char c : str.toCharArray()) {
            //都没出现过 第一次出现
            if (!bs1.get(c) && !bs2.get(c))
                bs1.set(c);     // 0 0 -> 0 1
            //第一次出现过 第二次没出现 这里就是第二次出现
            else if (bs1.get(c) && !bs2.get(c))
                bs2.set(c);     // 0 1 -> 1 1
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //只要出现过一次的 其他不要
            if (bs1.get(c) && !bs2.get(c))  // 0 1
                return i;
        }
        return -1;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc763
 * @Description: [763. Partition Labels 分隔字符串使同种字符出现在一起]
 * @Author: [clh]
 * @Date: 2021/10/29 10:37
 **/
public class Lc763 {
    //我自己想的 看ipad笔记 通过啦！
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<Integer>();
        int len = s.length();
        if(len < 2){
            return new ArrayList(Arrays.asList(len));
        }
        int start = 0;
        int end = len - 1;
        if(s.charAt(start) == s.charAt(end))
            return new ArrayList(Arrays.asList(len));
        while(start < len){
            char l = s.charAt(start);
            end = s.lastIndexOf(l);
            int ret = check(s, start, end);
            //返回的是长度！
            list.add(ret - start + 1);
            start = ret + 1;
        }
        return list;
    }

    private int check(String s, int start, int end) {
        int l = start;
        int r = end;
        while(l < r && r < s.length()){
            char cl = s.charAt(l);
            int tmp = s.lastIndexOf(cl);
            r = r > tmp ? r : tmp;
            l++;
        }
        return r;
    }

    // 2 leetcode greedy
    //思路跟我一样 不过代码比我简洁
    public List<Integer> partitionLabels2(String s){
        //用来记录字母最后出现的次数
        int[] last = new int[26];
        for(int i = 0; i < s.length(); i++){
            last[s.charAt(i) - 'a'] = i;
        }
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i < s.length(); i++){
            //如果是abc i = 1 j = 1 ans.add(1-1+1) 没错 就是对的
            //多读题目
            j = Math.max(j, last[s.charAt(i) - 'a']);
            if(i == j){
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}

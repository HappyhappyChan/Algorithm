import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc17
 * @Description: [17. Letter Combinations of a Phone Number]
 * @Author: [clh]
 * @Date: 2021/11/1 10:09
 **/
public class Lc17 {
    // 1 github backtracking
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if(digits == null || digits.length() == 0)
            return ret;
        doCombination(new StringBuilder(), ret, digits);
        return ret;
    }

    private void doCombination(StringBuilder prefix, List<String> ret, String digits) {
        if(prefix.length() == digits.length()){
            ret.add(prefix.toString());
            return;
        }
        String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        int curDigit = digits.charAt(prefix.length()) - '0';
        String letter = keys[curDigit];
        for(char c: letter.toCharArray()){
            //a
            prefix.append(c);   //添加
            //ad
            doCombination(prefix, ret, digits);
            //a
            prefix.deleteCharAt(prefix.length() - 1); //删除
        }
    }

    // 2 leetcode discussion fifo queue
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //为了能遍历第一个字符
        ans.add("");
        for(int i = 0; i < digits.length(); i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length() == i){
                //删除并返回第一个元素。
                String t = ans.remove();
                for(char s: map[x].toCharArray()){
                    ans.add(t+s);
                }
            }
        }
        return ans;
    }

    //3 leetcode discussion fifo bfs
    public List<String> letterCombinations3(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while(ans.peek().length() != digits.length()){
            String remove = ans.remove();
            String m = map[digits.charAt(remove.length()) - '0'];
            for(char c: m.toCharArray()){
                //元素添加到尾部
                ans.addLast(remove+c);
                //试过了 用add也可以
            }
        }
        return ans;
    }

}

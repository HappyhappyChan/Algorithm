import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz38
 * @Description: [字符串的排列]
 * @Author: [clh]
 * @Date: 2021/10/23 15:25
 **/
public class Jz38 {

    private ArrayList<String> ret = new ArrayList<String>();

    //solution 1 github 回溯
    public ArrayList<String> Permutation(String str) {
        if(str.length() == 0)
            return ret;
        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        backtracking(ch, new boolean[ch.length], new StringBuilder());
        return ret;
    }

    private void backtracking(char[] ch, boolean[] visited, StringBuilder s) {
        if(s.length() == ch.length){
            ret.add(s.toString());
            return;
        }
        for(int i = 0; i < ch.length; i++){
            if(visited[i])
                continue;
            //保证不重复
            //这说明如果1 2一样 若1没用过 2也不能用？
            //比如 a1 a2 b
            //a1肯定先遍历 就会有a1 a2 b存进去，到了以a2为首开始遍历 但因为a1 false
            //所以a2不能开头，这样就避免了重复吧
            if(i != 0 && ch[i] == ch[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            s.append(ch[i]);
            backtracking(ch, visited, s);
            s.deleteCharAt(s.length() - 1);
            visited[i] = false;
        }
    }

    //solution 2 递归 bilibili
    public ArrayList<String> solution2(String str){
        char[] a = str.toCharArray();
        ArrayList<String> ans = new ArrayList<String>();
        //进行去重
        ans = new ArrayList<String>(new HashSet<String>(ans));
        solve(ans, a, 0, str.length());
        //要求按字典顺序排列
        //其实也等价于ans.sort(null) 这里写null就默认升序排列
        Collections.sort(ans);
        return ans;
    }

    private void solve(ArrayList<String> ans, char[] a, int index, int len){
        if(index == len - 1){
            String res = String.valueOf(a);
            ans.add(res);
        }else{
            for(int i = index; i < len; i++){
                char tmp = a[i];
                a[i] = a[index];
                a[index] = tmp;
                solve(ans, a, index + 1, len);
                //原本abc 经过上面交换变成bac 但头要固定啊 下次还是要从最初的头后面加
                tmp = a[i];
                a[i] = a[index];
                a[index] = tmp;
            }
        }
    }
}

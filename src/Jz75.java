import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz75
 * @Description: [字符流中第一个不重复的字符]
 * @Author: [clh]
 * @Date: 2021/10/14 19:53
 **/
public class Jz75 {

    //我主要是用了跟34题类似的方法
    //但是newcoder会在StringBuilder报错提示找不到文件
    //根据上面 我觉得这题要考的是不能用一个字符串来记录
    //用来记录字符出现次数
    private int[] arr = new int[128];
    //用来记录插入的字符
    private StringBuilder s = new StringBuilder();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        int org = arr[ch];
        arr[ch] = org + 1;
        s.append(ch);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for(int i = 0; i < s.length(); i++){
            if(arr[s.charAt(i)] == 1)
                return s.charAt(i);
        }
        return '#';
    }

    //solution 2 from github
    private int[] cnts = new int[128];
    private Queue<Character> que = new LinkedList<>();

    public void Insert2(char ch){
        cnts[ch]++;
        if(cnts[ch] == 1){
            que.add(ch);
        }else{
            que.remove(ch);
        }
    }

    public char FirstAppearingOnce2(){
        if(que.isEmpty())
            return '#';
        //不能用poll  要用peek
        return que.peek();
    }
}

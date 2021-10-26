import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Jz54
 * @Description: [请实现一个函数用来找出字符流中第一个只出现一次的字符。]
 * @Author: [clh]
 * @Date: 2021/10/10 16:34
 **/
public class Jz50 {
    private int[] cnt = new int[128];
    private Queue<Character> que = new LinkedList<>();
    public void Insert(char ch){
        cnt[ch]++;
        que.add(ch);
        while(!que.isEmpty() && cnt[que.peek()] > 1)
            que.poll();
    }

    public char FirstAppearingOnce(){
        return que.isEmpty()? '#' : que.peek();
    }

    //from newcoder
    public void Insert2(char ch){
        if(cnt[ch]++ == 0){ //这个++不能写在{}里面！
            que.add(ch);
        }
    }

    public char FirstAppearingOnce2(){
        Character res = null;
        char c = 0;
        while((res = que.peek()) != null){
            c = res.charValue();
            //队列只阻止了非单身字符的第2~n次入队，没有阻止第一次，出队时才能判断是否真的是单身字符
            //终于懂了！用"google"这个例子分析 如果少了判断cnt==1 就会输出gggggg
            //正确的是输出"ggg#ll"
            if(cnt[c] == 1) //这一个也不能少！
                return c;
            else que.remove();
        }
        return '#';
    }
}

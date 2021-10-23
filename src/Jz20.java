import java.util.Stack;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz20
 * @Description: [包含min函数的栈]
 * @Author: [clh]
 * @Date: 2021/10/13 23:07
 **/
public class Jz20 {
    //一个栈存入栈的
    private Stack<Integer> stack1 = new Stack<>();
    //一个栈存当前最小的
    private Stack<Integer> stack2 = new Stack<>();

    //我写的！
    public void push(int node) {
        if(!stack2.isEmpty()){
            int tmp = stack2.peek();
            if(node < tmp){
                stack2.push(node);
            }else{
                stack2.push(tmp);
            }
        }else{
            stack2.push(node);
        }
        stack1.push(node);
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }

    //solution 2 from github
    //这种运行的速度和占用内存都比我的好
    public void solution2(int node) {
        stack1.push(node);
        stack2.push(stack2.isEmpty() ? node : Math.min(stack2.peek(), node));
    }

    //solution 3 from newcoder comments
    //采用单个栈
    //这个效率还是没有 solution 2的好
    //需要这样写来初始化stack，不然会报空指针push的时候
    private static Stack<Integer> stack3 = new Stack<Integer>();
    private static Integer minElement = Integer.MAX_VALUE;

    public void push3(int node) {
        if(stack3.empty()){
            minElement = node;
            stack3.push(node);
        }else{
            if(node <= minElement){
                stack3.push(minElement);//在push更小的值时需要保留在此值之前的最小值
                minElement = node;
            }
            stack3.push(node);
        }
    }

    public void pop3() {

        //增加最后一个元素以及栈为空时候的检测
        if(stack3.size() == 0)return;
        if( minElement == stack3.peek()){
            if(stack3.size() >1){
                stack3.pop();
                minElement = stack3.peek();
            }else{
                minElement = Integer.MAX_VALUE;
            }

        }
        stack3.pop();
    }

    public int top3() {
        return stack3.peek();
    }

    public int min3() {
        return minElement;
    }

}

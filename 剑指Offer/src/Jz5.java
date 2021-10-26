import java.util.Stack;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz5
 * @Description: [ 用两个栈实现队列]
 * @Author: [clh]
 * @Date: 2021/10/13 22:46
 **/
public class Jz5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    //["PSH2","PSH3","POP","PSH1","POP","POP"]
    // 2,3,1
    //上面这种情况容易忽视
    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int ret =  stack2.pop();
        //其他没pop的要倒回去 要不然后面又push进来的位置不对
        while(!stack2.isEmpty()){
            push(stack2.pop());
        }
        return ret;
    }

    //solution 2 from github
    //我多次一举 其实不用把stack2没pop的倒回去 只要stack2不空 又要pop的话
    //肯定先pop 有的
    public int solution() throws Exception{
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());

        if (stack2.isEmpty())
            //题目要求：保证pop操作时队列内已有元素
            throw new Exception("queue is empty");

        return stack2.pop();
    }
}

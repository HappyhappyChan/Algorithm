import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc232
 * @Description: [232. Implement Queue using Stacks (Easy) 用栈实现队列]
 * @Author: [clh]
 * @Date: 2021/11/22 19:40
 **/
public class Lc232 {
    class MyQueue {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();
        public MyQueue() {

        }

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            in2out();
            return out.pop();
        }
        private void in2out(){
            if(out.isEmpty()){
                while(!in.isEmpty()){
                    out.push(in.pop());
                }
            }
        }
        public int peek() {
            in2out();
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }
}

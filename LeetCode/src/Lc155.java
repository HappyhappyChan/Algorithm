import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc155
 * @Description: [155. Min Stack (Easy) 最小值栈]
 * @Author: [clh]
 * @Date: 2021/11/23 10:08
 **/
public class Lc155 {
    class MinStack {

        /** initialize your data structure here. */
        Stack<Integer> in = new Stack<>();
        Stack<Integer> min = new Stack<>();
        public MinStack() {

        }

        public void push(int x) {
            in.push(x);
            if(!min.isEmpty()){
                int pre = min.peek();
                min.push(pre <= x? pre : x);
            }else{
                min.push(x);
            }
        }

        public void pop() {
            in.pop();
            min.pop();
        }

        public int top() {
            return in.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }
}

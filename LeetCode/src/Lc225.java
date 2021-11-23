import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc225
 * @Description: [225. Implement Stack using Queues (Easy) 用队列实现栈]
 * @Author: [clh]
 * @Date: 2021/11/23 9:53
 **/
public class Lc225 {
    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
    class MyStack {

        Queue<Integer> que;
        public MyStack() {
            que = new LinkedList<>();
        }

        public void push(int x) {
            que.add(x);
            int cnt = que.size();
            while(cnt -- > 1){
                que.add(que.poll());
            }
        }

        public int pop() {
            return que.remove();
        }

        public int top() {
            return que.peek();
        }

        public boolean empty() {
            return que.isEmpty();
        }
    }


}

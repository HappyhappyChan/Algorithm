import java.util.Stack;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz21
 * @Description: [栈的压入弹出序列]
 * @Author: [clh]
 * @Date: 2021/10/13 23:27
 **/
public class Jz31 {
    private Stack<Integer> stack = new Stack<>();
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        int j = 0;
        for(int i = 0; i < pushA.length; i++) {
            //要用while 因为有可能一直弹出
            //[1,2,3,4,5] -- [4,3,5,2,1]
            while (!stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
            stack.push(pushA[i]);
        }

        //这一段还是要有的 因为我是先判断要不要pop然后再输入
        //如果是先输入再判断要不要pop就不用下面这一段了
        while(j < pushA.length){
            if(stack.pop() != popA[j]){
                return false;
            }
            j++;
        }

        return stack.isEmpty();
    }

    //solution 2 from github
    public boolean solution2(int [] pushSequence,int [] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            while (popIndex < n && !stack.isEmpty()
                    && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}

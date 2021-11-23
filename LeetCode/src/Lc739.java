import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc739
 * @Description: [739. Daily Temperatures (Medium) 数组中元素与下一个比它大的元素之间的距离]
 * @Author: [clh]
 * @Date: 2021/11/23 10:32
 **/
public class Lc739 {
    //1 暴力
    public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0)
            return null;
        int len = temperatures.length;
        int[] ret = new int[len];
        for(int i = 0; i < len; i++){
            for(int j = i+1; j < len; j++){
                if(temperatures[j] > temperatures[i]){
                    ret[i] = j-i;
                    break;
                }
            }
        }
        return ret;
    }

    //2 根据github分类到栈 尝试用栈
    public int[] dailyTemperatures2(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0)
            return null;
        int len = temperatures.length;
        int[] ret = new int[len];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < len; i++){
            if(s.isEmpty()){
                s.push(i);
            }else{
                int top = s.peek();
                while(temperatures[top] < temperatures[i]){
                    ret[top] = i - top;
                    s.pop();
                    if(s.isEmpty()){
                        break;
                    }else{
                        top = s.peek();
                    }
                }
                s.push(i);
            }
        }
        return ret;
    }

    //3 github
    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;
        int[] dist = new int[n];
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            while (!indexs.isEmpty() && temperatures[curIndex] > temperatures[indexs.peek()]) {
                int preIndex = indexs.pop();
                dist[preIndex] = curIndex - preIndex;
            }
            indexs.add(curIndex);
        }
        return dist;
    }

    //4 leetcode solution
    public int[] dailyTemperatures4(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int currDay = 0; currDay < n; currDay++) {
            int currentTemp = temperatures[currDay];
            // Pop until the current day's temperature is not
            // warmer than the temperature at the top of the stack
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                int prevDay = stack.pop();
                answer[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }

        return answer;
    }

    //5
    public int[] dailyTemperatures5(int[] temperatures) {
        int n = temperatures.length;
        int hottest = 0;
        int answer[] = new int[n];

        for (int currDay = n - 1; currDay >= 0; currDay--) {
            int currentTemp = temperatures[currDay];
            //注意 >=
            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue;
            }

            int days = 1;
            //这个等号很重要
            while (temperatures[currDay + days] <= currentTemp) {
                // Use information from answer to search for the next warmer day
                days += answer[currDay + days];
            }
            answer[currDay] = days;
        }

        return answer;
    }
}

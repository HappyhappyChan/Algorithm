import java.util.Arrays;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc503
 * @Description: [503. Next Greater Element II (Medium) 循环数组中比当前元素大的下一个元素]
 * @Author: [clh]
 * @Date: 2021/11/23 14:53
 **/
public class Lc503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!s.isEmpty() && nums[i] > nums[s.peek()]){
                ans[s.pop()] = nums[i];
            }
            s.push(i);
        }
        for(int i = 0; i < n-1; i++){
            while(!s.isEmpty() && nums[i] > nums[s.peek()]){
                ans[s.pop()] = nums[i];
            }
            s.push(i);
        }
        return ans;
    }

    //2 github
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> pre = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!pre.isEmpty() && nums[pre.peek()] < num) {
                next[pre.pop()] = num;
            }
            if (i < n){
                pre.push(i);
            }
        }
        return next;
    }

    //3 leetcode
    public int[] nextGreaterElements3(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = 1; j < nums.length; j++) {
                if (nums[(i + j) % nums.length] > nums[i]) {
                    res[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }
        return res;
    }

    //4 leetcode
    public int[] nextGreaterElements4(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }
}

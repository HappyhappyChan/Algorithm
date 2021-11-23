import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc20
 * @Description: [20. Valid Parentheses (Easy) 用栈实现括号匹配]
 * @Author: [clh]
 * @Date: 2021/11/23 10:18
 **/
public class Lc20 {
    public boolean isValid(String s) {
        Stack<Character> data = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                data.push(c);
            }else if(c == ')'){
                char top = data.isEmpty() ? 'a' : data.pop();
                if(top != '(')
                    return false;
            }else if(c == ']'){
                char top = data.isEmpty() ? 'a' : data.pop();
                if(top != '[')
                    return false;
            }else if(c == '}'){
                //"]"
                char top = data.isEmpty() ? 'a' : data.pop();
                if(top != '{')
                    return false;
            }else{
                return false;
            }
        }
        //"("
        return data.isEmpty();
    }

    //github
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char cStack = stack.pop();
                boolean b1 = c == ')' && cStack != '(';
                boolean b2 = c == ']' && cStack != '[';
                boolean b3 = c == '}' && cStack != '{';
                if (b1 || b2 || b3) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

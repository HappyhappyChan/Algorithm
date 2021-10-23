import java.util.Stack;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz44
 * @Description: [翻转单词顺序列]
 * @Author: [clh]
 * @Date: 2021/10/11 10:37
 **/
public class Jz73 {
    //任何使用了额外空间的解法在面试时都会大打折扣，包括递归解法。
    //先翻转每个单词，再翻转整个字符串。
    //“student. a am I”。正确的输出应该是“I am a student.”

    //解法1 from newcoder 两次翻转 先翻转整个字符串 再翻转单词
    public String ReverseSentence1(String str) {
        if(str == null || str.length() == 0)
            return str;
        //转成字符数组
        char[] arr = str.toCharArray();
        //翻转整个字符串
        //“student. a am I” -> I ma a .tenduts
        reverse(arr, 0, arr.length - 1);

        //头指针 单词第一个字母
        int start = 0;
        //尾指针 单词最后一个字母
        int end = 0;

        while(start < arr.length){
            if(arr[start] == ' '){
                //指到空格说明要换下一个 应该要指向单词
                start++;
                end++;
            }else if(end == arr.length || arr[end] == ' '){
                //要么尾部是空格，要么end刚刚超过数组最后一个角标，这两种情况就应该翻转了
                //end之所以会超过最后角标，是因为当最后一个字符不是空格时，会让end++，所以会越界
                reverse(arr, start, end-1);
                //翻转后，应该重新记录下个单词，因此更新end和start。
                end++;
                start = end;//这句和上一句可以写成start = ++end;
            }else{
                //到这里就说明，start指的不是空格，end也指的不是空格，说明是正常单词，end++即可
                end++;
            }
        }
        return String.valueOf(arr);

    }

    private void reverse(char[] arr, int i, int j) {
        while(i < j){
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    //解法2 from newcoder 用栈实现 我一开始也想到用栈
    public String ReverseSentence2(String str) {
        if(str == null || str.length() == 0){
            return str;
        }
        StringBuilder res  = new StringBuilder();
        //用空格分隔 就可以防止到时候还要将单词再转一次
        String[] tmp = str.split(" ");

        //防止出现全是空格
        if(tmp.length == 0)
            return str;
        Stack<String> stack = new Stack<String>();

        //因为最后一个不用空格
        for(int i = 0; i < tmp.length - 1; i++){
            stack.push(tmp[i]);
            //单词之间要用空格隔开
            stack.push(" ");
        }

        stack.push(tmp[tmp.length-1]);

        while(!stack.isEmpty()){
            res.append(stack.pop());
        }
        return res.toString();
    }

    //解法3 from github 先翻转每个单词 然后再翻转字符串
    public String ReverseSentence3(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reverse3(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse3(chars, 0, n - 1);
        return new String(chars);
    }

    private void reverse3(char[] c, int i, int j) {
        while (i < j)
            swap(c, i++, j--);
    }

    private void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }

}

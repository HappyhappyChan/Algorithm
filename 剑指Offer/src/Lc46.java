/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc46
 * @Description: [把数字翻译成字符串]
 * @Author: [clh]
 * @Date: 2021/10/26 13:56
 **/
public class Lc46 {
    // 1 leetcode cn
    public int solution1(int num){
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = 2; i <= s.length(); i++){
            String tmp = s.substring(i-2, i);
            int c = tmp.compareTo("10")>=0 && tmp.compareTo("25") <= 0? a + b : a;
            //dp[i - 2]
            b = a;
            //dp[i - 1]
            a = c;
        }
        return a;
    }

    // 2 leetcode cn
    //从右向左遍历
    public int solution2(int num){
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = s.length() - 2; i > -1; i++){
            String tmp = s.substring(i, i + 2);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }

    // 3 leetcode cn
    //数字取余
    public int solution3(int num){
        int a = 1, b = 1, x, y = num % 10;
        while(num != 0){
            num /= 10;
            x = num % 10;
            int tmp = 10*x + y;
            int c =  (tmp >= 10 && tmp <= 25) ? a + b : a;
            b = a;
            a = c;
            y = x;
        }
        return a;
    }

    // 4 leetcode cn
    public int translateNum(int num) {
        String s = Integer.toString(num);
        //比如数字12有两种跳法，12能匹配字母，因此dp[2]=dp[1]+dp[0]
        //而因为数字为一位的时dp[1]=1，因此推出dp[0]=1
        int a=1,b=1;
        if (s.length()==0||s.length()==1){
            return 1;
        }
        //从dp[2]开始判断
        for (int i = 2; i <= s.length(); i++) {
            //判断当前字符串i下标处最后两位数字是否>=10且<=25
            //因为<10走一步和走两步的翻译结果一样，>25只能一步一步走，因此这两种情况都只能dp[n]=dp[n-1]
            if (s.substring(i-2,i).compareTo("10")>=0&&s.substring(i-2,i).compareTo("25")<=0){
                a=a+b;
                b=a-b;
            }else {
                //dp[n]=dp[n-1]的情况
                //a=a,b=a 往后移一位
                b=a;
            }
        }
        return a;
    }
}

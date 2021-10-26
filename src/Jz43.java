/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz43
 * @Description: [从 1 到 n 整数中 1 出现的次数]
 * @Author: [clh]
 * @Date: 2021/10/25 13:51
 **/
public class Jz43 {
    //1 暴力法 bilibili
    public int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i++){
            //这里不能直接用i呀 要将i存在x 用x去拆分
            //因为后面还要用到i！
            int x = i;
            while(x != 0){
                if(x % 10 == 1)
                    sum++;
                x /= 10;
            }
        }
        return sum;
    }

    //2 bilibili
    public int NumberOf1Between1AndN_Solution2(int n) {
        if (n == 0) {
            return 0;
        }
        String str = "" + n;
        int len = str.length();
        if (len == 1) {
            return 1;
        }
        //245 res = 100
        int res = (int) Math.pow(10, len - 1); // 是获取当前n的幂级
//        int firstNumber = str.charAt(0) - '0';
        //245 firstNum = 2
        int firstNumber = n / res;
        //145 firstNum = 1 --> firstBit = 145 % 100 + 1 = 46. 及从100-145 共46个数含有1
        //245 firstBit = 100 100-199 共100个数
        int firstBit = firstNumber == 1 ? (n % res) + 1 : res;
        //245 2*2*100 / 10 = 40
        //145 2*1*100 / 10 = 20
        //看ipad上解析
        int otherBit = (len - 1) * firstNumber * res / 10; //(len - 1)的意思就是剩余位的个数(C(len-1, 1) -> 从剩余的len-1位中选取一位来作为1)，res/10的意思就是剩余的len-2位可能出现的情况
        return firstBit + otherBit + NumberOf1Between1AndN_Solution(n % res);
    }

    //solution 3 nowcoder
    // 这里假设n>=1000的情况 只计算百位数的情况
    public int NumberOf1Between1AndN_Solution3(int n) {
        int count = 0, bitNum = 100, high = n / 100, cur = (n / 10) % 10, low = n % 10;
        if(cur == 0) {
            // case 1: cur == 0
            // cur=0时，高位需要减去一位用于低位进行计算
            // 相当于 count = (high - 1) * bitNum + (99 + 1)
            count += high * bitNum;
        } else if(cur == 1) {
            // case 2: cur == 1
            // 相当于高位+低位计算结果，即(high * bitNum) + (low + 1)
            count += high * bitNum + low + 1;
        } else {
            // case3: cur > 1
            // 相对于cur=0的情况，就不需要高位减去一位来计算低位的结果数了
            // 相当于(high * bitNum) + (低位数结果数)
            count += (high + 1) * bitNum;
        }
        return count;
    }

    //从上面的只计算百位数到下面的各个位数都考虑
    public int NumberOf1Between1AndN_Solution4(int n) {
        int count = 0, bitNum = 1, high = n / 10, cur = n % 10, low = 0;
        // cur遍历n每一数位
        while(cur != 0 || high != 0) {
            if(cur < 1) {
                // case 1: cur == 0
                // cur=0时，高位需要减去一位用于低位进行计算
                // 相当于 count = (high - 1) * bitNum + (99 + 1)
                count += high * bitNum;
            } else if(cur == 1) {
                // case 2: cur == 1
                // 相当于高位+低位计算结果，即(high * bitNum) + (low + 1)
                count += high * bitNum + low + 1;
            } else {
                // case3: cur > 1
                // 相对于cur=0的情况，就不需要高位减去一位来计算低位的结果数了
                // 相当于(high * bitNum) + (低位数结果数)
                count += (high + 1) * bitNum;
            }
            // low、cur、high都像左偏移一个位
            // bitNum表示cur的数位
            low += cur * bitNum;
            cur = high % 10;
            high = high / 10;
            bitNum = bitNum * 10;
        }
        return count;
    }
}

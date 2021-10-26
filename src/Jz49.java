import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz49
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/10/24 16:46
 **/
public class Jz49 {
    //solution 1 nowcoder
    public int GetUglyNumber_Solution(int index) {
        // 1 2 3 4 5 6 8
        if(index <= 6)
            return index;
        // 三个变量 后面有大作用！
        int i2 = 0,i3 = 0,i5 = 0;
        int[] res = new int[index];
        res[0] = 1;  // 第一个丑数为 1

        for(int i = 1; i < index; i++){
            // 得到下一个丑数，三者中最小的
            res[i] = Math.min(res[i2]*2,Math.min(res[i3]*3,res[i5]*5));
            /*第一次是 2、3、5比较，得到最小的是2*/
            /*第二次是 4、3、5比较，为什么是4了呢？因为上次2已经乘了一次了，所以接下去可以放的丑数在4、3、5之间*/
            // 所以开头的三个指针就是来标记2 3 5 乘的次数的
            if(res[i] == res[i2]*2)
                i2++;
            if(res[i] == res[i3]*3)
                i3++;
            if(res[i] == res[i5]*5)
                i5++;
        }
        return res[index-1];
    }

    //solution 2 brute force nowcoder
    public int solution2(int index){
        int now = 1;
        int[] res = new int[index];
        int cnt = 0;
        while(cnt < index){
            if(check(now)){
                res[cnt] = now;
                cnt++;
            }
            now++;
        }
        return res[cnt - 1];
    }

    private boolean check(int x) {
        while(x % 2 == 0) x /= 2;
        while(x % 3 == 0) x /= 3;
        while(x % 5 == 0) x /= 5;
        return x == 1;
    }

    //solution 3 nowcoder set + priorityqueue
    //给的代码是cpp的 所以没写
    public int solution3(int idx){
       return 0;
    }
}

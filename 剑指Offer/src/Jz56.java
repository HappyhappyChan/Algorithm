import java.util.HashMap;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz56
 * @Description: [JZ56 数组中只出现一次的两个数字]
 * @Author: [clh]
 * @Date: 2021/10/25 19:45
 **/
public class Jz56 {
    public int[] FindNumsAppearOnce (int[] array) {
        // write code here
        int[] ret = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int x : array){
            map.put(x, map.getOrDefault(x, 0) +1);
        }
        int cnt = 0;
        for(int x: map.keySet()){
            if(map.get(x) == 1){
                ret[cnt] = x;
                cnt++;
            }
            if(cnt == 2)
                break;
        }
        if(ret[0] > ret[1]){
            int tmp = ret[1];
            ret[1] = ret[0];
            ret[0] = tmp;
        }
        return ret;
    }

    //2 bilibili
    public int[] solution2(int[] array){
        int[] ret = {0, 0}; //初始化为0 因为0和任何数异或得到的都是数本身
        if(array.length != 0){
            int ans = 0;
            //通过第一个循环找到那2个出现过1次的数字的异或结果
            for(int x: array){
                ans ^= x;
            }
            //根据这个异或结果去找到分割出这两个数字的方式
            //从右往左找到第一个位置不为0的地方
            int lastOne = find(ans);
            for(int x : array){
                if(judge(x, lastOne) == 0){
                    ret[0] ^= x;
                }else{
                    ret[1] ^= x;
                }
            }
        }

        if(ret[0] > ret[1]){
            int tmp = ret[1];
            ret[1] = ret[0];
            ret[0] = tmp;
        }
        return ret;
    }

    private int judge(int x, int lastOne){
        //判断x的从右往左看，第lastOne是否为1
        x >>= (lastOne - 1); //将x的lastone位移到最右边
        return x & 1;
    }

    private int find(int ans) {
        int sum = 1;
        int res = 1;
        while((ans & res) == 0){
            sum++;
            //res 1-> 10 -> 100
            res <<= 1;
        }
        return sum;
    }

    // 3 github
    public int[] solution3(int[] nums) {
        int[] ret = new int[2];
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        diff &= -diff;
        for (int num : nums) {
            if ((num & diff) == 0)
                ret[0] ^= num;
            else
                ret[1] ^= num;
        }
        if (ret[0] > ret[1]) {
            int tmp = ret[1];
            ret[1] = ret[0];
            ret[0] = tmp;
        }
        return ret;
    }
}

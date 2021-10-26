/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz42
 * @Description: [jz42 连续子数组的最大和]
 * @Author: [clh]
 * @Date: 2021/10/24 15:08
 **/
public class Jz42 {
    //1 brute force nowcoder
    public int solution1(int[] array){
        int ret = Integer.MIN_VALUE;
        int len = array.length;
        for(int i = 0; i < len; i++){
            for(int j = i; j < len; j++){
                int sum = 0;
                for(int k = i; k <= j; k++){
                    sum += array[k];
                }
                ret = sum > ret? sum : ret;
            }
        }
        return ret;
    }

    //2 前缀和优化
    //nowcoder
    public int solution2(int[] array){
        int ret = Integer.MIN_VALUE;
        int len = array.length;
        int[] sum = new int[len + 1];
        //sum[1]为arr[0] sum[0] = 0;
        int tmp = 0;
        for(int i = 1; i < len + 1; i++){
            tmp += array[i - 1];
            sum[i] = tmp;
        }
        //要小心[1]一个元素的情况
        for(int i = 0; i < len + 1; i++){
            //如果是自己-自己，出来为0 但是元素全为非负就错了
            for(int j = i + 1; j < len + 1; j++){
                tmp = sum[j] - sum[i];
                ret = ret > tmp ? ret : tmp;
            }
        }
        return ret;
    }

    //solution 3 nowcoder
    //dp
    public int solution3(int[] array){
        int num = 0;
        int ret = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            num = array[i] + (num > 0? num : 0);
            ret = ret > num ? ret : num;
        }
        return ret;
    }
}

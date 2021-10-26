import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz39
 * @Description: [数组中出现次数超过一半的数字]
 * @Author: [clh]
 * @Date: 2021/10/25 9:39
 **/
public class Jz39 {
    //1 brute force
    public int MoreThanHalfNum_Solution(int [] array) {
        int n = array.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int cnt = map.getOrDefault(array[i], 0);
            map.put(array[i], ++cnt);
            if(cnt > n/2){
                return array[i];
            }
        }
        return 0;
    }

    //1 bilibili 代码 比我的更简洁
    public int solution1(int[] array){
        Map<Integer, Integer> map = new HashMap<>();
        int target = 0;
        int sum = 0;
        for(int x : array){
            map.put(x, map.getOrDefault(x, 0) + 1);
           if(map.get(x) > sum){
               target = x;
               sum = map.get(x);
           }
        }
        return sum > array.length / 2? target : 0;
    }
    //2 github boyer-moore majority vote algorithm
    public int solution2(int[] nums){
        int maj = nums[0];
        //cnt = 前i次 maj出现的次数-maj没出现的次数
        //其实cnt是一个找众数的过程 所以找完之后还要判断>num.len/2
        for(int i = 1, cnt = 1; i < nums.length; i++){
            cnt = nums[i] == maj ? cnt + 1 : cnt - 1;
            if(cnt == 0){
                maj = nums[i];
                cnt = 1;
            }
        }
        int cnt = 0;
        for(int val : nums){
            if(val == maj)
                cnt++;
        }
        return cnt > nums.length / 2 ? maj : 0;
    }
}

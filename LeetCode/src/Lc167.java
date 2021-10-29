/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc167
 * @Description: [有序数组的 Two Sum]
 * @Author: [clh]
 * @Date: 2021/10/26 19:43
 **/
public class Lc167 {
    public int[] twoSum(int[] num, int target) {
        int[] ret = new int[2];
        for(int i = 0; i < num.length; i++){
            for(int j = i + 1; j < num.length; j++){
                if(num[i] + num[j] == target){
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return ret;
    }

    //2
    public int[] twoSum2(int[] num, int target) {
        int[] indice = new int[2];
        if (num == null || num.length < 2) return indice;
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return indice;
    }

    //3
    public int[] twoSum3(int[] num, int target) {
        int[] ret = new int[2];
        if(num.length < 2)
            return ret;
        int l = 0;
        int r = num.length - 1;
        while(l < num.length){
            int tmp = num[l] + num[r];
            if(tmp == target){
                ret[0] = l + 1;
                ret[1] = r + 1;
                return ret;
            }
            if(tmp > target){
                r--;
                continue;
            }
            if(tmp < target){
                l++;
                continue;
            }
        }
        return ret;
    }
}

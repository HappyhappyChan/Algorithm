/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz21
 * @Description: [调整数组顺序使奇数位于偶数前面]
 * @Author: [clh]
 * @Date: 2021/10/23 16:54
 **/
public class Jz21 {
    //Soution 1 暴力遍历
    public int[] reOrderArray (int[] array) {
        // write code here
        int index = 0; //用于记录最后一个奇数的位置
        if(array.length == 0)
            return array;
        int[] ret = new int[array.length];
        for(int i = 0; i < array.length; i++){
            if(array[i] % 2 == 1){
                ret[index++] = array[i];
            }
        }
        //再放入偶数
        for(int i = 0; i < array.length; i++){
            if(array[i] % 2 == 0){
                ret[index++] = array[i];
            }
        }
        return ret;
    }

    //solution 2 冒泡思想
    //github
    public int[] solution2(int[] nums) {
        int N = nums.length;
        for(int i = N - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(nums[j] % 2 == 0 && nums[j+1] % 2 == 1){
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
        return nums;
    }

    //solution 3 nowcoder
    //在原数组上修改 双指针
    public int[] solution3(int[] array){
        int i = 0;
        for(int j = 0; j < array.length; j++){
            //遇到奇数
            if(array[j] % 2 == 1){
                int tmp = array[j];
                //将[i, j-1]数组往后移
                for(int k = j - 1; k >= i; --k){
                    array[k+1] = array[k];
                }
                array[i++] = tmp;
            }
        }
        return array;
    }
}

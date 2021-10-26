/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1
 * @Description: [二维数组中的查找]
 * @Author: [clh]
 * @Date: 2021/10/13 20:32
 **/
public class Jz1 {
    public boolean Find(int target, int [][] array) {
        int up = 0;
        int left = 0;
        int down = array.length;
        //别忘记判断矩阵是否为空！
        if(down == 0) return false;
        int right = array[0].length;
        if(right == 0) return false;
        //还要注意这个down right是长度 不是最后的索引！
        while(up < down && left < right){
            if(target > array[down - 1][left]){
                left++;
                //少了这个continue就会报错！
                continue;
            }
            if(target < array[down - 1][left]){
                down--;
                //少了这个continue就会报错！
                continue;
            }
            if(target == array[down - 1][left]){
                return true;
            }
        }
        return false;
    }
}

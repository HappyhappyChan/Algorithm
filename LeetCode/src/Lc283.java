/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc283
 * @Description: [283. Move Zeroes (Easy) 把数组中的 0 移到末尾]
 * @Author: [clh]
 * @Date: 2021/11/24 19:18
 **/
public class Lc283 {
    //一个easy的题目……我搞了半天才弄好
    public void moveZeroes(int[] nums) {
        int cnt = 0; //用来记有多少个0
        int p1 = 0, p2 = 1;
        int n = nums.length;
        while(p2 < n){
            if(nums[p1] == 0) {
                while (p2 < n && nums[p2] == 0) {
                    p2++;
                    cnt++;
                }
                if (p2 == n) break;
                nums[p1++] = nums[p2];
                nums[p2++] = 0;
            }else{
                p1++;
                p2++;
            }
        }
        /*while(p1 < n){
            nums[p1++] = 0;
        }*/
    }

    //2 github
    public void moveZeroes2(int[] nums){
        int cnt = 0;
        int idx = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0)
                nums[idx++] = nums[i];
            else{
                cnt++;
            }
        }
        for(int i = 0; i < cnt; i++){
            nums[idx++] = 0;
        }
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc303
 * @Description: [303. Range Sum Query - Immutable 数组区间和]
 * @Author: [clh]
 * @Date: 2021/11/4 15:25
 **/
public class Lc303 {
    //要求要实现这个class 给的里面没有class 为了不报错 我自己加的void
    /*private int[] sums;
    public void NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for(int i = 1; i <= nums.length; i++){
            //sum[i] 为 0 ~ i - 1 的和。
            sums[i] = sums[i-1] + nums[i-1];
    }

    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }*/


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc88
 * @Description: [88. Merge Sorted Array 归并两个有序数组]
 * @Author: [clh]
 * @Date: 2021/10/27 10:02
 **/
public class Lc88 {
    //我好久之前写的
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1, tail2 = n - 1, finished = m + n - 1;
        while (tail1 >= 0 && tail2 >= 0) {
            nums1[finished--] = (nums1[tail1] > nums2[tail2]) ?
                    nums1[tail1--] : nums2[tail2--];
        }

        while (tail2 >= 0) { //only need to combine with remaining nums2
            nums1[finished--] = nums2[tail2--];
        }

    }

    //我这个有bug啊
    //4 5 6 000， 123 这种情况4和1换了之后 nums2变成了4 23 就不是按顺序了
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)
            return;
        int l = 0, r = 0;
        while(l < m){
            if(nums1[l] < nums2[r]){
                l++;
            } else if(nums1[l] == nums2[r]){
                int tmp = nums1[l+1];
                nums1[l+1] = nums2[r];
                nums2[r] = tmp;
                l++;
            }else{
                int tmp = nums1[l];
                nums1[l] = nums2[r];
                nums2[r] = tmp;
                l++;
            }
        }
        while(r < n){
            nums1[l++] = nums2[r++];
        }
    }

    //3 github 从尾开始遍历
    public static void merge3(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1, p2 = n - 1;
        int p3 = m + n - 1;
        while(p2 >= 0){
            if(p1 < 0){
                nums1[p3--] = nums2[p2--];
            }else if(p2 < 0){//测试发现这个根本用不到 删了也可以通过测试
                nums1[p3--] = nums1[p1--];
            }else if(nums1[p1] > nums2[p2]){
                nums1[p3--] = nums1[p1--];
            }else{
                nums1[p3--] = nums2[p2--];
            }
        }
    }

    //4 我觉得p2<0那个怪怪的 改成下面这样
    public void merge4(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1, p2 = n - 1;
        int p3 = m + n - 1;
        while(p2 >= 0){
            if(p1 < 0){
                nums1[p3--] = nums2[p2--];
            }else if(nums1[p1] > nums2[p2]){
                nums1[p3--] = nums1[p1--];
            }else{
                nums1[p3--] = nums2[p2--];
            }
        }
        while(p1 >= 0) {
            nums1[p3--] = nums1[p1--];
        }
    }

    public static void main(String[] args){
        int[] nums1 = {4,0,0};
        int m = 1;
        int[] nums2 = {2,3};
        int n = 2;
        merge3(nums1, m, nums2, n);
    }
}

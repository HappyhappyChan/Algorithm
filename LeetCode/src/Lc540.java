/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc540
 * @Description: [540. Single Element in a Sorted Array 有序数组的 Single Element]
 * @Author: [clh]
 * @Date: 2021/10/29 20:08
 **/
public class Lc540 {
    //1 brute force
    public int singleNonDuplicate(int[] nums) {
        int i = 0;
        int len = nums.length;
        while(i < len - 1){
            if(nums[i] != nums[i+1])
                return nums[i];
            i += 2;
        }
        return nums[i];
    }

    //2 binary search
    //我自己想的 多举例子 debug！
    public int singleNonDuplicate2(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        int mid = l + (h - l)/2;
        while(l < h){
            mid = l + (h - l) / 2;
            if(nums[mid] == nums[mid + 1]){
                // 1 1 2 3 3 4 4 8
                //要找的数一定在奇数个那边
                if((h - mid + 1) % 2 == 0){
                    h = mid - 1;
                }else{
                    l = mid + 2;
                }
            }else if(nums[mid] == nums[mid - 1]){
                if((mid - l + 1) % 2 == 0){
                    l = mid + 1;
                }else{
                    h = mid - 2;
                }
            }else{
                return nums[mid];
            }
        }
        return nums[h];
    }

    // 3 github binary search
    public int singleNonDuplicate3(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (m % 2 == 1) {
                m--;   // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            //因为l h m 都在偶数位 那h-m+1为奇数，又m == m+1 说明在右边
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                //同理 说明要找的数在左边
                h = m;
            }
        }
        return nums[l];
    }
}

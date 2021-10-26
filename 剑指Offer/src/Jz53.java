/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz72
 * @Description: [数字在升序数组中出现的次数 对应剑指72]
 * @Author: [clh]
 * @Date: 2021/10/23 9:19
 **/
public class Jz53 {

    //我自己写的暴力法
    public int GetNumberOfK(int [] array , int k) {
        if(array.length == 0 || k < 0)
            return 0;
        int cnt = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == k)
                cnt++;
        }
        return cnt;
    }

    //solution 2 newcoder
    public int solution2(int[] arr, int k){
        int first = binarySearch(arr, k);
        //右边界 即找k+1
        int last = binarySearch(arr, k+1);
        return (first == arr.length || arr[first] != k)? 0 : last - first;
    }

    public int binarySearch(int[] arr, int k){
        int l = 0, r = arr.length;
        while(l < r){
            int m = l + (r - l)/2;
            if(arr[m] >= k)
                r = m;
            else
                l = m+1;
        }
        return l;
    }
}

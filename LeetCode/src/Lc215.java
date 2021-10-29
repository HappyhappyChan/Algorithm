import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc215
 * @Description: [215. Kth Largest Element in an Array ]
 * @Author: [clh]
 * @Date: 2021/10/27 16:24
 **/
public class Lc215 {
    // 1 我自己写的 小顶堆
    public int findKthLargest(int[] nums, int k) {
        if(nums.length < k){
            return 0;
        }
        //建立小顶堆
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int x: nums){
            int cnt = que.size();
            if(cnt < k){
                que.add(x);
            }else if(cnt == k){
                que.add(x);
                que.poll();
            }
        }
        return que.peek();
    }

    // 2 排序
    public int findKthLargest2(int[] nums, int k){
        if(nums.length < k)
            return 0;
        Arrays.sort(nums);
        return nums[k-1];
    }

    //3 快速排序
    public int findKthLargest3(int[] nums, int k){
        //12345 第2大（从1开始数）=第（5-2）小（从0开始数）
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while(l < h){
            int j = partition(nums, l, h);
            if(j == k){
                break;
            }else if(j < k){
                l = j + 1;
            }else{
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l;
        int j = h;
        while(true){
            //不能是i<=h 否则会死循环
            while(nums[i] <= nums[l] && i < h){
                i++;
            }
            //不能是j>=l 否则也是会死循环
            while(nums[j] >= nums[l] && j > l){
                j--;
            }
            if(i >= j)
                break;
            if(i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
//            if(j < i){
//                int tmp = nums[l];
//                nums[j] = nums[j];
//                nums[j] = tmp;
//            }
        }
        int tmp = nums[l];
        nums[l] = nums[j];
        nums[j] = tmp;
        return j;
    }


}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz51
 * @Description: [数组中的逆序对]
 * @Author: [clh]
 * @Date: 2021/10/23 21:25
 **/
public class Jz51 {
    //我的思路跟nowcoder是一样的，但是会报错 可能是出现了越界的情况
    //这种在面试中肯定不行
    public int InversePairs(int [] array) {
        int cnt = 0;
        for(int i = 0; i < array.length - 1; i++){
            for(int j = i + 1; j < array.length; j++){
                if(array[i] > array[j])
                    cnt++;
            }
        }
        return (int)(cnt % (long)(1000000007));
    }

    //solution 2 nowcoder 归并排序
    int cnt2 = 0;
    public int solution2(int[] array){
        if(array.length < 2)
            return 0;
        //进入归并
        mergeSort(array, 0, array.length - 1);
        return cnt2;
    }

    public void mergeSort(int[] array, int left, int right){
        //找到分割点
        //这种计算方法当是偶数的时候会取靠左边的那个
        int mid = left + (right - left)/2;
        if(left < right){
            //左子数组
            mergeSort(array, left, mid);
            //右子数组
            mergeSort(array, mid+1, right);
            //合并
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        //创建临时数组，长度为两个数组加起来的长度
        int[] arr = new int[right - left + 1];
        //临时数组的下标起点
        int c = 0;
        //保存在原数组的起点下标值
        int s = left;
        //左子数组的起始指针
        int l = left;
        //右子数组的起始指针
        int r = mid + 1;
        while(l <= mid && r <= right){
            // 当左子数组的当前元素小的时候，跳过，无逆序对
            if(array[l] <= array[r]){
                //放入临时数组
                arr[c] = array[l];
                // 临时数组下标+1
                c++;
                // 左子数组指针右移
                l++;
            }else{
                // 否则，此时存在逆序对
                // 放入临时数组
                arr[c] = array[r];
                //逆序对的个数为左子数组的终点-当前左子数组的指针
                cnt2 += mid - l + 1;
                cnt2 %= 1000000007;
                // 临时数组+1
                c++;
                // 右子数组的指针右移
                r++;
            }
        }
        // 左子数组还有元素时，全部放入临时数组
        while(l <= mid)
            arr[c++] = array[l++];
        // 右子数组还有元素时，全部放入临时数组
        while(r <= right)
            arr[c++] = array[r++];
        // 将临时数组中的元素放入到原数组的指定位置
        for(int num:arr){
            array[s++] = num;
        }
    }

}

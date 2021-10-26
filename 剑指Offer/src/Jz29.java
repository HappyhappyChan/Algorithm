import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @ClassName: Main29
 * @Description: [给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。]
 * @Author: [clh]
 * @Date: 2021/10/10 14:50
 **/
public class Jz29 {
    //参考bilibili 剑指offer-最小的K个数(大顶堆实现)-Java版
    public ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
        if(k > input.length || k == 0){
            return new ArrayList<>();
        }
        int[] a = new int[k]; //用数组去模拟k个结点的堆结构
        for(int i = 0; i < k; i++){
            a[i] = input[i]; //初始化堆中元素
        }

        //下面开始维护堆使其成为大顶堆
        //从非叶子节点下标开始维护
        for(int i = k/2 - 1; i >= 0; i--){
            //i其实就是我们要去维护的堆的节点下标
            initiate(i,a,k);
        }

        //遍历剩余的len-k个结点
        for(int i = k; i < input.length; i++){
            if(input[i] < a[0]){
                //当前位置的值<大顶堆堆顶位置的值
                a[0] = input[i];
                initiate(0, a, k);
            }
        }

        //将大顶堆中的节点元素进行升序操作
        for(int i = a.length -1; i > 0; i--){
            int tmp = a[i];
            a[i] = a[0];
            a[0] = tmp;
            //换完的元素不参加到以后的维护中
            initiate(0, a, i);
        }
        //返回
        ArrayList<Integer> arr = new ArrayList<>();
        for(int x : a){
            arr.add(x);
        }
        return arr;
    }

    /**
     * 初始化堆的函数，其实就是维护每一个节点位置的函数
     * @param x 维护当前堆的下标
     * @param a 数组
     * @param len 堆的节点个数
     */
    private void initiate(int x, int[] a, int len) {
        int tmp = a[x]; // 先去保存当前位置的值
        for(int k = x*2+1; k < len; k = k*2+1){
            if((k+1) < len && a[k+1] > a[k]){
                //取出当前位置的左右孩子中节点值最大的节点
                k++;
            }
            if(a[k] > tmp){
                a[x] = a[k];
                x = k; //更新x的值，x 代表的是tmp数字最终在堆红的位置，但k = k*2+1执行后，x和k的关系就是父结点和孩子节点的关系
            }else{
                break; //由于我们是从下往上维护，如果a0 > a1 a2 就没必要往下看
            }
        }
        a[x] = tmp; //x所在的位置进行更新就行
    }


    //solution2 from github
    //运用PriorityQueue实现堆的能力 默认是小顶堆，但可以通过lambda (o1, o2) -> o2 - o1 来实现大顶堆
    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] nums, int k) {
        if(k > nums.length || k <= 0)
            return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int num: nums){
            maxHeap.add(num);
            if(maxHeap.size() > k)
                maxHeap.poll();
        }
        return new ArrayList<>(maxHeap);
    }

    //solution3 from github by using partition 快速排序
    public ArrayList<Integer> GetLeastNumbers_Solution3(int[] nums, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if(k > nums.length || k <= 0)
            return ret;
        findSmallest(nums, k-1);
        for(int i = 0; i < k; i++){
            ret.add(nums[i]);
        }
        return ret;
    }

    private void findSmallest(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while(l < h) {
            int j = partition(nums, l, h);
            if (j == k)
                break;
            if (j > k)
                h = j - 1;
            if (j < k)
                l = j + 1;
        }
    }

    private int partition(int[] nums, int l, int h) {
        int p = nums[l]; // 切分元素
        int i = l, j = h + 1;
        while(true){
            while(i != h && nums[++i] < p);
            while(j != l && nums[--j] > p);
            if(i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, i, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

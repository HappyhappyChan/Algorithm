import java.util.PriorityQueue;

/**
 * @ClassName: Main63
 * @Description: [ 数据流中的中位数]
 * @Author: [clh]
 * @Date: 2021/10/10 15:51
 **/
public class Jz63 {
    //大顶堆
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1,o2) -> o2 - o1);
    //小顶堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    //当前数据流读入元素的个数
    private int n = 0;

    public void Insert(Integer num) {
        // 插入要保证两个堆处于平衡状态
        if (n % 2 == 0){
            // n 为偶数的情况要插到右边
            // 右边元素要大于左边，但插入的元素不一定比左边的都大
            // 所以先将元素插入左边， 然后利用左边维护取出大的放入右边
            //我觉得其实就是跟如果奇数 你想从大顶堆还是小顶堆取出中位值有关，如果想从小顶堆去取，那小顶堆就要放奇数个，而这里的n是从0开始的，
            // 所以就是先放左边 然后挪到右边
            left.add(num);
            right.add(left.poll());
        }else{
            right.add(num);
            left.add(right.poll());
        }
        n++;
    }

    public Double GetMedian() {
        if(n % 2 == 0){
            return (left.peek() + right.peek())/2.0;
        }else{
            return (double)right.peek();
        }
    }

}

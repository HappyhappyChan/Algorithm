import java.util.*;
/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz64
 * @Description: [滑动窗口的最大值]
 * @Author: [clh]
 * @Date: 2021/10/10 17:07
 **/
public class Jz64 {
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(size > num.length || size < 1)
            return ret;
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0; i < size; i++)
            heap.add(num[i]);
        ret.add(heap.peek());
        for(int i = 0, j = i + size; j < num.length; i++, j++){
            heap.remove(num[i]);
            heap.add(num[j]); // 因为是从0开始数的 size=2 j就相当于是当前窗口外的一个数
            ret.add(heap.peek());
        }
        return ret;
    }
}

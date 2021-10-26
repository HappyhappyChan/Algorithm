import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz59
 * @Description: [滑动窗口的最大值]
 * @Author: [clh]
 * @Date: 2021/10/14 20:24
 **/
public class Jz59 {

    private ArrayList<Integer> ret = new ArrayList<>();

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        //记录数组的长度
        int n = num.length;
        if(size > n || size == 0 || n == 0)
            return null;

        //大顶堆
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        /**
         * 可行但是效率低
        for(int i = 0; i < n - size + 1; i++){
            int cnt = 0;
            int j = i;
            while(cnt < size){
                que.add(num[j]);
                j++;
                cnt++; // 一开始居然忘记了cnt++
            }
            int tmp = que.peek();

//            que.remove(num[i]); //不能用remove 因为后面还会继续加
            que.clear();
            ret.add(tmp);
        }
         */

        /**
         * 改成下面这样效率也是很低
         */
        for(int i = 0; i < n - size + 1; i++){
            int j = i;
            int cnt = 0;
            if(i == 0){
                while(cnt < size){
                    que.add(num[j]);
                    j++;
                    cnt++;
                }
                ret.add(que.peek());
            }
            if(i+size < n){
                //int tmp = que.peek();
                que.remove(num[i]);
                que.add(num[i+size]);
                ret.add(que.peek());
            }
        }
        return ret;
    }

    //solution 2 from github
    //感觉跟我的代码思路没差啊
    //确实 通过查看运行时间什么的 果然一样
    public ArrayList<Integer> maxInWindows2(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (size > num.length || size < 1)
            return ret;
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);  /* 大顶堆 */
        for (int i = 0; i < size; i++)
            heap.add(num[i]);
        ret.add(heap.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {            /* 维护一个大小为 size 的大顶堆 */
            heap.remove(num[i]);
            heap.add(num[j]);
            ret.add(heap.peek());
        }
        return ret;
    }

}

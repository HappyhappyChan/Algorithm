import java.util.Arrays;
import java.util.Comparator;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc435
 * @Description: [435. Non-overlapping Intervals 不重叠的区间个数]
 * @Author: [clh]
 * @Date: 2021/10/28 10:03
 **/
public class Lc435 {
    //1 github
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0)
            return 0;
        //根据区间结尾进行排序
        //Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        //使用 lambda 表示式创建 Comparator 会导致算法运行时间过长
        // 如果注重运行时间，可以修改为普通创建 Comparator 语句：
        //实现 compare() 函数时避免使用 return o1[1] - o2[1]; 这种减法操作，防止溢出。
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return (o1[1] < o2[1]) ? -1 : ((o1[1] == o2[1])? 0 : 1);
            }
        });
        int cnt = 1; //不重叠区间的个数
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < end){
                continue;
            }
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length - cnt;
    }

    //2 dp leetcode-cn
    //但这种方法会超时
    public int eraseOverlapIntervals2(int[][] intervals){
        if(intervals.length == 0)
            return 0;
        Arrays.sort(intervals, new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2){
               return o1[0] - o2[0];
           }
        });

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(intervals[j][1] <= intervals[i][0]){
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        //Stream.max()根据提供的Comparator返回流的最大元素
        return n - Arrays.stream(f).max().getAsInt();
    }
}

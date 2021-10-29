import java.util.Arrays;
import java.util.Comparator;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc452
 * @Description: [452. Minimum Number of Arrows to Burst Balloons 投飞镖刺破气球]
 * @Author: [clh]
 * @Date: 2021/10/28 10:59
 **/
public class Lc452 {
    //1 我自己尝试写
    public int findMinArrowShots(int[][] points) {
        if(points.length < 2)
            return points.length;
        //先根据左端点排序 如果左端点相同按照右端点排序
        //Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        //应该让左端点小 右端点大的尽量排前面
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[0] > o2[0])
                    return 1;
                if(o1[0] == o2[0]){
                    if(o1[1] == o2[1])
                        return 0;
                    if(o1[1] > o2[1])
                        return -1;
                    if(o1[1] < o2[1])
                        return 1;
                }
                return -1;
            }
        });
        int ret = 1; //记录需要多少根箭
        int start = points[0][0];
        int end = points[0][1];
        //[[1,2],[4,5],[1,5]] 这种需要2根
        for(int i = 1; i < points.length; i++){
            if(points[i][0] >= start && points[i][0] <= end){
                start = Math.max(points[i][0], start);
                end = Math.min(points[i][1], end);
                continue;
            }
            start = points[i][0];
            end = points[i][1];
            ret++;
        }
        return ret;
    }

    // 2 github
    public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int cnt = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        return cnt;
    }
}

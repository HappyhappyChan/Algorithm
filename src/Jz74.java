import java.util.ArrayList;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz41
 * @Description: [和为 S 的连续正数序列]
 * @Author: [clh]
 * @Date: 2021/10/11 9:54
 **/
public class Jz74 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2;
        int cur = 3;
        //因为要和=sum 所以那些序列中的最大的数一定比sum小
        while(end < sum){
            if(cur > sum){
                cur -= start;
                start++;
            }else if(cur < sum){
                end++;
                cur += end;
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                for(int i = start; i <= end; i++)
                    list.add(i);
                ret.add(list);
                cur -= start;
                start++;
                end++;
                cur += end;
            }
        }
        return ret;
    }
}

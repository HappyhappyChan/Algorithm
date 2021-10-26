/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1002
 * @Description: [jz70 矩形覆盖]
 * @Author: [clh]
 * @Date: 2021/10/24 10:05
 **/
public class Jz1002 {
    //solution 1 nowcoder
    //recursive
    public int rectCover(int target) {
        if(target == 0)
            return 0;
        if(target == 1)
            return 1;
        if(target == 2)
            return 2;
        return rectCover(target - 1) +rectCover(target - 2);
    }

    //solution 2 nowcoder github
    //dp
    public int solution2(int target){
        if(target == 0)
            return 0;
        if(target == 1)
            return 1;
        if(target == 2)
            return 2;
        int pre1 = 1;
        int pre2 = 2;
        int ret = 0;
        for(int i = 3; i <= target; i++){
            ret = pre2 + pre1;
            pre1 = pre2;
            pre2 = ret;
        }
        return ret;
    }
}

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1003
 * @Description: [10.3 jz69 跳台阶]
 * @Author: [clh]
 * @Date: 2021/10/24 10:37
 **/
public class Jz1003 {
    public int jumpFloor(int target) {
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

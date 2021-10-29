/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc278
 * @Description: [278. First Bad Version 第一个错误的版本]
 * @Author: [clh]
 * @Date: 2021/10/29 20:50
 **/
public class Lc278 {
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    // 1 类似找到排在最左边又=target的数
    public int firstBadVersion(int n) {
        //题目已经明确一定有一个错了
        if(n == 1) return 1;
        int l = 1, h = n;
        int mid = l + (h - l)/2;
        while(l < h){
            mid = l + (h - l)/2;
            if(isBadVersion(mid)){
                h = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    //随便写的 防止报错
    boolean isBadVersion(int n){
        if(n == 1)
            return true;
        return false;
    }

}

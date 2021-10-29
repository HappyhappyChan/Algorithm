/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc69
 * @Description: [69. Sqrt(x) 求开方]
 * @Author: [clh]
 * @Date: 2021/10/29 16:53
 **/
public class Lc69 {
    //我好久以前写的
    public int mySqrt(int x) {
        int res = 0;
        for(int i = 1;  i * i > 0&& i * i <= x; i++)
        {
            if(i * i == x)
            {
                res = i;
                break;
            }
            if(i * i < x && (i+1)*(i+1) > x)
            {
                res = i;
                break;
            }
            if(i * i < x && (i+1)*(i+1) < 0)
            {
                res = i;
                break;
            }
        }
        return res;
    }

    // 1 我想的 暴力法
    //但是会报错 因为有可能溢出 改成2就通过了
    public int mySqrt2(int x) {
        int i = 0;
        while(i*i <= x){
            i++;
        }
        return i - 1;
    }
    //2 将1改成2这样就可以通过
    public int mySqrt3(int x) {
        long target = (long)x;
        long i = 0;
        while(i*i <= target){
            i++;
        }
        return (int)(i - 1);
    }

    // 3 尝试用二分法 到底l h mid关系如何判断 用一个例子去尝试即可
    public int mySqrt4(int x) {
        if(x == 0 || x == 1) return x;
        long l = 0;
        long h = x;
        long mid = l + (h-l)/2;
        while(l < h - 1){
            mid = l + (h-l)/2;
            long tmp = mid * mid;
            if(tmp > x){
                h = mid;
            }else if(tmp < x){
                l = mid;
            }else{
                return (int) mid;
            }
        }
        return (int) l;
    }

    //4 github
    public int mySqrt5(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 1, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            } else if (mid > sqrt) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return h;
    }
}

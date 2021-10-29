/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc605
 * @Description: [605. Can Place Flowers 种植花朵]
 * @Author: [clh]
 * @Date: 2021/10/28 18:53
 **/
public class Lc605 {
    //1 我的方法就是遍历咯
    //搞了半天还是报错
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed.length == 0) return false;
        if(flowerbed.length < 2 && flowerbed[0] == 1) return n > 0 ? false : true;
        if(flowerbed.length < 2 && flowerbed[0] == 0) return n > 1 ? false : true;
        int i = 0;
        int cnt = n;
        while(i < flowerbed.length){
            if(i == 0){
                if(flowerbed[1] == 0 && flowerbed[0] == 0){
                    flowerbed[0] = 1;
                    cnt--;
                    i += 2;
                }else if(flowerbed[0] == 1){
                    i++;
                }else if(flowerbed[1] == 1){
                    i += 2;
                }
                continue;
            }
            if(i == flowerbed.length - 1){
                if(flowerbed[i] == 0 && flowerbed[i-1] == 0){
                    flowerbed[i] = 1;
                    cnt--;
                    break;
                }
            }
            if(flowerbed[i] == 1) {
                i += 2;
                continue;
            }
            if(flowerbed[i] == 0 && flowerbed[i+1] == 0 && flowerbed[i-1] == 0){
                flowerbed[i] = 1;
                cnt--;
            }
            i++;
        }
        return cnt == 0;
    }

    //2 single scan
    public boolean canPlaceFlowers2(int[] flowerbed, int n){
        int i = 0, cnt = 0;
        while(i < flowerbed.length){
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)){
                flowerbed[i] = 1;
                cnt++;
            }
            i++;
        }
        return cnt >= n;
    }

    //3 优化
    public boolean canPlaceFlowers3(int[] flowerbed, int n){
        int i = 0, cnt = 0;
        while(i < flowerbed.length){
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)){
                flowerbed[i] = 1;
                cnt++;
            }
            if(cnt >= n)
                return true;
            i++;
        }
        return false;
    }
}

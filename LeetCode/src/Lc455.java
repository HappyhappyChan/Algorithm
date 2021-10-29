import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc455
 * @Description: [455. Assign Cookies 分配饼干]
 * @Author: [clh]
 * @Date: 2021/10/28 9:44
 **/
public class Lc455 {
    //  1 github
    public int findContentChildren(int[] g, int[] s) {
        if(g == null || s == null) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0, si = 0;
        while(gi < g.length && si < s.length){
            if(g[gi] <= s[si]){
                gi++;
            }
            si++;
        }
        return gi;
    }
}

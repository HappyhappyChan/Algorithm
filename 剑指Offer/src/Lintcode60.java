import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: LintCode60
 * @Description: [cs 60 lintcode 20]
 * @Author: [clh]
 * @Date: 2021/10/24 17:36
 **/
public class Lintcode60 {
    //solution 1 github dp
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        // Write your code here
        // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        // to create the pair
        final int face = 6;
        //最大和为6面*n个骰子
        final int pointNum = face * n;
        long[][] dp = new long[n + 1][pointNum + 1];

        //第一个骰子出现1~6的次数为1
        for (int i = 1; i <= face; i++)
            dp[1][i] = 1;

        //从第二个骰子开始
        for (int i = 2; i <= n; i++)
            //前n个骰子的点数和最小也为n 最大也是=最大和的
            //遍历前n个骰子出现和的情况
            for (int j = i; j <= pointNum; j++)     /* 使用 i 个骰子最小点数为 i */
                //遍历第i个骰子出现和为j的情况
                for (int k = 1; k <= face && k <= j; k++)
                    //如果第i骰子的值是k，那第i-1个骰子的和要是j-k, 这样前i个骰子的和才可能是j
                    dp[i][j] += dp[i - 1][j - k];

        final double totalNum = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
        for (int i = n; i <= pointNum; i++)
            ret.add(new AbstractMap.SimpleEntry<>(i, dp[n][i] / totalNum));

        return ret;
    }

    //solution 2 github dp + rotated array
    public List<Map.Entry<Integer, Double>> dicesSum2(int n) {
        final int face = 6;
        final int pointNum = face * n;
        long[][] dp = new long[2][pointNum + 1];

        for (int i = 1; i <= face; i++)
            dp[0][i] = 1;

        int flag = 1;                                     /* 旋转标记 */
        for (int i = 2; i <= n; i++, flag = 1 - flag) {
            for (int j = 0; j <= pointNum; j++)
                dp[flag][j] = 0;                          /* 旋转数组清零 */

            //前n个骰子的点数和最小也为n 最大也是=最大和的
            for (int j = i; j <= pointNum; j++)
                for (int k = 1; k <= face && k <= j; k++)
                    //如果第i骰子的值是k，那第i-1个骰子的和要是j-k, 这样前i个骰子的和才可能是j
                    //感觉flag就是用来记录当前骰子 和 前一个骰子的
                    dp[flag][j] += dp[1 - flag][j - k];
        }

        final double totalNum = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
        for (int i = n; i <= pointNum; i++)
            ret.add(new AbstractMap.SimpleEntry<>(i, dp[1 - flag][i] / totalNum));

        return ret;
    }

}

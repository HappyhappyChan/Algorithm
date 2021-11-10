import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc650
 * @Description: [650. 2 Keys Keyboard 复制粘贴字符]
 * @Author: [clh]
 * @Date: 2021/11/9 23:02
 **/
public class Lc650 {
    //1 leetcode 因素分解
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

    //2 leetcode-cn dfs
    // DFS递归
    public static int minSteps2(int n) {
        if (n == 1) return 0;
        return dfs(n, 1, 0);
    }

    // n：固定参数，要达到的目标（输出n个'A'）
    // cur：当前记事本上已输出的'A'的数量
    // paste：当前粘贴板上已有的'A'的数量
    // 返回：在当前cur、paste的情况下，达到目标，所需要的最少操作次数
    private static int dfs(int n, int cur, int paste) {
        if (cur == n) return 0; // 当前记事本输出已达目标，无需操作
        if (cur > n) return INF; // 当前记事本输出超过了目标，不可能达到目标，表示之前的DFS尝试方案无效
        // 1）本次操作，选择复制（如果当前粘贴板上'A'数量 != 当前记事本上'A'数量，则可以有此选择，否则，复制操作无意义，不做此选择）：
        int p1 = cur != paste ? 1 + dfs(n, cur, cur) : INF;
        // 2）本次操作，选择粘贴（如果当前粘贴板上'A'数量 > 0，则可以有此选择，否则，粘贴操作无意义，不做此选择）：
        int p2 = paste > 0 ? 1 + dfs(n, cur+paste, paste) : INF;
        return Math.min(p1, p2);
    }

    private static final int INF = Integer.MAX_VALUE/2;

    // 3 dp
    public int minSteps3(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                f[i][j] = INF;
            }
        }
        f[1][0] = 0; f[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = INF;
            for (int j = 0; j <= i / 2; j++) {
                //上一次操作是paste
                f[i][j] = f[i - j][j] + 1;
                //上一次操作是copy
                min = Math.min(min, f[i][j]);
            }
            //上一次操作是copy
            f[i][i] = min + 1;
        }
        int ans = INF;
        for (int i = 0; i <= n; i++) ans = Math.min(ans, f[n][i]);
        return ans;
    }

    //4 dp 优化 lc-cn
    public int minSteps4(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <=n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int i = 2; i <=n; i++) {
            int min = INF;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    if (i != j) {
                        dp[i][j] = dp[i - j][j] + 1;
                        min = Math.min(min, dp[i][j]);
                    } else {
                        dp[i][j] = min + 1;
                    }
                }
            }
        }

        return IntStream.of(dp[n]).min().getAsInt();
    }

    //5 我自己写的 居然也通过了 但我返回的是dp[n][n]
    public int minSteps5(int n) {
        int[][] dp = new int[n+1][n+1];
        dp[1][1] = 0;
        dp[1][0] = 0;

        int min = Integer.MAX_VALUE / 2;
        for(int i = 2; i <= n; i++){
            min = Integer.MAX_VALUE / 2;
            for(int j = 1; j <= i/2; j++){
                dp[i][j] = dp[i-j][j]+1;
                if(i % j == 0){
                    min = Math.min(min, dp[i][j]);
                }
            }
            dp[i][i] = min+1;
        }
        return dp[n][n];
    }
}

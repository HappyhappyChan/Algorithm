import java.util.Arrays;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1004
 * @Description: [jz71 跳台阶扩展问题]
 * @Author: [clh]
 * @Date: 2021/10/24 10:56
 **/
public class Jz1004 {
    public int jumpFloorII(int target) {
        if(target == 1)
            return 1;
        if(target == 2)
            return 2;
        int ret = 3;
        int sum = 3;
        for(int i = 3; i <= target; i++){
            ret = sum + 1;
            sum += ret;
        }
        return ret;
    }

    //solution 2 from github
    //dp
    public int jumpFloorII2(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++)
            //dp[n] = dp[0]+dp[1]+..+dp[n]=1+2+..+1
            //=f[1]+f[2]+..+f[n]+1
            //->dp[n] = f[n+1]
            //dp[1]=2 dp[2]=3
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }

    //solution 3 github
    public int JumpFloorII3(int target) {
        return (int) Math.pow(2, target - 1);
    }
}

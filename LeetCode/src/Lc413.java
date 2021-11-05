/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc413
 * @Description: [413. Arithmetic Slices (Medium) 数组中等差递增子区间的个数]
 * @Author: [clh]
 * @Date: 2021/11/4 15:36
 **/
public class Lc413 {
    //我完全没思路 看github解析的
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 3)
            return 0;
        int n = nums.length;
        int[] dp = new int[nums.length];
        for(int i = 2; i < n; i++){
            if(nums[i] - nums[i-1] == nums[i-1] -nums[i-2]){
                dp[i] = dp[i-1]+1;
            }
        }
        int sum = 0;
        for(int x : dp){
            sum += x;
        }
        return sum;
    }

    //2 leetcode discuss上简易版本 思路跟上面一样
    public int numberOfArithmeticSlices2(int[] A) {
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }

    //3 leetcode discuss上另一种dp解法
    public int numberOfArithmeticSlices3(int[] A) {
        int n=A.length;
        if(n<3){return 0;}
        boolean[][] dp=new boolean[n][n]; //initial value is false
        int count=0;
        for(int i=0;i<n-3+1;i++){
            if((A[i+1]-A[i])==(A[i+2]-A[i+1])){
                dp[i][i+3-1]=true;
                count++;
            }
        }
        //这里的遍历没有理解
        for(int k=4;k<=n;k++){
            for (int i=0;i<n-k+1;i++){
                int j=i+k-1;
                if(dp[i+1][j]==true&&(A[i+1]-A[i]==A[i+2]-A[i+1])){
                    dp[i][j]=true;
                    count++;
                }else if(dp[i][j-1]==true&&(A[j]-A[j-1]==A[j-1]-A[j-2])){
                    dp[i][j]=true;
                    count++;
                }
            }
        }
        return count;
    }
}

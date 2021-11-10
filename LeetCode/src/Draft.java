import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Draft
 * @Description: [用来打草稿的]
 * @Author: [clh]
 * @Date: 2021/11/10 10:09
 **/
public class Draft {
    /**
     * 因为动态规划最不熟悉 从动态规划开始
     */

    //70 climbing stairs
    public int climbStairs1(int n) {
        if(n <= 2) return n;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    //优化空间
    public int climbStairs2(int n) {
        if(n <= 2) return n;
        int a = 1;
        int b = 1;
        for(int i = 2; i <= n; i++){
            int tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }

    //尝试递归
    public int climbStairs3(int n) {
        if(n <= 2) return n;
        return climbStairs3(n-1) + climbStairs3(n-2);
    }

    /**
     * 198 house robber
     */
    public int rob(int[] nums) {
        if(nums == null) return 0;
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for(int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }

    //优化空间
    public int rob2(int[] nums) {
        if(nums == null) return 0;
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        int a = nums[0];
        int b = Math.max(a, nums[1]);
        for(int i = 2; i < n; i++){
            int tmp = Math.max(b, a + nums[i]);
            a = b;
            b = tmp;
        }
        return b;
    }

    //递归
    public int rob3(int[] nums) {
        if(nums == null) return 0;
        int n = nums.length - 1;
        return robs3helper(nums, n);
    }

    public int robs3helper(int[] nums, int idx){
        if(idx < 0) return 0;
        if(idx == 0) return nums[0];
        if(idx == 1) return Math.max(nums[0], nums[1]);
        return Math.max(robs3helper(nums, idx-1), robs3helper(nums, idx-2)+nums[idx]);
    }

    /**
     * 213. House Robber II (Medium)
     */
    public int robII(int[] nums) {
        if(nums == null) return 0;
        int n = nums.length;
        if(n < 0) return 0;
        if(n == 1) return nums[0];
        if(n == 2) return nums[0] > nums[1] ? nums[0] : nums[1];
        int a = nums[0];
        int b = nums[0] > nums[1] ? nums[0] : nums[1];
        int head = 0;
        for(int i = 2; i < n-1; i++){
            int tmp = Math.max(a+nums[i], b);
            a = b;
            b = tmp;
        }
        head = b;

        a = nums[1];
        b = Math.max(a, nums[2]);
        for(int i = 3; i < n; i++){
            int tmp = Math.max(a+nums[i], b);
            a = b;
            b = tmp;
        }
        return b > head ? b : head;
    }

    /**
     * 64. Minimum Path Sum
     */
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j > 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if(i > 0 && j == 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else if(i == 0 && j == 0){
                    dp[0][0] = grid[0][0];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
    //优化空间 failure
    public int minPathSum2(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int r = grid[0][0]; //记录dp[i-1][j]
        int c = grid[0][0]; //记录dp[i][j-1]
        //这样是不行的，因为是一行一行遍历 不能同时存储对应所需的r c
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                /*if(i == 0 && j > 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if(i > 0 && j == 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else if(i == 0 && j == 0){
                    dp[0][0] = grid[0][0];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
                }*/
            }
        }
        return -1;
    }

    //优化空间 因为只需要返回min 不需要返回grid
    //直接在grid里面改
    public int minPathSum3(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j > 0){
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                }else if(i > 0 && j == 0){
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                }else if(i == 0 && j == 0){
                    grid[0][0] = grid[0][0];
                }else{
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1])+grid[i][j];
                }
            }
        }
        return grid[m-1][n-1];
    }

    //递归 TLE
    public int minPathSum4(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        return minPathSumHelper(grid, m-1, n-1);
    }

    private int minPathSumHelper(int[][] grid, int r, int c) {
        if(r == 0 && c == 0)
            return grid[0][0];
        if(r == 0 && c != 0)
            return grid[0][c] + minPathSumHelper(grid, r, c-1);
        if(r != 0 && c == 0)
            return grid[r][0] + minPathSumHelper(grid, r-1, c);
        return Math.min(minPathSumHelper(grid, r-1,c), minPathSumHelper(grid, r, c-1)) + grid[r][c];
    }

    /**
     * 62. Unique Paths
     */
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0)
            return 0;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    dp[0][0] = 1;
                }else if(i == 0){
                    dp[0][j] = 1;
                }else if(j == 0){
                    dp[i][0] = 1;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    //递归
    public int uniquePaths2(int m, int n){
        if(m == 0 || n == 0)
            return 0;
        return uniquePaths2Helper(m-1, n-1);
    }

    private int uniquePaths2Helper(int r, int c) {
        if(r == 0 || c == 0)
            return 1;
        return uniquePaths2Helper(r, c-1) + uniquePaths2Helper(r-1, c);
    }

    //math
    public int uniquePaths3(int m, int n){
        int S = m + n - 1;
        int down = m - 1;
        //int ret = 1;
        long ret = 1; //要变成long啊
        for(int i = 1; i <= down; i++){
            // ret = ret * (S - i + 1) /i; 这不行
            ret = ret * (S - down + i) /i;
        }
        return (int)(ret);
    }

    // memoization
    public int uniquePaths4(int m, int n){
        if(m == 0 || n == 0)
            return 0;
        return uniquePaths4Helper(m-1, n-1, new int[m][n]);
    }

    private int uniquePaths4Helper(int r, int c, int[][] memo) {
        if(r == 0 || c == 0){
            memo[r][c] = 1;
            return 1;
        }else if(memo[r][c] > 0){
            return memo[r][c];
        }else{
            memo[r][c] = uniquePaths4Helper(r, c-1, memo) + uniquePaths4Helper(r-1, c, memo);
        }
        return memo[r][c];
    }

    //dp 优化
    public int uniquePaths5(int m, int n){
        if(m == 0 || n == 0)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

    /**
     * 303. Range Sum Query - Immutable (Easy)
     */
    public void NumArray(int[] nums) {
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        for(int i = 1; i <= nums.length; i++){
            dp[i] = dp[i-1] + nums[i-1];
        }
    }

    public int sumRange(int left, int right, int[] dp) {
        return dp[right+1] - dp[left];
    }

    /**
     * 413. Arithmetic Slices (Medium)
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 3)
            return 0;
        int n = nums.length;
        int[] dp = new int[n];
        for(int i = 2; i < n; i++){
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                dp[i] = dp[i-1] + 1;
            }
        }
        int res = 0;
        for(int x : dp){
            res += x;
        }
        return res;
    }

    /**
     * 343. Integer Break (Medim)
     */
    public int integerBreak(int n) {
        if(n <= 0) return 0;
        if(n <= 2) return 1;
        if(n == 3) return 2;
        int res = 1;
        while(n/3 != 0){
            res *= 3;
            n = n-3;
        }
        if(n == 1){
            res = res/3 * 4;
        }else if(n==2){
            res *= n;
        }
        return res;
    }

    //dp
    public int integerBreak2(int n) {
        if(n <= 0) return 0;
        if(n <= 2) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <= n; i++){
            int max = 0;
            for(int j = 1; j < i; j++){
                int tmp = Math.max(j*(i-j), j * dp[i-j]);
                max = Math.max(tmp, max);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    //dp 优化
    public int integerBreak3(int n) {
        if(n <= 0) return 0;
        if(n <= 2) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            int tmp1 = Math.max(2*(i-2), 2*dp[i-2]);
            int tmp2 = Math.max(3*(i-3), 3*dp[i-3]);
            dp[i] = Math.max(tmp1, tmp2);
        }
        return dp[n];
    }

    /**
     * 279. Perfect Squares(Medium)
     */
    public int numSquares(int n) {
        if(n <= 1) return n;
        List<Integer> squares = numSquaresHelper(n);
        Queue<Integer> que = new LinkedList<>();
        boolean[] seen = new boolean[n+1];
        que.add(n);
        int level = 0;
        while(!que.isEmpty()){
            level++;
            int size = que.size();
            while(size-->0){
                int cur = que.poll();
                for(int square: squares){
                    int next = cur - square;
                    if(next == 0)
                        return level;
                    if( next < 0)
                        break;
                    if(seen[next])
                        continue;
                    seen[next] = true;
                    que.add(next);
                }
            }
        }
        return level;
    }

    private List<Integer> numSquaresHelper(int n) {
        List<Integer> list = new ArrayList<Integer>();
        int d = 3;
        int cur = 1;
        while(cur <= n){
            list.add(cur);
            cur += d;
            d += 2;
        }
        return list;
    }

    //dp
    public int numSquares2(int n){
        if(n <= 1) return n;
        if((int)Math.sqrt(n) * (int)Math.sqrt(n) == n) return 1;
        List<Integer> squares = numSquaresHelper(n);
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int s: squares) {
            dp[s] = 1;
        }
        for(int i = 2; i <= n; i++){
            //不能用dp[i] = i;因为上面已经定义好dp[s] = 1
            //如果改成dp[i]=i会改变数组
            int tmp = i;
            for(int s: squares){
                if(s <= i){
                    tmp = Math.min(tmp, dp[s]+dp[i-s]);
                }
            }
            dp[i] = dp[i] > 0? dp[i] : tmp;
        }
        return dp[n];
    }

    //dp 优化
    public int numSquares3(int n) {
        if (n <= 1) return n;
        if ((int) Math.sqrt(n) * (int) Math.sqrt(n) == n) return 1;
        List<Integer> squares = numSquaresHelper(n);
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            int min = i;
            for(int s : squares){
                if(s > i)
                    break;
                //i-s < i 所以do[i-s]肯定比dp[i]先算好了
                min = Math.min(min, dp[i-s] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    /**
     * 91. Decode Ways (Medium)
     */
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)
            return 0;
        if(s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        //初始化dp[0] = 1 要不然12会报错s
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            int cur = s.charAt(i-1) - '0';
            if(cur > 0){
                //一开始楼了(s.charAt(i-2)-'0') - ‘0’就错了
                if(s.charAt(i-2) != '0' && (s.charAt(i-2)-'0')*10 + cur <= 26){
                    dp[i] = dp[i-1] + dp[i-2];
                }else{
                    dp[i] = dp[i-1];
                }
            }else{
                int pre = s.charAt(i-2)-'0';
                if(pre>0 && pre < 3){
                    dp[i] = dp[i-2];
                }else{
                    return 0;
                }
            }
        }
        return dp[n];
    }

    // dp 空间优化
    // a= dp[i-2] b = dp[i-1] c = dp[i]
    public int numDecodings2(String s) {
        if(s == null || s.length() == 0)
            return 0;
        if(s.charAt(0) == '0') return 0;
        int n = s.length();
        int a = 1;
        int b = 1;
        int c = 1; //eg s = "1" 所以c一开始不能为0
        for(int i = 2; i <= n; i++){
            int cur = s.charAt(i-1) - '0';
            if(cur > 0){
                //一开始楼了(s.charAt(i-2)-'0') - ‘0’就错了
                if(s.charAt(i-2) != '0' && (s.charAt(i-2)-'0')*10 + cur <= 26){
                    c = a + b;
                    a = b;
                    b = c;
                }else{
                    c = b;
                    a = b;
                    b = c;
                }
            }else{
                int pre = s.charAt(i-2)-'0';
                if(pre>0 && pre < 3){
                    c = a;
                    a = b;
                    b = c;
                }else{
                    return 0;
                }
            }
        }
        return c;
    }

    /**
     * 300. Longest Increasing Subsequence (Medium)
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if(n == 1) return 1;
        int[] dp = new int[n];
        //别忘了这个
        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        //不一定以nums[n-1]结尾就是最大的
//        return dp[n-1];
        //这个也很常用
        return Arrays.stream(dp).max().getAsInt();
    }

    //二分查找

}


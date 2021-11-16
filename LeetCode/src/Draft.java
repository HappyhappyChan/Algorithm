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
    public int lengthOfLIS2(int[] nums){
        if(nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for(int x : nums){
            int index = lengthOfLIS2Search(tails, len, x);
            tails[index] = x;
            if(index == len){
                len++;
            }
        }
        return len;
    }

    private int lengthOfLIS2Search(int[] tails, int len, int target) {
        int l = 0;
        int h = len;
        while(l < h){
            int mid = l + (h-l)/2;
            if(tails[mid] == target){
                return mid;
            }else if(tails[mid] > target){
                h = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 646. Maximum Length of Pair Chain (Medium)
     */
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0 || pairs[0].length == 0)
            return 0;
        Arrays.sort(pairs, (o1, o2) -> (o1[0]-o2[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(pairs[i][0] > pairs[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        //但solution和github里面都是要求遍历dp然后选最大的
        //我这样也能通过很神奇
        return dp[n-1];
    }

    //greedy
    public int findLongestChain2(int[][] pairs) {
        if(pairs == null || pairs.length == 0 || pairs[0].length == 0)
            return 0;
        int cur = Integer.MIN_VALUE;
        int cnt = 0;
        Arrays.sort(pairs, (a,b)-> (a[1]-b[1]));
        for(int[] p : pairs){
            if(cur < p[0]){
                cnt++;
                cur = p[1];
            }
        }
        return cnt;
    }

    /**
     * 376. Wiggle Subsequence (Medium)
     */
    //之前没搞懂，这次也没搞懂，搞了半天补充了9 10在原先的代码文件中
    //unknown
    /**
     * 1143. Longest Common Subsequence
     */
    public int longestCommonSubsequence(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;
        if(s1.charAt(0) == s2.charAt(0)){
            return 1 + longestCommonSubsequence(s1.substring(1), s2.substring(1));
        }else{
            return Math.max(longestCommonSubsequence(s1, s2.substring(1)), longestCommonSubsequence(s1.substring(1), s2));
        }
    }

    //dp
    public int longestCommonSubsequence2(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                    continue;
                }
                //因为是m+1 n+1 啊
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    // dp space optimzation
    public int longestCommonSubsequence3(String s1, String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n+1];
        for(int i = 1; i <= m; i++){
            int[] tmp = new int[n+1];
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    tmp[j] = dp[j-1] +1;
                }else{
                    tmp[j] = Math.max(dp[j], tmp[j-1]);
                }
            }
            dp = tmp;
        }
        return dp[n];
    }

    /**
     * 416. Partition Equal Subset Sum (Medium)
     */
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        if((sum & 1) == 1) return false;
        int target = sum >> 1;
        int n = nums.length;
        boolean[][] dp = new boolean[n+1][target+1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= target; j++){
                dp[i][j] = dp[i-1][j];
                //注意是i-1 不是 i
                //不能少了等号！
                if(j >= nums[i-1]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }

    //dp space optimization
    public boolean canPartition2(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        if((sum & 1) == 1) return false;
        int target = sum >> 1;
        int n = nums.length;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int i = 1; i <= n; i++){
            for(int j = target; j >= nums[i-1]; j--){
                dp[j] = dp[j] || dp[j-nums[i-1]];
            }
        }
        return dp[target];
    }

    //backtrack
    //TLE
    public boolean canPartition3(int[] nums){
        if(nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        if((sum & 1) == 1) return false;
        int target = sum >> 1;
        return canPartition3Backtrack(nums, 0, target);
    }

    private boolean canPartition3Backtrack(int[] nums, int start, int target) {
        if(target==0){
            return true;
        }
        if(target < 0 || start >= nums.length)
            return false;
        for(int i = start; i < nums.length; i++){
            if(target - nums[i] >= 0){
                if(canPartition3Backtrack(nums, i+1, target - nums[i]))
                    return true;
            }
        }
        return false;
    }

    /**
     * 494. Target Sum (Medium)
     */
    public int findTargetSumWays(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return 0;
        int sum = 0;
        for(int x: nums){
            sum += x;
        }
        int tar = target + sum;
        //[100] target = -200 这里不能tar<=0 因为tar= 0 return 1
        if((tar & 1) == 1 || tar < 0) return 0;
        tar = tar >> 1;
        int[] dp = new int[tar + 1];
        int n = nums.length;
        dp[0] = 1; //要初始化 要不然一直为0
        for(int i = 1; i <= n; i++){
            for(int j = tar; j >= nums[i-1]; j--){
                dp[j] = dp[j] + dp[j-nums[i-1]];
            }
        }
        return dp[tar];
    }

    //dfs 相当于有2种走法 右走 下走 分别表示+ -
    public int findTargetSumWays2(int[] nums, int target){
        if(nums == null || nums.length == 0) return 0;
        return findTargetSumWays2Helper(nums, 0, target);
    }

    private int findTargetSumWays2Helper(int[] nums, int start, int target) {
        if(start == nums.length){
            return target == 0 ? 1 : 0;
        }
        return findTargetSumWays2Helper(nums, start+1, target-nums[start])
                + findTargetSumWays2Helper(nums, start+1, target-nums[start]);
    }

    /**
     * 474. Ones and Zeroes (Medium)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs == null || strs.length == 0 )
            return 0;
        if(m == 0 && n == 0) return 0;
        int len = strs.length;
        int[][][] dp = new int[len+1][m+1][n+1];
        for(int i = 1; i <= len; i++){
            String s = strs[i-1];
            int ones = 0;
            int zeros = 0;
            for(int x = 0; x < s.length(); x++){
                if(s.charAt(x) == '0'){
                    zeros++;
                }else{
                    ones++;
                }
            }
            //一直出错就是因为这里j k 要从0开始
            //有可能1个1 0个0 这种也要遍历的
            /*for(int j = 1; j <= m; j++){
                for(int k = 1; k <= n; k++){*/
            for(int j = 0; j <= m; j++){
                for(int k = 0; k <= n; k++){
                    int max = dp[i-1][j][k];
                    //j 对应的是zero啊啊
                    if(j >= zeros && k >= ones){
                        max = Math.max(max, dp[i-1][j-zeros][k-ones]+1);
                    }
                    /*if(j >= ones && zeros == 0){
                        max = Math.max(max, dp[i-1][j-ones][k]+1);
                    }
                    if(k >= zeros && ones == 0){
                        max = Math.max(max, dp[i-1][j][k-zeros]+1);
                    }*/
                    dp[i][j][k] = max;
                }
            }
        }
        return dp[len][m][n];
    }

    //dp space optimization
    public int findMaxForm2(String[] strs, int m, int n) {
        if(strs == null || strs.length == 0 )
            return 0;
        if(m == 0 && n == 0) return 0;
        int len = strs.length;
        int[][] dp = new int[m+1][n+1];
        for(int x = 1; x <= len; x++){
            String s = strs[x-1];
            int ones = 0;
            int zeros = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '0'){
                    zeros++;
                }else{
                    ones++;
                }
            }
            for(int i = m; i >= 0; i--){
                for(int j = n; j >= 0; j--){
                    if(i >= zeros && j >= ones){
                        dp[i][j] = Math.max(dp[i][j], dp[i-zeros][j-ones]+1);
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 322. Coin Change (Medium)
     */
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        //完全背包 要求恰好装满 除了[0][0]=0 其他都初始化最大or最小值
        int min = Integer.MAX_VALUE/2;
        for(int[] x: dp){
            Arrays.fill(x, min);
        }
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= amount; j++){
                dp[i][j] = dp[i-1][j];
                if(j >= coins[i-1])
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-coins[i-1]]+1);
            }
        }
        return dp[n][amount] >= min ? -1 : dp[n][amount];
    }

    //dp optimization
    public int coinChange2(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0)
            //[1] 0
            return 0;
        int n = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= amount; j++){
                if(j >= coins[i-1])
                    dp[j] = Math.min(dp[j], dp[j-coins[i-1]]+1);
            }
        }
        //[1] 3
        return dp[amount] >= Integer.MAX_VALUE/2 ? -1: dp[amount];
    }

    //bfs
    public int coinChange3(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int level = 0;
        Queue<Integer> que = new LinkedList<>();
        que.add(amount);
        boolean[] seen = new boolean[amount+1];
        seen[amount] = true;
        while(!que.isEmpty()){
            level++;
            int size = que.size();
            while(size-- > 0){
                int cur = que.poll();
                for(int coin: coins){
                    int next = cur - coin;
                    if(next < 0)
                        continue;
                    if(next == 0)
                        return level;
                    if(seen[next])
                        continue;
                    seen[next] = true;
                    que.add(next);
                }
            }
        }
        return -1;
    }

    //recursion
    //TLE
    public int coinChange4(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int res = coinChange4Helper(coins, amount);
        return  res >= Integer.MAX_VALUE/2 ? -1 : res;
    }

    private int coinChange4Helper(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;
        int min = Integer.MAX_VALUE/2;
        for(int coin: coins){
            if(amount >= coin)
               min = Math.min(min, 1 + coinChange4Helper(coins, amount - coin));
        }
        return min;
    }

    //recursion + memoization
    public int coinChange5(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int res = coinChange5Helper(coins, amount, new int[amount+1]);
        return  res;
    }

    private int coinChange5Helper(int[] coins, int amount, int[] memo) {
        if(amount < 0)
            return -1;
        if(amount == 0)
            return 0;
        //不能是>0 因为有可能是-1
        if(memo[amount] != 0)
            return memo[amount];
        int min = Integer.MAX_VALUE/2;
        for(int coin : coins){
            int tmp = coinChange5Helper(coins, amount-coin, memo);
            if (tmp >= 0 && tmp < min) {
                min = 1 + tmp;
            }

        }
        memo[amount] = (min >= Integer.MAX_VALUE/2) ? -1 : min;
        return memo[amount];
    }

    /**
     * 518. Coin Change 2 (Medium)
     */
    public int change(int amount, int[] coins) {
        if(coins == null || coins.length == 0)
            return 0;
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= amount; j++){
                dp[i][j] = dp[i-1][j];
                if(j >= coins[i-1])
                    dp[i][j] += dp[i][j-coins[i-1]];
            }
        }
        return dp[n][amount];
    }

    //dp optimization
    public int change2(int amount, int[] coins) {
        if(coins == null || coins.length == 0)
            return 0;
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= amount; j++){
                if(j >= coins[i-1])
                    dp[j] += dp[j- coins[i-1]];
            }
        }
        return dp[amount];
    }

    //optimization 3
    public int change3(int amount, int[] coins) {
        if(coins == null || coins.length == 0)
            return 0;
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = coins[i-1]; j <= amount; j++){
                    dp[j] += dp[j- coins[i-1]];
            }
        }
        return dp[amount];
    }

    //dfs 原本想用dfs写 但是不知道怎么搞……

    /**
     * 139. Word Break (Medium)
     */
    //这个还是不熟啊
    //unknown
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict.size() == 0)
            return false;
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 1; i <= n; i++){
            for(String x : wordDict){
                int len = x.length();
                if(i >= len && x.equals(s.substring(i-len, i)))
                    dp[i] = dp[i] || dp[i-len];
            }
        }
        return dp[n];
    }

    //hashset
    public boolean wordBreak2(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict.size() == 0)
            return false;
        int n = s.length();
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && wordDictSet.contains(s.substring(j, i)))
                    dp[i] = true;
            }
        }
        return dp[n];
    }

    /**
     * 377. Combination Sum IV (Medium)
     */
    //这种完全背包+考虑顺序的不熟啊
    //unknown
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int j = 0; j <= target; j++){
            for(int i = 1; i <= n; i++){
                if(j >= nums[i-1])
                    dp[j] += dp[j-nums[i-1]];
            }
        }
        return dp[target];
    }

    /**
     * 309. Best Time to Buy and Sell Stock with Cooldown(Medium)
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        int[][] dp = new int[n][3];
        //0--不持股 进入cooldown
        //1--不持股 不在cooldown
        //2--持股
        dp[0][2] = -prices[0];
        for(int i = 1; i < n; i++){
            dp[i][0] = dp[i-1][2] + prices[i];
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][2] = Math.max(dp[i-1][1]-prices[i], dp[i-1][2]);
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);

    }

    //dp optimization
    public int maxProfit2(int[] prices){
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        int[] dp = new int[n];
        int a = 0, b = 0, c = -prices[0];
        for(int i = 1; i < n; i++){
            int tmp1 = c + prices[i];
            int tmp2 = Math.max(a, b);
            int tmp3 = Math.max(b-prices[i], c);
            a = tmp1;
            b = tmp2;
            c = tmp3;
        }
        return Math.max(a,b);
    }

    /**
     * 714. Best Time to Buy and Sell Stock with Transaction Fee (Medium)
     */
    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length == 0)
            return 0;
        int n = prices.length;
        int[] sell = prices;
        int[] buy = new int[n];
        for(int i = 0; i < n; i++){
            buy[i] = prices[i] + fee;
        }
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            int max = dp[i-1];
            for(int j = 1; j < i; j++){
                max = Math.max(max, dp[j-1] + sell[i-1] - buy[j-1]);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int maxProfit2(int[] prices, int fee){
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        int[] sell = new int[n];
        int[] buy = new int[n];
        buy[0] = -prices[0];
        sell[0] = 0;
        buy[1] = Math.max(buy[0], -prices[1]);
        sell[1] = Math.max(sell[0], prices[1]+buy[1]-fee);
        for(int i = 2; i < n; i++){
            buy[i] = Math.max(buy[i-1], sell[i-1]-prices[i]);
            sell[i] = Math.max(sell[i-1], prices[i]+buy[i-1]-fee);
        }
        return sell[n-1];
    }

    //对上面的空间优化
    public int maxProfit3(int[] prices, int fee){
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        int buy1 = -prices[0];
        int sell1 = 0;
        int buy2 = Math.max(buy1, -prices[1]);
        int sell2 = Math.max(sell1, buy1+prices[1]-fee);
        for(int i = 2; i < n; i++){
            int buy3 = Math.max(buy2, sell2-prices[i]);
            int sell3 = Math.max(sell2, buy2+prices[i]-fee);
            buy1 = buy2;
            buy2 = buy3;
            sell1 = sell2;
            sell2 = sell3;
        }
        return sell2;
    }
    //其实从1开始就ok
    public int maxProfit4(int[] prices, int fee){
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        int buy1 = -prices[0];
        int sell1 = 0;
        for(int i = 1; i < n; i++){
            buy1 = Math.max(buy1, sell1-prices[i]);
            sell1 = Math.max(sell1, buy1+prices[i]-fee);
        }
        return sell1;
    }

    /**
     * 123. Best Time to Buy and Sell Stock III (Hard)
     */
    public int maxProfitIII(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        int[] l2r = new int[n];
        int[] r2l = new int[n];

        int p1 = 0;
        int min = prices[0];
        for(int i = 1; i < n; i++){
            int cur = prices[i] - min;
            if(cur > p1)
                p1 = cur;
            if(prices[i] < min)
                min = prices[i];
            l2r[i] = p1;
        }

        int p2 = 0;
        int max = prices[n-1];
        for(int i = n-2; i >= 0; i--){
            int cur = max - prices[i];
            if(cur > p2)
                p2 = cur;
            if(prices[i] > max)
                max = prices[i];
            r2l[i] = p2;
        }
        int res = 0;
        for(int i = 0; i < n; i++){
            res = Math.max(res, l2r[i]+r2l[i]);
        }
        return res;
    }

    //dp
    public int maxProfitIII2(int[] prices){
        if(prices ==  null || prices.length == 0)
            return 0;
        int sell1 = 0;
        int buy1 = Integer.MIN_VALUE;
        int sell2 = 0;
        int buy2 = Integer.MIN_VALUE;
        for(int cur: prices){
            if(buy1 < -cur){
                buy1 = -cur;
            }
            if(sell1 < buy1 + cur){
                sell1 = buy1 + cur;
            }
            if(buy2 < sell1 - cur){
                buy2 = sell1 - cur;
            }
            if(sell2 < buy2 + cur){
                sell2 = buy2 + cur;
            }
        }
        return sell2;
    }

    /**
     * 188. Best Time to Buy and Sell Stock IV (Hard)
     */
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int n = prices.length;
        k = Math.min(k, n/2);
        int[][][] dp = new int[n][2][k+1];
        //这里从0开始
        for(int i = 0; i <= k; i++){
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
//        dp[0][1][1] = -prices[0];
        for(int i = 1; i < n; i++){
            //这里从0开始 因为买入才算一次交易 有可能一直没买入啊
            //但如果从0开始 下面的j-1就会越界啊
            //那是为什么从1开始呢
            for(int j = 1; j <= k; j++){
                dp[i][0][j] = Math.max(dp[i-1][1][j]+prices[i], dp[i-1][0][j]);
                dp[i][1][j] = Math.max(dp[i-1][1][j], dp[i-1][0][j-1]-prices[i]);
            }
        }
        return dp[n-1][0][k];

    }

    /**
     * 583. Delete Operation for Two Strings (Medium)
     */
    public int minDistance(String s1, String s2) {
        if(s1 == null || s2 == null)
            return 0;
        return s1.length() + s2.length() - 2*minDistanceHelper(s1, s2, s1.length(), s2.length());
    }

    private int minDistanceHelper(String s1, String s2, int m, int n) {
        if(m == 0 || n == 0)
            return 0;
        if(s1.charAt(m-1) == s2.charAt(n-1))
            return 1 + minDistanceHelper(s1, s2, m-1, n-1);
        else
            return Math.max(minDistanceHelper(s1, s2, m-1, n), minDistanceHelper(s1, s2, m, n-1));
    }

    //recursion + memorization
    private int minDistanceHelper(String s1, String s2, int m, int n, int[][] memo) {
        if(m == 0 || n == 0)
            return 0;
        if(memo[m][n] > 0)
            return memo[m][n];
        if(s1.charAt(m-1) == s2.charAt(n-1))
            memo[m][n] = 1 + minDistanceHelper(s1, s2, m-1, n-1, memo);
        else
            memo[m][n] = Math.max(minDistanceHelper(s1, s2, m, n-1, memo), minDistanceHelper(s1, s2, m-1, n, memo));
        return memo[m][n];
    }

    //dp
    public int minDistance2(String s1, String s2) {
        if(s1 == null || s2 == null)
            return 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return m + n - 2*dp[m][n];
    }

    //dp 1d
    public int minDistance3(String s1, String s2) {
        if(s1 == null || s2 == null)
            return 0;
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n+1];
        for(int i = 1; i <= m; i++){
            int[] tmp = new int[n+1];
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    tmp[j] = dp[j-1] + 1;
                else
                    tmp[j] = Math.max(dp[j], tmp[j-1]);
            }
            dp = tmp;
        }
        return m + n - 2*dp[n];
    }

    //dp without lcs
    public int minDistance4(String s1, String s2){
        if(s1 == null || s2 == null)
            return 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(j == 0){
                    //dp[1][0]=1 dp[2][0] = 2
                    dp[i][j] = i;
                    continue;
                }
                if(i == 0){
                    dp[i][j] = j;
                    continue;
                }
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
            }
        }
        return dp[m][n];
    }

    /**
     * 72. Edit Distance (Hard)
     */
    public int minDistanceII(String s1, String s2) {
        if(s1 == null || s2 == null)
            return 0;
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0) {
                    dp[i][j] = i + j;
                    continue;
                }
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int tmp = Math.min(dp[i][j-1], dp[i-1][j-1]);
                    dp[i][j] = Math.min(tmp, dp[i-1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 650. 2 Keys Keyboard (Medium)
     */
    public int minSteps(int n) {
        if(n == 1) return 0;
        if(n < 1) return -1;
        int[][] dp = new int[n+1][n+1];
        for(int[] x : dp){
            Arrays.fill(x, n+1);
        }
        dp[1][0] = 0; dp[1][1] = 1;
        for(int i = 2; i <= n; i++){
            int min = n + 1;
            for(int j = 1; j <= i; j++){
                if(i != j){
                    dp[i][j] = dp[i-j][j] + 1;
                    min = Math.min(min, dp[i][j]);
                }else{
                    dp[i][i] = min + 1;
                }
            }
        }
        int res = n+1;
        for(int x: dp[n]){
            res = Math.min(res, x);
        }
        return res;
    }

    //dfs
    public int minSteps2(int n){
        if(n == 1) return 0;
        if(n < 1) return -1;
        return minSteps2Helper(n, 1, 0);
    }

    private int minSteps2Helper(int n, int cur, int paste) {
        if(cur == n)
            return 0;
        if(cur > n)
            return n+1;
        int p1 = cur != paste ? 1 + minSteps2Helper(n, cur, cur) : n+1;
        int p2 = paste > 0 ? 1 + minSteps2Helper(n, cur+paste, paste) : n+1;
        return Math.min(p1, p2);
    }

    //prime fatorization
    public int minSteps3(int n){
        if(n == 1) return 0;
        if(n < 1) return -1;
        int ans = 0;
        int d = 2;
        while(n > 1){
            while(n % d == 0){
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

}


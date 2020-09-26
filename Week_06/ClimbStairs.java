public class Solution {
    public int climbStairs(int n) {
        // dp[i][j] = 到第i+1级台阶最后一步走j+1级台阶有dp[i][j]种走法
        int[][] dp = new dp[n][3];
        dp[0][0] = 1;
        for (int i = 1;i < n;i++) {
            dp[i][0] = dp[i-1][1] + dp[i-1][2]; 
            dp[i][1] = i >= 2 ? dp[i - 2][0] + dp[i - 2][2] : 0;
            dp[i][2] = i >= 3 ? dp[i - 3][0] + dp[i - 1][1] : 0;
        }

        return dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2];
    }
}

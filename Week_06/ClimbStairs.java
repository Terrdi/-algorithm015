public class Solution {
    public int climbStairsPlus(int n) {
        // dp[i][j] = 到第i+1级台阶最后一步走j+1级台阶有dp[i][j]种走法
        int[][] dp = new int[n][3];
        dp[0][0] = 1;
        if (dp.length > 1) {
            dp[1][1] = 1;
        }
        if (dp.length > 2) {
            dp[2][2] = 1;
        }
        for (int i = 1;i < n;i++) {
            for (int j = 0;j < Math.min(i, 3);j++) {
                dp[i][j] = 0;
                for (int k = 0;k < 3;k++) {
                    if (k != j)
                        dp[i][j] += dp[i - j - 1][k];
                }
            }
        }

        return dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2];
    }
}

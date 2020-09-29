class Solution {
    /**
     * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的
     * left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
     *
     * 求所能获得硬币的最大数量。
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int[] vals = new int[n + 2];
        vals[0] = vals[n + 1] = 1;
        System.arraycopy(nums, 0, vals, 1, n);

        int[][] dp = new int[n + 2][n + 2];
        for (int i = n + 1;i >= 0;i--) {
            for (int j = i + 2;j < n + 2;j++) {
                for (int k = i + 1;k < j;k++) {
                    dp[i][j] = Math.max(dp[i][j], vals[i] * vals[k] * vals[j] + dp[i][k] + dp[k][j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
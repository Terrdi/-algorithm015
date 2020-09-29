class Solution {
    /**
     * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        return splitArrayBS(nums, m);
    }

    /**
     * 动态规划
     *
     * 时间复杂度 O(mn)
     * 空间复杂度 O(mn)
     * @param nums
     * @param m
     * @return
     */
    public int splitArrayDP(int[] nums, int m) {
        int len = nums.length;
        int[][] dp = new int[len + 1][m + 1];
        int[] sum = new int[len + 1];
        for (int i = 0;i <= len;i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            sum[i] = i > 0 ? sum[i - 1] + nums[i - 1] : 0;
        }

        dp[0][0] = 0;
        for (int i = 1;i <= len;i++) {
            for (int j = 1;j <= Math.min(i, m);j++) {
                for (int k = 0;k < i;k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }

        return dp[len][m];
    }

    /**
     * 二分
     *
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(1)
     * @param nums
     * @param m
     * @return
     */
    public int splitArrayBS(int[] nums, int m) {
        int left = Integer.MIN_VALUE, right = 0;

        for (int num : nums) {
            left = Math.max(num, left);
            right += num;
        }

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (split(nums, mid) > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int split(int[] nums, final int max) {
        int split = 1, sum = 0;
        for (int num : nums) {
            if (sum + num > max) {
                split++;
                sum = 0;
            }
            sum += num;
        }
        System.out.printf(", split = %d.\n", split);
        return split;
    }
}
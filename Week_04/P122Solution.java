class Solution {
    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        // 持股人一共有2种状态  持有股票/不持有股票
        int[][] MP = new int[prices.length][2];
        // MP[i][j] = 第i天 j=0 -> 不持有股票 / j=1 -> 持有股票 的最大利润
        int i = 0;
        // 第一天的状态
        MP[i][0] = 0;
        MP[i][1] = -prices[0];
        for (i = 1; i < prices.length; i++) {
            MP[i][0] = Math.max(MP[i - 1][0], MP[i - 1][1] + prices[i]);
            MP[i][1] = Math.max(MP[i - 1][1], MP[i - 1][0] - prices[i]);
        }

        return MP[i - 1][0];
    }
}
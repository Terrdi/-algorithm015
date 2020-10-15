class Solution {
    /**
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // one -> 一步到达当前节点有多少种走法
        // two -> 两步到达当前节点有多少种走法
        int one = 1, two = 0;
        for (int i = 1;i < n;i++) {
            one = two + (two = one);
        }
        return one + two;
    }
}
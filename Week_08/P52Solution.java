class Solution {
    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
     *
     * 时间复杂度 O(n!)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        AtomicInteger count = new AtomicInteger(0);
        _dfs(n, 0, 0, 0, 0, count);
        return count.get();
    }

    private void _dfs(int n, int row, int col, int pre, int na, AtomicInteger count) {
        if (row >= n) {
            count.getAndIncrement();
        } else {
            int bits = ((1 << n) - 1) & (~(col | pre | na));
            while (bits > 0) {
                int p = bits & (-bits);
                _dfs(n, row + 1, col | p, (pre | p) << 1, (na | p) >> 1, count);
                bits &= bits - 1;
            }
        }
    }
}
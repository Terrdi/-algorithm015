class Solution {
    private char[] POINTS = null;

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        return solveNQueensBIT(n);
    }

    /**
     * 位运算
     * 时间复杂度 O(n!)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public synchronized List<List<String>> solveNQueensBIT(int n) {
        POINTS = new char[n];
        Arrays.fill(POINTS, '.');
        List<List<String>> result = new ArrayList<>();
        _bit(n, new LinkedList<>(), result, 0, 0, 0);
        return result;
    }

    private void _bit(int n, Deque<String> cur, List<List<String>> result, int col, int pre, int na) {
        if (cur.size() >= n) {
            result.add(new ArrayList<>(cur));
        } else {
            int bits = ((1 << n) - 1) & ~(col | pre | na);
            while (bits > 0) {
                int p = bits & (-bits);
                cur.offer(toString(p));
                _bit(n, cur, result, col | p, (pre | p) << 1, (na | p) >> 1);
                cur.pollLast();
                bits = bits & (bits - 1);
            }
        }
    }

    private String toString(int p) {
        int i = 1, index = POINTS.length - 1;
        while (i <= p) {
            POINTS[index--] = (i & p) > 0 ? 'Q' : '.';
            i <<= 1;
        }
        String tmp = String.valueOf(POINTS);
        Arrays.fill(POINTS, '.');
        return tmp;
    }

    /**
     * 深度优先搜索
     *
     * 时间复杂度 O(n!)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public synchronized List<List<String>> solveNQueensDFS(int n) {
        POINTS = new char[n];
        Arrays.fill(POINTS, '.');
        List<List<String>> result = new ArrayList<>();
        _dfs(n, new LinkedList<>(), result, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return result;
    }

    private void _dfs(int n, Deque<String> cur, List<List<String>> result, Set<Integer> col, Set<Integer> pre, Set<Integer> na) {
        int r = cur.size();
        if (r >= n) {
            result.add(new ArrayList<>(cur));
        } else {
            for (int c = 0;c < n;c++) {
                if (col.contains(c) || pre.contains(r + c) || na.contains(r - c)) continue;
                col.add(c);
                pre.add(r + c);
                na.add(r - c);
                POINTS[c] = 'Q';
                cur.offerLast(String.valueOf(POINTS));
                POINTS[c] = '.';
                _dfs(n, cur, result, col, pre, na);
                cur.pollLast();
                col.remove(c);
                pre.remove(r + c);
                na.remove(r - c);
            }
        }
    }
}
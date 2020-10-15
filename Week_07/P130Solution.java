class Solution {
    private static final int[][] steps = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * @param board
     */
    public void solve(char[][] board) {
        solve2(board);
    }

    /**
     * 深度优先搜索
     * 时间复杂度 O(mn)
     * 空间复杂度 O(mn)
     * @param board
     */
    public void solve1(char[][] board) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        for (int i = 1;i < board.length - 1;i++) {
            for (int j = 1;j < board[i].length - 1;j++) {
                if (!visited.contains(new Pair<>(i, j)) || board[i][j] == 'O') {
                    Set<Pair<Integer, Integer>> tmp = new HashSet<>(visited);
                    if (solveHelper(board, i, j, tmp)) {
                        for (Pair<Integer, Integer> pair : tmp) {
                            if (!visited.contains(pair)) {
                                board[pair.getKey()][pair.getValue()] = 'X';
                            }
                        }
                    }
                    visited = tmp;
                }
            }
        }
    }

    private boolean solveHelper(char[][] board, int i, int j, Set<Pair<Integer, Integer>> visited) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) return false;
        if (board[i][j] == 'X' || !visited.add(new Pair<>(i, j))) return true;
        if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) return false;
        for (int[] step : steps) {
            if (!solveHelper(board, i + step[0], j + step[1], visited)) {
                return false;
            }
        }
        return true;
    }

    private static final char PLACE_HOLDER = '#';

    /**
     * 时间复杂度 O(mn)
     * 空间复杂度 O(1)
     * @param board
     */
    public void solve2(char[][] board) {
        if (Objects.isNull(board)) return;
        int m, n;
        if (0 == (m = board.length) || 0 == (n = board[m - 1].length)) return;
        for (int i = 0;i < Math.max(m, n);i++) {
            solveHelper(board, i, 0);
            solveHelper(board, i, n - 1);
            solveHelper(board, 0, i);
            solveHelper(board, m - 1, i);
        }

        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++)
                board[i][j] = board[i][j] == PLACE_HOLDER ? 'O' : 'X';
        }
    }

    private void solveHelper(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || board[i][j] != 'O') return;
        board[i][j] = PLACE_HOLDER;
        for (int[] step : steps) {
            solveHelper(board, i + step[0], j + step[1]);
        }
    }
}
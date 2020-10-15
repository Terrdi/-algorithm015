class Solution {
    private static final char[] tables = "123456789".toCharArray();

    /**
     * 编写一个程序，通过填充空格来解决数独问题。
     *
     * 一个数独的解法需遵循如下规则：
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     * 时间复杂度 O(9 ^ (mn))
     * 空间复杂度 O(mn)
     * @param board
     */
    public void solveSudoku(char[][] board) {
        List<Set<Character>> rows = new ArrayList<>(9),
                cols = new ArrayList<>(9),
                blocks = new ArrayList<>(9);

        for (int i = 0;i < board.length;i++) {
            for (int j = 0;j < board[i].length;j++) {
                while (rows.size() <= i) rows.add(new HashSet<>());
                while (cols.size() <= j) cols.add(new HashSet<>());
                int block = i / 3 * 3 + j / 3;
                while (blocks.size() <= block) blocks.add(new HashSet<>());
                char ch = board[i][j];
                if (ch != '.') {
                    rows.get(i).add(ch);
                    cols.get(j).add(ch);
                    blocks.get(block).add(ch);
                }
            }
        }
        _dfs(board, 0, 0, rows, cols, blocks);
    }

    private boolean _dfs(char[][] board, int i, int j, List<Set<Character>> rows,
                         List<Set<Character>> cols, List<Set<Character>> blocks) {
        if (i < 0 || j < 0) {
            return false;
        }
        if (i < board.length && j >= board[i].length) {
            i++;
            j = 0;
        }
        if (i >= board.length) return true;
        if (board[i][j] == '.') {
            int block = i / 3 * 3 + j / 3;
            for (char ch : tables) {
                if (rows.get(i).contains(ch) || cols.get(j).contains(ch) || blocks.get(block).contains(ch))
                    continue;
                rows.get(i).add(ch);
                cols.get(j).add(ch);
                blocks.get(block).add(ch);
                if (_dfs(board, i, j + 1, rows, cols, blocks)) {
                    board[i][j] = ch;
                    return true;
                }
                rows.get(i).remove(ch);
                cols.get(j).remove(ch);
                blocks.get(block).remove(ch);
            }
            return false;
        } else
            return _dfs(board, i, j + 1, rows, cols, blocks);
    }
}
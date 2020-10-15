class Solution {
    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     * 时间复杂度 O(mn)
     * 空间复杂度 O(mn)
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        List<Set<Character>> cols = new ArrayList<>(9),blocks = new ArrayList<>(9);
        for (int row = 0;row < 9;row++) {
            Set<Character> rows = new HashSet<>();
            for (int col = 0;col < 9;col++) {
                if (board[row][col] == '.') continue;
                if (!rows.add(board[row][col])) return false;
                while (cols.size() <= col) cols.add(new HashSet<>());
                if (!cols.get(col).add(board[row][col])) return false;
                int block = row / 3 * 3 + col / 3;
                while (blocks.size() <= block) blocks.add(new HashSet<>());
                if (!blocks.get(block).add(board[row][col])) return false;
            }
        }
        return true;
    }
}
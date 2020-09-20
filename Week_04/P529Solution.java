class Solution {
    private final int[][] steps = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    /**
     *
     *    让我们一起来玩扫雷游戏！
     *
     *    给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
     *
     *    现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
     *
     *    如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
     *    如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
     *    如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
     *    如果在此次点击中，若无更多方块可被揭露，则返回面板。
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        return this.updateBoardBFS(board, click);
    }

    /**
     * 广度优先遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoardBFS(char[][] board, int[] click) {
        Pair<Integer, Integer> c = new Pair<>(click[0], click[1]);
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        set.add(c);
        while (!set.isEmpty()) {
            Pair<Integer, Integer> pair = this.popOne(set);
            if (pair.getKey() >= 0 && pair.getValue() >= 0 && pair.getKey() < board.length && pair.getValue() < board[pair.getKey()].length) {
                switch (board[pair.getKey()][pair.getValue()]) {
                    case 'M':
                        board[pair.getKey()][pair.getValue()] = 'X';
                        break;
                    case 'E':
                        Set<Pair<Integer, Integer>> setTmp = new HashSet<>();
                        char e = '0';
                        for (int[] step : steps) {
                            if (pair.getKey() + step[0] >= 0 && pair.getValue() + step[1] >= 0
                                    && pair.getKey() + step[0] < board.length && pair.getValue() + step[1] < board[pair.getKey() + step[0]].length)
                                if (board[pair.getKey() + step[0]][pair.getValue() + step[1]] == 'M') {
                                    // 扫描到周围有一颗地雷
                                    e++;
                                } else {
                                    setTmp.add(new Pair<>(pair.getKey() + step[0], pair.getValue() + step[1]));
                                }
                        }
                        if (e == '0') {
                            board[pair.getKey()][pair.getValue()] = 'B';
                            set.addAll(setTmp);
                        } else {
                            board[pair.getKey()][pair.getValue()] = e;
                        }
                        break;
                    default:
                }
            }
        }

        return board;
    }

    /**
     * 深度优先遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoardDFS(char[][] board, int[] click) {
        if (click[0] < 0 || click[1] < 0 || click[0] >= board.length || click[1] >= board[click[0]].length) {
            return board;
        }
        switch (board[click[0]][click[1]]) {
            case 'M':
                // 点击地雷
                board[click[0]][click[1]] = 'X';
                break;
            case 'E':
                // 未知的数字, 扫描周围
                char e = '0';
                for (int[] step : steps) {
                    if (click[0] + step[0] >= 0 && click[1] + step[1] >= 0
                            && click[0] + step[0] < board.length && click[1] + step[1] < board[click[0] + step[0]].length
                            && board[click[0] + step[0]][click[1] + step[1]] == 'M')
                        // 扫描到周围有一棵地雷
                        e++;
                }
                if (e == '0') {
                    board[click[0]][click[1]] = 'B';
                    for (int[] step : steps) {
                        this.updateBoardDFS(board, new int[]{click[0] + step[0], click[1] + step[1]});
                    }
                } else {
                    board[click[0]][click[1]] = e;
                }
            default:

        }
        return board;
    }

    private <T> T popOne(Set<T> set) {
        java.util.Optional<T> one = set.stream().findFirst();
        if (one.isPresent()) {
            T o = one.get();
            set.remove(o);
            return o;
        } else {
            return null;
        }
    }
}
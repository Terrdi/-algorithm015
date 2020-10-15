class Solution {
    private static final int[][] steps = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 时间复杂度 O(mn)
     * 空间复杂度 O(mn)
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0;i < grid.length;i++) {
            for (int j = 0;j < grid[i].length;j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.offer(new Pair<>(i, j));
                    while (!queue.isEmpty()) {
                        Pair<Integer, Integer> pair = queue.poll();
                        if (pair.getKey() < 0 || pair.getValue() < 0
                                || pair.getKey() >= grid.length || pair.getValue() >= grid[pair.getKey()].length
                                || grid[pair.getKey()][pair.getValue()] == '0') continue;

                        grid[pair.getKey()][pair.getValue()] = '0';
                        for (int[] step : steps) {
                            queue.offer(new Pair<>(pair.getKey() + step[0], pair.getValue() + step[1]));
                        }
                    }
                }
            }
        }
        return count;
    }
}
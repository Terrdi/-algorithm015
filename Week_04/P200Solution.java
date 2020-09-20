class Solution {
    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        return numIslandsDFS(grid);
    }

    /**
     * 深度优先遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param grid
     * @return
     */
    public int numIslandsDFS(char[][] grid) {
        int count = 0;
        if (Objects.isNull(grid) || grid.length == 0) {
            return count;
        }
        for (int i = 0;i < grid.length;i++) {
            for (int j = 0;j < grid[i].length;j++) {
                if (grid[i][j] == '1') {
                    count++;
                    fillZero(grid, i, j);
                }
            }
        }

        return count;
    }

    private void fillZero(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        fillZero(grid, i - 1, j);
        fillZero(grid, i + 1, j);
        fillZero(grid, i, j - 1);
        fillZero(grid, i, j + 1);
    }

    /**
     * 广度优先遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param grid
     * @return
     */
    private int numIslandsBFS(char[][] grid) {
        int count = 0;
        if (Objects.isNull(grid) || grid.length == 0) {
            return count;
        }
        boolean[] visited = new boolean[grid.length * grid[0].length];
        Arrays.fill(visited, false);
        for (int i = 0;i < grid.length;i++) {
            for (int j = 0;j < grid[i].length;j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Deque<Pair<Integer, Integer>> deque = new LinkedList<>();
                    deque.push(new Pair<>(i, j));
                    while (!deque.isEmpty()) {
                        Pair<Integer, Integer> pair = deque.pop();
                        if (pair.getKey() >= 0 && pair.getValue() >= 0 && pair.getKey() < grid.length && pair.getValue() < grid[pair.getKey()].length
                                && grid[pair.getKey()][pair.getValue()] == '1') {
                            grid[pair.getKey()][pair.getValue()] = '0';
                            this.offer(deque, pair.getKey() + 1, pair.getValue(), visited, grid[i].length);
                            this.offer(deque, pair.getKey() - 1, pair.getValue(), visited, grid[i].length);
                            this.offer(deque, pair.getKey(), pair.getValue() + 1, visited, grid[i].length);
                            this.offer(deque, pair.getKey(), pair.getValue() - 1, visited, grid[i].length);
                        }
                    }
                }
            }
        }

        return count;
    }

    private void offer(Deque<Pair<Integer, Integer>> deque, int i, int j, boolean[] visited, int len) {
        if (i < 0 || j < 0 || j >= len) return;
        int index = i * len + j;
        if (index >= 0 && index < visited.length && !visited[index]) {
            visited[index] = true;
            deque.offer(new Pair<>(i, j));
        }
    }
}
class Solution {
    public int minPathSum(int[][] grid) {
        for (int i = 0;i < grid.length;i++) {
            for (int j = 0;j < grid[i].length;j++) {
                grid[i][j] += Math.max(Math.min(j > 0 ? grid[i][j - 1] : Integer.MAX_VALUE, i > 0 ? grid[i - 1][j] : Integer.MAX_VALUE) + 1, 1) - 1;
            }
        }

        return grid[grid.length - 1][grid[grid.length - 1].length - 1];
    }
}
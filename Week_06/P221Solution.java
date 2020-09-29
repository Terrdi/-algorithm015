class Solution {
    public int maximalSquare(char[][] matrix) {
        int m, n;
        if (Objects.isNull(matrix) || 0 == (m = matrix.length)) {
            return 0;
        }
        int[][] ints = new int[m][n = matrix[m - 1].length];
        int max = 0;

        for (int i = 0;i < m;i++) {
            for (int j = 0;i < m && j < n;j++) {
                switch (matrix[i][j]) {
                    case '1':
                        max = Math.max(max, ints[i][j] = 1 + Math.min(Math.min(i > 0 ? ints[i - 1][j] : 0, j > 0 ? ints[i][j - 1] : 0),
                                i > 0 && j > 0 ? ints[i - 1][j - 1] : 0));
                        break;
                    case '0':
                        if (j + max > n) {
                            j += max;
                        }
                    default:
                }
            }
        }

        return max * max;
    }
}
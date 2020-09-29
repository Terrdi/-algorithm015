class Solution {
    /**
     * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
     * 时间复杂度 O(m*n^2) m-非空二维矩阵的列数, n-非空二位矩阵的行数
     * 空间复杂度 O(m)
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int result = Integer.MIN_VALUE;
        if (Objects.isNull(matrix)) {
            return result;
        }

        int rows = matrix.length, col = 0;
        if (rows == 0 || 0 == (col = matrix[rows - 1].length)) {
            return result;
        }

        int[] rowSum = new int[rows];

        for (int l = 0;l < col;l++) {
            Arrays.fill(rowSum, 0);
            for (int r = l;r < col;r++) {
                int sum = 0;
                TreeSet<Integer> set = new TreeSet<>();
                for (int i = 0;i < rows;i++) {
                    rowSum[i] += matrix[i][r];
                    sum += rowSum[i];
                    if (sum < k) {
                        result = Math.max(sum, result);
                    } else if (sum == k) {
                        return k;
                    }
                    Integer cur = set.ceiling(sum - k);
                    if (cur != null) {
                        result = Math.max(result, sum - cur);
                    }

                    set.add(sum);
                }
            }
        }

        return result;
    }
}
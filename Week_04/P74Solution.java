class Solution {
    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     * 时间复杂度 O(m + log(n))
     * 空间复杂度 O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        if (M == 0) {
            return false;
        }
        int N = matrix[0].length;
        if (N == 0)
            return false;
        int i = 0;
        int low = 0, high = N - 1;
        while (i < M && target > matrix[i][high]) i++;
        if (i == M) {
            return false;
        }

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (matrix[i][mid] == target) {
                return true;
            } else if (matrix[i][mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
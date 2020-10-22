class Solution {
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int[][] result = new int[intervals.length][2];
        int index = 0;
        for (int[] interval : intervals) {
            int i = 0;
            for (;i < index;i++) {
                if (result[i][0] <= interval[1] && result[i][1] >= interval[0]) {
                    // 有交集
                    result[i][0] = Math.min(result[i][0], interval[0]);
                    result[i][1] = Math.max(result[i][1], interval[1]);
                    break;
                }
            }
            if (i == index) {
                result[index++] = interval;
            } else {
                // 检查后续的区间有无重叠
                for (int j = index - 1;j > i;j--) {
                    if (result[j][1] >= result[i][0] && result[j][0] <= result[i][1]) {
                        // 有交集
                        result[i][0] = Math.min(result[i][0], result[j][0]);
                        result[i][1] = Math.max(result[i][1], result[j][1]);
                        index--;
                        for (int k = j;k < index;k++) {
                            result[k][0] = result[k + 1][0];
                            result[k][1] = result[k + 1][1];
                        }
                    }
                }
            }
        }

        return Arrays.copyOf(result, index);
    }
}
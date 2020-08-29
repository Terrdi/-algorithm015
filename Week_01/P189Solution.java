class Solution {
    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int count = 0; // 完成旋转的元素个数
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }
        int start = 0;
        int tmp, cur;
        while (count < n) {
            cur = (start + k) % n;
            tmp = nums[start];
            while (cur != start && count < n) {
                int ntmp = nums[cur];
                nums[cur] = tmp;
                tmp = ntmp;
                cur = (cur + k) % n;
                count++;
            }
            nums[start++] = tmp;
            count++;
        }
    }
}
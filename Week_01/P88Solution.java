class Solution {
    /**
     * 时间复杂度 O(m + n)
     * 空间复杂度 O(1)
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;n--;
        for (int last = m + n + 1;last >= 0;last--) {
            if (m > -1 && n > -1) {
                if (nums1[m] > nums2[n]) {
                    nums1[last] = nums1[m];
                    m--;
                } else {
                    nums1[last] = nums2[n];
                    n--;
                }
            } else if (m > -1) {
                return;
            } else {
                System.arraycopy(nums2, 0, nums1, 0, n + 1);
                return;
            }
        }
    }
}
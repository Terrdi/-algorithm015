class Solution {
    /**
     * 给定一个数组  nums  ，如果  i < j  且  nums[i] > 2*nums[j]  我们就将  (i, j)  称作一个重要翻转对。
     *
     * 你需要返回给定数组中的重要翻转对的数量。
     *
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        return reversePairsHelper(nums, 0, nums.length - 1);
    }

    private int reversePairsHelper(int[] nums, int begin, int end) {
        if (begin >= end) return 0;

        int mid = begin + ((end - begin) >> 1);
        int count = reversePairsHelper(nums, begin, mid) + reversePairsHelper(nums, mid + 1, end);
        int[] cache = new int[end - begin + 1];
        int index = 0, j = mid + 1, t = j;
        for (int i = begin;i <= mid;i++) {
            while (t <= end && ((long) nums[i]) > ((long) nums[t] << 1)) t++;
            while (j <= end && nums[j] < nums[i]) cache[index++] = nums[j++];
            cache[index++] = nums[i];
            count += t - mid - 1;
        }
        System.arraycopy(cache, 0, nums, begin, index);
        return count;
    }
}
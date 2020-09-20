class Solution {
    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 时间复杂度 O(logn)
     * 空间复杂度 O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        long low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = Math.toIntExact(low + ((high - low) >> 1));
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[Math.toIntExact(low)]) {
                // low, mid 有序
                if (target >= nums[Math.toIntExact(low)] && target < nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {
                // mid, high 有序
                if (target > nums[mid] && target <= nums[Math.toIntExact(high)])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return -1;
    }
}
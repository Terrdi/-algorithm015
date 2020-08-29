class Solution {
    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int lastZeroFoundAt = 0;
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] != 0) {
                if (nums[i] != nums[lastZeroFoundAt]) {
                    nums[i] ^= nums[lastZeroFoundAt];
                    nums[lastZeroFoundAt] ^= nums[i];
                    nums[i] ^= nums[lastZeroFoundAt];
                }
                lastZeroFoundAt++;
            }
        }
    }
}

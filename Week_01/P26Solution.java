public class Solution {
    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int j = 0, i = 0;
        while (i < nums.length) {
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            nums[j++] = nums[i++];
        }
        return j;
    }
}
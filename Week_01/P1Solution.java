class Solution {
    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // key 为index value 为nums[index]的map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - i)) {
                return new int[] {map.get(target - i).intValue(), i};
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }
}
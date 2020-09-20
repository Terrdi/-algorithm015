class Solution {
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 贪心算法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int step = 0, end = 0, max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                step++;
                end = max;
            }
        }
        return step;
    }


    /**
     * 动态规划
     * 时间复杂度 O(n²)
     * 空间复杂度 O(n)
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        int[] steps = new int[nums.length];
        Arrays.fill(steps, nums.length);
        steps[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            if (steps[i] + 1 < steps[Math.min(i + nums[i], nums.length - 1)]) {
                for (int j = 1;j <= nums[i] && i + j < nums.length; j++) {
                    steps[i + j] = Math.min(steps[i + j], steps[i] + 1);
                }
            }
        }
        return steps[nums.length - 1];
    }
}
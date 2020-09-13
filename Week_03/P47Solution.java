class Solution {
    private boolean[] visited = null;

    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 时间复杂度 O(n * n!)
     * 空间复杂度 O(n)
     * @param nums
     * @return
     */
    public synchronized List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        visited = null;
        visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        Arrays.sort(nums);
        permuteUniqueHelper(nums, new ArrayDeque<>(nums.length), result);
        visited = null;
        return result;
    }

    private void permuteUniqueHelper(int[] nums, Deque<Integer> deque, List<List<Integer>> result) {
        if (deque.size() == nums.length) {
            result.add(new ArrayList<>(deque));
        } else {
            for (int i = 0; i < nums.length; i++) {
                /**
                 * 当前元素没有被访问 并且 上一个元素和当前元素不同或上一个元素已在集合中
                 */
                if (!visited[i] && (i == 0 || nums[i] != nums[i - 1] || visited[i - 1])) {
                    deque.offer(nums[i]);
                    visited[i] = true;
                    permuteUniqueHelper(nums, deque, result);
                    visited[i] = false;
                    deque.pollLast();
                }
            }
        }
    }
}
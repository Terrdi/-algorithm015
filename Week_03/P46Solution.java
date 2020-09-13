class Solution {
    private boolean[] visited = null;

    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 时间复杂度 O(n * n!)
     * 空间复杂度 O(n)
     * @param nums
     * @return
     */
    public synchronized List<List<Integer>> permute(int[] nums) {
        visited = null;
        visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        List<List<Integer>> result = new LinkedList<>();
        permuteHelper(nums, new LinkedList<>(), result);
        return result;
    }

    private void permuteHelper(int[] nums, Deque<Integer> deque, List<List<Integer>> result) {
        if (deque.size() == nums.length) {
            result.add(new ArrayList<>(deque));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    deque.offer(nums[i]);
                    permuteHelper(nums, deque, result);
                    deque.pollLast();
                    visited[i] = false;
                }
            }
        }
    }

    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 时间复杂度 O(n * n!)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums, 0, result);
        return result;
    }

    private void permuteHelper(int[] nums, int begin, List<List<Integer>> result) {
        if (begin == nums.length - 1) {
            result.add(asList(nums));
        } else {
            for (int i = begin;i < nums.length;i++) {
                swap(nums, begin, i);
                permuteHelper(nums, begin + 1, result);
                swap(nums, begin, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    private List<Integer> asList(int[] nums) {
        if (nums == null) return null;
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }
}
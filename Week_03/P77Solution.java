class Solution {
    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * 时间复杂度 O(n!/k!)
     * 空间复杂度 O(k)
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        helper(n, k, 1, new LinkedList<>(), result);
        return result;
    }

    private void helper(int n, int k, int i, Deque<Integer> deque, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(deque));
        } else {
            while (i <= n) {
                deque.offer(i);
                helper(n, k - 1, i + 1, deque, result);
                deque.pollLast();
                i++;
            }
        }
    }
}
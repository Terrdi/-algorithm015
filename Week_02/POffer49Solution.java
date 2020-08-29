class Solution {
    /**
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        priorityQueue.offer(1L);
        for (int i = 1; i < n; i++) {
            long m = priorityQueue.poll();
            set.remove(m);
            if (!set.contains(m * 2L)) {
                priorityQueue.offer(m * 2L);
                set.add(m * 2L);
            }
            if (!set.contains(m * 3L)) {
                priorityQueue.offer(m * 3L);
                set.add(m * 3L);
            }
            if (!set.contains(m * 5L)) {
                priorityQueue.offer(m * 5L);
                set.add(m * 5L);
            }
        }
        return Math.toIntExact(priorityQueue.peek());
    }

    /**
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        int[] dp = new int[n];
        int p2, p3, p5;
        int index;
        index = p2 = p3 = p5 = 0;
        dp[index++] = 1;
        while (index < n) {
            dp[index] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            if (dp[index] == dp[p2] * 2) p2++;
            if (dp[index] == dp[p3] * 3) p3++;
            if (dp[index] == dp[p5] * 5) p5++;
            index++;
        }
        return dp[index - 1];
    }
}
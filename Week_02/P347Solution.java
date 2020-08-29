class Solution {
    class Node implements Comparable<Node> {
        public Node(int val, int frequency) {
            this.val = val;
            this.frequency = frequency;
        }
        final int val;

        final int frequency;

        @Override
        public int compareTo(Node o) {
            if (o.frequency == this.frequency) return 0;
            return o.frequency > this.frequency ? -1 : 1;
        }
    }

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * 注： n为数组内不同的元素个数
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, AtomicInteger> map = new HashMap<>();
        for (int num : nums) {
            map.putIfAbsent(num, new AtomicInteger(0));
            map.get(num).getAndIncrement();
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        map.forEach((num, v) -> {
            priorityQueue.offer(new Node(num, v.get()));
            while (priorityQueue.size() > k) priorityQueue.poll();
        });
        int[] ints = new int[k];
        for (int i = 0; i < k && !priorityQueue.isEmpty(); i++) {
            ints[i] = priorityQueue.poll().val;
        }
        return ints;
    }
}
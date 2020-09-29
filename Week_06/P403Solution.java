class Solution {
    /**
     * 一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。 青蛙可以跳上石头，但是不可以跳入水中。
     *
     * 给定石子的位置列表（用单元格序号升序表示），请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。开始时，
     * 青蛙默认已站在第一个石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。
     *
     * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。
     * 另请注意，青蛙只能向前方（终点的方向）跳跃。
     *
     * 时间复杂度 O(n * 3^n)
     * 空间复杂度 O(3^n)
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        int dest = stones[stones.length - 1];

        Map<Integer, Set<Integer>> map = new HashMap<>();

        map.put(1, Collections.singleton(1));
        for (int i = 1;i < stones.length;i++) {
            int cur = stones[i];
            for (Integer step : map.getOrDefault(cur, Collections.emptySet())) {
                if (step > 1) {
                    map.putIfAbsent(step - 1 + cur, new HashSet<>());
                    map.get(step - 1 + cur).add(step - 1);
                }
                map.putIfAbsent(step + cur, new HashSet<>());
                map.get(step + cur).add(step);
                map.putIfAbsent(step + 1 + cur, new HashSet<>());
                map.get(step + 1 + cur).add(step + 1);
            }

            if (map.containsKey(dest)) {
                return true;
            }
            map.remove(cur);
        }

        return false;
    }
}
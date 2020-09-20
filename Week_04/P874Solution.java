class Solution {
    private final int[][] steps = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
     *
     * -2：向左转 90 度
     * -1：向右转 90 度
     * 1 <= x <= 9：向前移动 x 个单位长度
     * 在网格上有一些格子被视为障碍物。
     *
     * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
     *
     * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
     *
     * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。
     *
     * 时间复杂度 O(M X N) M为commands的长度 N为每次命令走的步数
     * 空间复杂度 O(L) obstacles的长度
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(makePair(obstacle[0], obstacle[1]));
        }
        int max = 0;
        int curX = 0, curY = 0;
        int stepIndex = 0;

        for (int command : commands) {
            switch (command) {
                case -2: stepIndex = (stepIndex + 3) % steps.length; break;
                case -1: stepIndex = (stepIndex + 1) % steps.length; break;
                default:
                    int curDx = steps[stepIndex][0], curDy = steps[stepIndex][1];
                    for (int i = 1; i <= command; i++) {
                        Pair<Integer, Integer> pair = makePair(curX + curDx, curY + curDy);
                        if (set.contains(pair)) break;
                        curX = pair.getKey(); curY = pair.getValue();
                    }
                    max = Math.max(max, curX * curX + curY * curY);
            }
        }
        return max;
    }

    private Pair<Integer, Integer> makePair(int obstacle0, int obstacle1) {
        return new Pair<>(obstacle0, obstacle1);
    }
}
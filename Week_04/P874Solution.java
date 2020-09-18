class Solution {
    private final int[][] steps = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     *
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
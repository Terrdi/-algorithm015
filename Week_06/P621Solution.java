class Solution {
    public int leastInterval1(char[] tasks, int n) {
        int[] times = new int[26];
        int[] taskNum = new int[26];

        Arrays.fill(times, Integer.MIN_VALUE);
        Arrays.fill(taskNum, 0);
        int num = 0;
        for (char task : tasks) {
            taskNum[task - 'A']++;
            num++;
        }

        int cur = 0;
        do {
            int index = -1;
            for (int i = 0; i < 26; i++) {
                if (taskNum[i] > 0 && times[i] + n < cur) {
                    if (index < 0 || taskNum[i] > taskNum[index]) {
                        index = i;
                    }
                }
            }
            if (index >= 0) {
                taskNum[index]--;
                num--;
                times[index] = cur;
            }
            cur++;
        } while (num > 0);

        return cur;
    }

    public int leastInterval(char[] tasks, int n) {
        int[] taskNum = new int[26];
        for (char task : tasks) {
            taskNum[task - 'A']++;
        }

        Arrays.sort(taskNum);
        int max = taskNum[25] - 1;
        int idleSlots = n * max;
        for (int i = 24;i >= 0 && idleSlots > 0;i--) {
            idleSlots -= Math.min(taskNum[i], max);
        }
        return Math.max(idleSlots, 0) + tasks.length;
    }
}
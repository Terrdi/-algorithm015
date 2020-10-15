class Solution {
    private final static char[] tables = "ACGT".toCharArray();

    /**
     * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
     *
     * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
     *
     * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
     *
     * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
     *
     * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
     *
     * 时间复杂度 O(n * len(start))
     * 空间复杂度 O(n)
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.remove(end)) return -1;

        bankSet.remove(start);
        Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
        set1.add(start);set2.add(end);

        int step = 1;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            Set<String> tmp = null;
            if (set1.size() > set2.size()) {
                tmp = set2;
                set2 = set1;
                set1 = tmp;
            }

            tmp = new HashSet<>();
            for (String val : set1) {
                char chars[] = val.toCharArray();
                for (int i = 0;i < chars.length;i++) {
                    char originalChar = chars[i];
                    for (char ch : tables) {
                        chars[i] = ch;
                        start = String.valueOf(chars);
                        if (bankSet.remove(start)) {
                            tmp.add(start);
                        } else if (set2.contains(start)) {
                            return step;
                        }
                    }
                    chars[i] = originalChar;
                }
            }

            set1 = tmp;
            step++;
        }
        return -1;
    }
}
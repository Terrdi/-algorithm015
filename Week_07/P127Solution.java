class Solution {
    private static final char[] tables = "qwertyuiopasdfghjklzxcvbnm".toCharArray();

    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     *
     * 时间复杂度 O(len(word) * n)
     * 空间复杂度 O(len(word) * n)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        wordSet.remove(beginWord);
        wordSet.remove(endWord);

        Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
        set1.add(beginWord);
        set2.add(endWord);

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
                char[] chars = val.toCharArray();
                for (int i = 0;i < chars.length;i++) {
                    char originalChar = chars[i];
                    for (char ch : tables) {
                        chars[i] = ch;
                        beginWord = String.valueOf(chars);
                        if (wordSet.remove(beginWord)) {
                            tmp.add(beginWord);
                        } else if (set2.contains(beginWord)) {
                            return step + 1;
                        }
                    }
                    chars[i] = originalChar;
                }
            }
            step++;
            set1 = tmp;
        }

        return 0;
    }
}
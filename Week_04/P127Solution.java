class Solution {
    private static final int NOT_FOUND = 0;

    /**
     * 题目中确定只会出现小写字母
     */
    private static final char[] table = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     *
     * L 单词长度
     * N wordList个数
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return ladderLengthDBfs(beginWord, endWord, wordList);
    }

    /**
     * 双向广度优先遍历
     * 从开始结点和结束结点触发, 每次选择度较小的结点进行广度优先遍历, 从而降低了遍历所需的时间
     * 时间复杂度 O(N * L)
     * 空间复杂度 O(N)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private int ladderLengthDBfs(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.contains(endWord)) {
            int step = 1;
            Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
            set1.add(beginWord);
            set2.add(endWord);
            wordSet.remove(beginWord);
            wordSet.remove(endWord);
            while (!set1.isEmpty() && !set2.isEmpty()) {
                if (set1.size() > set2.size()) {
                    Set<String> tmp = set2;
                    set2 = set1;
                    set1 = tmp;
                }
                Set<String> setTmp = new HashSet<>();
                while (!set1.isEmpty()) {
                    beginWord = this.popOne(set1);
                    char[] beginChars = beginWord.toCharArray();
                    for (int i = 0; i < beginChars.length; i++) {
                        char originChar = beginChars[i];
                        for (char ch : table) {
                            if (ch != originChar) {
                                beginChars[i] = ch;
                                beginWord = String.valueOf(beginChars);
                                if (wordSet.contains(beginWord)) {
                                    setTmp.add(beginWord);
                                    wordSet.remove(beginWord);
                                } else if (set2.contains(beginWord)) {
                                    return step + 1;
                                }
                            }
                        }
                        beginChars[i] = originChar;
                    }
                }
                step++;
                set1 = setTmp;
            }
        }
        return NOT_FOUND;
    }

    /**
     * 从集合中弹出一个元素
     * @param set
     * @param <T>
     * @return
     */
    private <T> T popOne(Set<T> set) {
        java.util.Optional<T> one = set.stream().findFirst();
        if (one.isPresent()) {
            T o = one.get();
            set.remove(o);
            return o;
        } else {
            return null;
        }
    }

    /**
     * 广度优先遍历
     * 时间复杂度 O(N * L)
     * 空间复杂度 O(N)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private int ladderLengthBfs(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.contains(endWord)) {
            return ladderLengthBfsHelper(beginWord, endWord, wordSet);
        } else {
            return NOT_FOUND;
        }
    }

    private int ladderLengthBfsHelper(String beginWord, String endWord, Set<String> wordSet) {
        int step = 1;
        Deque<String> deque = new LinkedList<>();
        deque.offer(beginWord);
        wordSet.remove(beginWord);
        while (!deque.isEmpty()) {
            Deque<String> dequeTmp = new LinkedList<>();
            while (!deque.isEmpty()) {
                beginWord = deque.poll();
                char[] beginChars = beginWord.toCharArray();
                for (int i = 0; i < beginChars.length; i++) {
                    char originalChar = beginChars[i];
                    for (char ch : table) {
                        if (originalChar != ch) {
                            beginChars[i] = ch;
                            beginWord = String.valueOf(beginChars);
                            if (wordSet.contains(beginWord)) {
                                if (Objects.equals(beginWord, endWord)) {
                                    return step + 1;
                                }
                                dequeTmp.push(beginWord);
                                wordSet.remove(beginWord);
                            }
                        }
                    }
                    beginChars[i] = originalChar;
                }
            }
            step++;
            deque = null;
            deque = dequeTmp;
        }
        return NOT_FOUND;
    }

    /**
     * 深度优先遍历 必须遍历完整棵树
     *
     * 时间复杂度 O(N * L * N!)
     * 空间复杂度 O(N)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private int ladderLengthDfs(String beginWord, String endWord, List<String> wordList) {
        List<Boolean> visited = new ArrayList<>(wordList.size());
        wordList.forEach(w -> visited.add(Boolean.FALSE));
        int result = ladderLengthDfsHelper(beginWord, endWord, wordList, visited);
        return result > wordList.size() ? NOT_FOUND : result;
    }

    private int ladderLengthDfsHelper(String beginWord, String endWord, List<String> wordList, List<Boolean> visited) {
        if (Objects.equals(beginWord, endWord)) {
            return 1;
        } else {
            int visitedIndex = 0, min = wordList.size() + 1;
            for (String word : wordList) {
                if (!visited.get(visitedIndex) && this.reachable(beginWord, word)) {
                    visited.set(visitedIndex, true);
                    min = Math.min(min, ladderLengthDfsHelper(word, endWord, wordList, visited) + 1);
                    visited.set(visitedIndex, false);
                }
                visitedIndex++;
            }
            return min;
        }
    }

    /**
     * 不合理的寻找下一层结点的方法
     * 时间复杂度 O(L)
     * @param word1
     * @param word2
     * @return
     */
    private boolean reachable(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        } else {
            int diff = 0;
            for (int i = 0; i < word1.length() && diff <= 1; i++) {
                if (!Objects.equals(word1.charAt(i), word2.charAt(i))) {
                    diff++;
                }
            }
            return diff == 1;
        }
    }
}
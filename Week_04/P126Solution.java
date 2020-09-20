class Solution {
    /**
     * 题目中确定只会出现小写字母
     */
    private static final char[] table = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换后得到的单词必须是字典中的单词。
     *
     * 单词个数 n
     * 单词长度 L
     *
     * 时间复杂度 O(n * L)
     * 空间复杂度 O(n)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(final String beginWord, final String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if (wordSet.contains(endWord)) {
            Set<String> visitedSet1 = new HashSet<>(), visitedSet2 = new HashSet<>();
            visitedSet1.add(beginWord);
            visitedSet2.add(endWord);
            Set<LinkedList<String>> set1 = new HashSet<>();
            Set<LinkedList<String>> set2 = new HashSet<>();
            set1.add(new LinkedList<>(Collections.singletonList(beginWord)));
            set2.add(new LinkedList<>(Collections.singletonList(endWord)));

            while (!set1.isEmpty() && !set2.isEmpty() && result.isEmpty()) {
                if (set1.size() > set2.size()) {
                    Set<LinkedList<String>> tmp = set1;
                    set1 = set2;
                    set2 = tmp;
                    Set<String> vTmp = visitedSet1;
                    visitedSet1 = visitedSet2;
                    visitedSet2 = vTmp;
                }

                Set<LinkedList<String>> setTmp = new HashSet<>();
                Set<String> visitTmp = new HashSet<>();
                while (!set1.isEmpty()) {
                    LinkedList<String> list = this.popOne(set1);
                    Set<String> curSet = new HashSet<>(list);
                    char[] curChars = list.peekLast().toCharArray();
                    for (int i = 0; i < curChars.length; i++) {
                        char originalChar = curChars[i];
                        for (char ch : table) {
                            if (originalChar != ch) {
                                curChars[i] = ch;
                                String curWord = String.valueOf(curChars);
                                if (wordSet.contains(curWord) && !curSet.contains(curWord)) {
                                    if (visitedSet2.contains(curWord)) {
                                        for (LinkedList<String> oList : set2) {
                                            if (Objects.equals(oList.peekLast(), curWord) && this.intersection(curSet, new HashSet<>(oList)).isEmpty()) {
                                                LinkedList<String> l1, l2;
                                                if (Objects.equals(list.peekFirst(), beginWord)) {
                                                    l1 = new LinkedList<>(list);
                                                    l2 = new LinkedList<>(oList);
                                                } else {
                                                    l1 = new LinkedList<>(oList);
                                                    l2 = new LinkedList<>(list);
                                                }
                                                while (!l2.isEmpty()) {
                                                    l1.add(l2.pollLast());
                                                }
                                                result.add(l1);
                                            }
                                        }
                                    }
                                    if (!visitedSet1.contains(curWord)) {
                                        visitTmp.add(curWord);
                                        list.addLast(curWord);
                                        setTmp.add(new LinkedList<>(list));
                                        list.pollLast();
                                    }
                                }
                            }
                        }
                        curChars[i] = originalChar;
                    }
                }
                set1 = setTmp;
                visitedSet1.addAll(visitTmp);
            }
        }
        return result;
    }

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

    private <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        if (Objects.isNull(set1) || Objects.isNull(set2) || set2.isEmpty()) {
            return Collections.emptySet();
        }

        return set1.parallelStream().filter(set2::contains).collect(Collectors.toSet());
    }
}
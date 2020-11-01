class Solution {
    /**
     *
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * 说明：
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.isEmpty() || p.isEmpty() || s.length() < p.length()) return Collections.emptyList();

        Map<Character, AtomicInteger> pMap = new HashMap<>(), sMap = new HashMap<>();
        for (Character ch : p.toCharArray()) {
            pMap.putIfAbsent(ch, new AtomicInteger());
            pMap.get(ch).incrementAndGet();
        }

        for (int i = 0;i < p.length();i++) {
            sMap.putIfAbsent(s.charAt(i), new AtomicInteger());
            sMap.get(s.charAt(i)).getAndIncrement();
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0;i < s.length() - p.length() + 1;i++) {
            if (i != 0) {
                sMap.get(s.charAt(i - 1)).decrementAndGet();
                sMap.putIfAbsent(s.charAt(i + p.length() - 1), new AtomicInteger());
                sMap.get(s.charAt(i + p.length() - 1)).getAndIncrement();
            }
            if (equals(sMap, pMap)) result.add(i);
        }
        return result;
    }

    private boolean equals(Map<Character, AtomicInteger> sMap, Map<Character, AtomicInteger> pMap) {
        for (Character key : pMap.keySet()) {
            if (!sMap.containsKey(key) || sMap.get(key).get() != pMap.get(key).get()) {
                return false;
            }
        }
        return true;
    }
}
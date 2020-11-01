class Solution {
    /**
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sMap = new HashMap<>(), tMap = new HashMap<>();
        for (int i = 0;i < s.length();i++) {
            if (sMap.containsKey(s.charAt(i)) && tMap.containsKey(t.charAt(i))) {
                if (sMap.get(s.charAt(i)) == t.charAt(i) && tMap.get(t.charAt(i)) == s.charAt(i)) {
                    continue;
                } else {
                    return false;
                }
            } else if (sMap.containsKey(s.charAt(i)) || tMap.containsKey(t.charAt(i))) {
                return false;
            } else {
                sMap.put(s.charAt(i), t.charAt(i));
                tMap.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;
    }
}
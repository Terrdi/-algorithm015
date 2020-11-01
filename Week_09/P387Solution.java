class Solution {
    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0;i < s.length();i++) {
            map.put(s.charAt(i), i);
        }
        for (int i = 0;i < s.length();i++) {
            if (map.getOrDefault(s.charAt(i), -1) == i) {
                return i;
            } else {
                map.remove(s.charAt(i));
            }
        }
        return -1;
    }
}
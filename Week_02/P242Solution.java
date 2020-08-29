class Solution {
    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] letter = new int[26];
        Arrays.fill(letter, 0);
        for (int i = 0; i < s.length(); i++) {
            letter[s.charAt(i) - 'a']++;
            letter[t.charAt(i) - 'a']--;
        }
        for (int i : letter) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
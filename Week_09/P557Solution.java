class Solution {
    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 时间复杂度 O(n)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        int left, right, len, ptr;
        ptr = left = right = 0;
        len = s.length();
        char[] chars = new char[len];
        while (left < len) {
            while (right < len && !Character.isWhitespace(s.charAt(right))) right++;
            for (int i = right - 1;i >= left;i--) {
                chars[ptr++] = s.charAt(i);
            }
            if (right < len) chars[ptr++] = s.charAt(right);
            left = ++right;
        }
        return String.valueOf(chars);
    }
}
class Solution {
    /**
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, true);
    }

    private boolean validPalindrome(String s, int left, int right, boolean flag) {
        if (left >= right || left < 0 || right >= s.length()) {
            return true;
        }
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (flag) {
                return validPalindrome(s, left + 1, right, false) || validPalindrome(s, left, right - 1, false);
            } else {
                return false;
            }
        }
        return true;
    }
}
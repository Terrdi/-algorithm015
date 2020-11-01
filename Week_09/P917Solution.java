class Solution {
    /**
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int index = 0, rIndex = S.length() - 1;
        while (index < chars.length) {
            if (Character.isLetter(chars[index])) {
                while (rIndex >= 0 && !Character.isLetter(S.charAt(rIndex))) rIndex--;
                chars[index++] = S.charAt(rIndex--);
            } else {
                index++;
            }
        }
        return String.valueOf(chars);
    }
}
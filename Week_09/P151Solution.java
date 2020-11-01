class Solution {
    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * 说明：
     * 无空格字符构成一个 单词 。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        stack.push(0);
        for (int i = 0;i < s.length();i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                while (i < s.length() - 1 && Character.isWhitespace(s.charAt(++i)));
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            char ch;
            if (Character.isWhitespace(ch = s.charAt(j))) continue;
            while (j < s.length() && Character.isLetterOrDigit(ch = s.charAt(j++))) {
                sb.append(ch);
            }
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
class Solution {
    /**
     * 给定一个只包含 '('和 ')'的字符串，找出最长的包含有效括号的子串的长度。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        return longestValidParenthesesDP(s);
    }

    /**
     * 栈
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @return
     */
    public int longestValidParenthesesStack(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int result = 0;
        for (int i = 0;i < s.length();i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }
        return result;
    }

    /**
     * 动态规划
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @return
     */
    public int longestValidParenthesesDP(String s) {
        int[] dp = new int[s.length()];
        int result = 0;
        for (int i = 0;i < dp.length;i++) {
            if (s.charAt(i) == ')' && i > 0) {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i > 1 ? dp[i - 2] + 2 : 2;
                } else if (i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] > 1 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 贪心算法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param s
     * @return
     */
    public int longestValidParenthesesDoublePointer(String s) {
        int left = 0, right = 0, maxLength = 0;
        for (int i = 0;i < s.length();i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, left + right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1;i >= 0;i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, left + right);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLength;
    }
}
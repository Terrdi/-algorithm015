class Solution {
    /**
     * 给定一个只包含 '('和 ')'的字符串，找出最长的包含有效括号的子串的长度。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        return longestValidParenthesesGreedy(s);
    }

    /**
     * 贪心算法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param s
     * @return
     */
    public int longestValidParenthesesGreedy(String s) {
        int result = 0;
        for (int i = 0, left = 0, right = 0;i < s.length();i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                result = Math.max(result, left << 1);
            } else if (left < right) {
                left = right = 0;
            }
        }

        for (int i = s.length() - 1, left = 0, right = 0;i >= 0;i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                result = Math.max(result, left << 1);
            } else if (left > right) {
                left = right = 0;
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
        int len = s.length(), result = 0;
        int[] dp = new int[len];

        for (int i = 1;i < len;i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] + 2 : 2);
                }
                result = Math.max(result, dp[i]);
            }
        }

        return result;
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
        int result = 0;
        stack.push(-1);
        for (int i = 0;i < s.length();i++) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push(i);
                    break;
                case ')':
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        result = Math.max(result, i - stack.peek());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Character " + s.charAt(i) + " At " + i + " of " + s);
            }
        }
        return result;
    }
}
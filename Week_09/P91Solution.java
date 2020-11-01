class Solution {
    /**
     *
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     *
     * 题目数据保证答案肯定是一个 32 位的整数。
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        if (s.charAt(0) == '0') return 0;
        dp[0] = 1;
        for (int i = 1;i < s.length();i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) != '1' && s.charAt(i - 1) != '2') {
                    return 0;
                } else {
                    dp[i] = i > 1 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6' ? dp[i - 1] + (i > 1 ? dp[i - 2] : 1) : dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }
}
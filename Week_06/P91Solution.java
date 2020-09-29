class Solution {
    /**
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length()];
        char[] ch = s.toCharArray();
        dp[0] = ch[0] > '0' ? 1 : 0;
        for (int i = 1;i < ch.length;i++) {
            if (ch[i] == '0') {
                if (ch[i - 1] == '1' || ch[i - 1] == '2')
                    dp[i] = i > 1 ? dp[i - 2] : dp[i - 1];
                else
                    return 0;
            } else {
                dp[i] = dp[i - 1] + (ch[i - 1] == '1' || ch[i - 1] == '2' && ch[i] <= '6' ?
                        (i > 1 ? dp[i - 2] : 1)
                        : 0);
            }

        }
        return dp[ch.length - 1];
    }
}

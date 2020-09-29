class Solution {
    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n)
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int len = s.length(), count = 0;
        boolean[] dp = new boolean[len];
        for (int i = 0;i < len; i++) {
            for (int j = 0;j <= i; j++) {
                if (i == j || s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1])) {
                    dp[j] = true;
                    count++;
                } else {
                    dp[j] = false;
                }
            }
        }
        return count;
    }
}
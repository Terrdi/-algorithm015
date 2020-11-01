class Solution {
    /**
     *
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
     * 说明：
     * 分隔时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     *
     * 时间复杂度 O(n * 2^n)
     * 空间复杂度 O(2^n)
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1;i <= len;i++) {
            for (int j = i - 1;j >= 0;j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        List<String> result = new ArrayList<>();
        if (dp[len]) {
            wordBreak(s, len, dp, set, new LinkedList<>(), result);
        }
        return result;
    }

    private void wordBreak(String s, int index, final boolean[] dp, Set<String> wordSet, Deque<String> deque, List<String> result) {
        if (index <= 0) {
            result.add(String.join(" ", deque));
            return;
        } else if (index > s.length()) {
            return;
        }
        String s1;
        for (int i = index - 1;i >= 0;i--) {
            if (dp[i] && wordSet.contains(s1 = s.substring(i, index))) {
                deque.offerFirst(s1);
                wordBreak(s, i, dp, wordSet, deque, result);
                deque.pollFirst();
            }
        }
    }
}
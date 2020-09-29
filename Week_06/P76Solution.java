class Solution {
    /**
     * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
     *
     * n - S的长度
     * m - T的长度
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(m)
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, AtomicInteger> needs = new HashMap<>();

        for (char ch : t.toCharArray()) {
            needs.putIfAbsent(ch, new AtomicInteger());
            needs.get(ch).incrementAndGet();
        }

        Map<Character, AtomicInteger> window = new HashMap<>();

        String result = "";
        int left, right, count, len;
        left = right = count = 0;
        len = s.length() + 1;
        while (right < s.length()) {
            char ch = s.charAt(right);
            window.putIfAbsent(ch, new AtomicInteger());
            if (needs.containsKey(ch) && needs.get(ch).get() >= window.get(ch).incrementAndGet()) {
                count++;
            }

            while (count == t.length()) {
                // left 右移
                char leftCh = s.charAt(left);
                if (needs.containsKey(leftCh) && needs.get(leftCh).get() >= window.get(leftCh).getAndDecrement()) {
                    count--;
                }

                if (right - left + 1 < len) {
                    len = right - left + 1;
                    result = s.substring(left, right + 1);
                }
                left++;
            }
            right++;
        }

        return result;
    }
}
class Solution {
    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        return isAnagramMap(s, t);
    }

    /**
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramSort(String s, String t) {
        char[] sch = s.toCharArray(), tch = t.toCharArray();
        final int len = sch.length;
        if (len != tch.length) return false;
        quickSort(sch, 0, len - 1);
        quickSort(tch, 0, len - 1);
        int i;
        for (i = 0;i < len && sch[i] == tch[i];i++);
        return i == len;
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(m) m为s字符串不同字符的个数
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramMap(String s, String t) {
        final int len = s.length();
        if (t.length() != len) return false;
        Map<Character, AtomicInteger> count = new HashMap<>();
        for (int i = 0;i < len;i++) {
            count.putIfAbsent(s.charAt(i), new AtomicInteger(0));
            count.get(s.charAt(i)).incrementAndGet();
        }

        for (int i = 0;i < len;i++) {
            int val = 0;
            if (count.containsKey(t.charAt(i))) {
                val = count.get(t.charAt(i)).getAndDecrement();
            }
            if (val == 0) return false;
        }
        return true;
    }

    private void quickSort(char[] ch, int begin, int end) {
        if (begin >= end) return;
        int l = begin, r = end;
        char c = ch[l];
        while (l < r) {
            while (l < r && ch[r] >= c) r--;
            if (l < r) ch[l++] = ch[r];
            while (l < r && ch[l] <= c) l++;
            if (l < r) ch[r--] = ch[l];
        }
        ch[l] = c;
        quickSort(ch, begin, l - 1);
        quickSort(ch, l + 1, end);
    }
}
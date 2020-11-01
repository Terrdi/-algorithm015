class Solution {
    /**
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        final int len = s.length();
        char[] chars = s.toCharArray();
        int start = 0;
        while (start < len) {
            k = Math.min(k, len - start);
            for (int i = start, j = start + k - 1;i < j;i++,j--) {
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
            }
            start += (k << 1);
        }
        return String.valueOf(chars);
    }
}
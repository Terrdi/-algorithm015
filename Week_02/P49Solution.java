class Solution {
    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 时间复杂度 O(n) n为strs所有包含的字符的个数
     * 空间复杂度 O(n)
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] letter = new int[26];
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Arrays.fill(letter, 0);
            for (int i = 0;i < str.length();i++) {
                letter[str.charAt(i) - 'a']++;
            }

            String key = Arrays.toString(letter);
            map.putIfAbsent(key, new LinkedList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
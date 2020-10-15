class Solution {
    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * 时间复杂度 O(2^n)
     * 空间复杂度 O(2^n)
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(new char[n << 1], 0, 0, n, result);
        return result;
    }

    private void generateParenthesis(char[] vals, int left, int right, int n, List<String> result) {
        if (right == n) {
            result.add(String.valueOf(vals));
        } else {
            if (left < n) {
                vals[left + right] = '(';
                generateParenthesis(vals, left + 1, right, n, result);
            }
            if (left > right) {
                vals[left + right] = ')';
                generateParenthesis(vals, left, right + 1, n, result);
            }
        }
    }
}
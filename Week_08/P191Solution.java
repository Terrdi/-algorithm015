public class Solution {
    /**
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (;n != 0;n&=n-1) count++;
        return count;
    }
}
class Solution {
public:
    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * @param digits
     * @return
    **/
    vector<int> plusOne(vector<int>& digits) {
        int radius = 10;
        int plus = 1;
        int index = digits.size() - 1;
        while (index > -1 && plus != 0) {
            int val = plus + digits[index];
            digits[index] = val % radius;
            plus = val / radius;
            index--;
        }
        if (plus != 0) {
            digits.insert(digits.begin(), plus);
        }

        return digits;
    }
};
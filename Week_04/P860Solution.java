class Solution {
    /**
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * 注意，一开始你手头没有任何零钱。
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int numOf5 = 0, numOf10 = 0, numOf20 = 0;
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    numOf5++;
                    break;
                case 10:
                    if (numOf5 < 1) return false;
                    numOf10++;
                    numOf5--;
                    break;
                case 20:
                    if (numOf10 > 0 && numOf5 > 0) {
                        numOf10--;
                        numOf5--;
                    } else if (numOf5 >= 3) {
                        numOf5 -= 3;
                    } else {
                        return false;
                    }
                    numOf20++;
                    break;
            }
        }
        return true;
    }
}
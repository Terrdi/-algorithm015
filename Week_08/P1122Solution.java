class Solution {
    /**
     * 给你两个数组，arr1 和 arr2，
     *
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     *
     * m 为 arr1 长度
     * n 为 arr2 长度
     * 时间复杂度 O(mn+mlogm)
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index = 0;
        int i = 0;
        while (index < arr2.length) {
            for (int j = i;j < arr1.length;j++) {
                if (arr2[index] == arr1[j]) {
                    int tmp = arr1[j];
                    arr1[j] = arr1[i];
                    arr1[i++] = tmp;
                }
            }
            index++;
        }
        Arrays.sort(arr1, i, arr1.length);
        return arr1;
    }
}
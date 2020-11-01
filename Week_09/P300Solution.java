class Solution {
    private int binarySearch(List<Integer> nums, int key) {
        int low = 0, high = nums.size() - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums.get(mid) > key) {
                high = mid - 1;
            } else if (nums.get(mid) < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        List<Integer> ns = new ArrayList<>(nums.length);
        for (int num : nums) {
            int index = binarySearch(ns, num);
            if (index == ns.size()) {
                ns.add(num);
            } else {
                ns.set(index, num);
            }
        }
        return ns.size();
    }
}
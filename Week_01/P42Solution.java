class Solution {
    /**
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n)
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0) {
            return sum;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                sum += (Math.min(height[i], height[stack.peek()]) - height[top]) * (i - stack.peek() - 1);
            }
            stack.push(i);
        }

        return sum;
    }
}
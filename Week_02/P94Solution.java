class Solution {
    /**
     * 给定一个二叉树，返回它的中序遍历。
     * 时间复杂度 O(n)
     * 空间复杂度 O(logn)
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        inorderTraversal1(root, result::add);
        return result;
    }

    private void inorderTraversal(TreeNode root, Consumer<? super Integer> consumer) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (Objects.nonNull(stack.peek())) stack.push(stack.peek().left);
            stack.pop();
            if (!stack.isEmpty()) {
                TreeNode p = stack.pop();
                consumer.accept(p.val);
                stack.push(p.right);
            }
        }
    }

    private void inorderTraversal1(TreeNode root, Consumer<? super Integer> consumer) {
        if (Objects.nonNull(root)) {
            inorderTraversal1(root.left, consumer);
            consumer.accept(root.val);
            inorderTraversal1(root.right, consumer);
        }
    }
}
class Solution {
    /**
     * 给定一个二叉树，返回它的 前序 遍历。
     * 时间复杂度 O(n)
     * 空间复杂度 O(logn)
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list::add);
        return list;
    }

    private void preorderTraversal(TreeNode root, Consumer<? super Integer> consumer) {
        if (Objects.nonNull(root)) {
            consumer.accept(root.val);
            preorderTraversal(root.left, consumer);
            preorderTraversal(root.right, consumer);
        }
    }

    private void preorderTraversal1(TreeNode root, Consumer<? super Integer> consumer) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();
            if (Objects.nonNull(p)) {
                consumer.accept(p.val);
                stack.push(p.left);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    p = stack.pop();
                    stack.push(p.right);
                }
            }
        }
    }
}
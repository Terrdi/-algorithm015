class Solution {
    /**
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     * 时间复杂度 O(n)
     * 空间复杂度 O(logn)
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new LinkedList<>();
        preorder(root, list::add);
        return list;
    }

    private void preorder(Node root, Consumer<? super Integer> consumer) {
        if (Objects.nonNull(root)) {
            consumer.accept(root.val);
            for (Node node : root.children) {
                preorder(node, consumer);
            }
        }
    }

    private void preorder1(Node root, Consumer<? super Integer> consumer) {
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node p = stack.poll();
            if (Objects.nonNull(p)) {
                consumer.accept(p.val);
                for (int i = p.children.size() - 1; i >= 0; i--) {
                    stack.offerFirst(p.children.get(i));
                }
            }
        }
    }
}
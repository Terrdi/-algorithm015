class Solution {
    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
     * 时间复杂度 O(n)
     * 空间复杂度 O(logn)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, 0, result);
        return result;
    }

    private void helper(Node root, int level, List<List<Integer>> result) {
        if (Objects.isNull(root)) {
            return;
        }
        while (result.size() < level + 1) {
            result.add(new LinkedList<>());
        }
        result.get(level).add(root.val);
        for (Node node : root.children) {
            helper(node, level + 1, result);
        }
    }
}
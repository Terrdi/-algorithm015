class Solution {
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 时间复杂度 O(n)
     * 空间复杂度 O(logn)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (Objects.isNull(root) || root == p || root == q) {
            return root;
        } else {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            return Objects.isNull(left) ? right : Objects.isNull(right) ? left : root;
        }
    }
}
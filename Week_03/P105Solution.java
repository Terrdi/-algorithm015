class Solution {
    private int inIndex, preIndex;

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。(看题解写的)
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param preorder
     * @param inorder
     * @return
     */
    public synchronized TreeNode buildTree(int[] preorder, int[] inorder) {
        inIndex = preIndex = 0;
        return buildTreeHelper(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int end) {
        if (preIndex >= preorder.length) return null;
        if (inorder[inIndex] == end) {
            // 左子树已经创建完毕, 开始创建右子树
            inIndex++;
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIndex++]);
        root.left = buildTreeHelper(preorder, inorder, root.val);
        root.right = buildTreeHelper(preorder, inorder, end);
        return root;
    }

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * 时间复杂度 O(n * log^2 n)
     * 空间复杂度 O(n * logn)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (isEmpty(inorder) || isEmpty(preorder)) {
            return null;
        }
        int val = preorder[0];
        TreeNode root = new TreeNode(val);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                // 左子树有i个结点
                int[] leftPreorder = Arrays.copyOfRange(preorder, 1, i + 1);
                int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);
                int[] rightPreorder = Arrays.copyOfRange(preorder, i + 1, preorder.length);
                int[] rightInorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                root.left = buildTree(leftPreorder, leftInorder);
                root.right = buildTree(rightPreorder, rightInorder);
                break;
            }
        }

        return root;
    }

    private boolean isEmpty(int[] ints) {
        return Objects.isNull(ints) || ints.length == 0;
    }
}
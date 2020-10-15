class Solution {
    /**
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
     *
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     *
     * 时间复杂度 O(M^2)
     * 空间复杂度 O(M)
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        UnionFind<Integer> unionFind = new UnionFind<>(generate(M.length));
        for (int i = 0;i < M.length;i++) {
            for (int j = i + 1;j < M[i].length;j++) {
                if (M[i][j] > 0) unionFind.union(i, j);
            }
        }
        return unionFind.getCount();
    }

    private List<Integer> generate(int N) {
        List<Integer> list = new ArrayList<>(N);
        for (int i = 0;i < N;i++) list.add(i);
        return list;
    }
}

class UnionFind<T> {
    /**
     * 并查集的一个节点
     * @param <T>
     */
    class UnionFindNode<T> {
        /**
         * 并查集的父节点
         * 当当前节点是根节点的时候等于自己
         */
        UnionFindNode<T> parent = this;

        /**
         * 当前节点存储的数据
         */
        final T data;

        public UnionFindNode(T data) {
            this.data = data;
        }
    }

    /**
     * 当前并查集集合的数量
     */
    private int count;

    /**
     * 存储并查集的结点
     */
    private Map<T, UnionFindNode<T>> union = new HashMap<>();

    public UnionFind(Iterable<T> collection) {
        for (T data : collection) {
            this.union.put(data, new UnionFindNode<>(data));
            this.count++;
        }
    }

    /**
     * 获取对应数据的根节点
     * @param data
     * @return
     */
    public T getParent(T data) {
        UnionFindNode<T> node = Objects.requireNonNull(this.union.get(data), data + " don't exist in the union find");
        UnionFindNode<T> tmp = node;
        while (tmp.parent != tmp) {
            tmp = tmp.parent;
        }
        node.parent = tmp;
        return tmp.data;
    }

    public int getCount() {
        return this.count;
    }

    /**
     * 合并两个并查集
     * @param data1
     * @param data2
     */
    public void union(T data1, T data2) {
        UnionFindNode<T> node1 = Objects.requireNonNull(this.union.get(data1), data1 + " don't exist in the union find"),
                node2 = Objects.requireNonNull(this.union.get(data2), data2 + " don't exist in the union find");
        while (node1.parent != node1) {
            node1 = node1.parent;
        }
        while (node2.parent != node2) {
            UnionFindNode<T> tmp = node2;
            node2 = node2.parent;
            tmp.parent = node1;
        }
        node2.parent = node1;
        if (node1 != node2) this.count--;
    }
}
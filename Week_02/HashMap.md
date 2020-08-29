# HashMap 源码分析(基于JDK 1.8)
### 存储结构
```java
public class HashMap {
    transient Node<K,V>[] table;
    static final int TREEIFY_THRESHOLD = 8;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
}
```
* **底层采用数组方式**
* **处理哈希冲突采用链式哈希的方式**
* **当哈希桶内元素的个数大于 TREEIFY_THRESHOLD 的时候, 该哈希桶的底层实现将变成红黑树**
* **当table的table.length \* DEFAULT_LOAD_FACTOR部分Node不再为null的时候，就需要扩充table,即java.util.HashMap#resize方法，将Node扩充为原来长度的两倍，再将节点重新hash一遍。**

### 放入
```java
public class HashMap {
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 对hashtable进行初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 对不存在hash冲突的元素直接放入哈希表中
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            // 处理hash冲突
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                // 当前结点的key和放入的key相等
                e = p;
            else if (p instanceof TreeNode)
                // 将当前结点放入红黑树中
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    // 对当前结点所在哈希桶进行遍历

                    // 找到当前哈希桶的尾节点
                    if ((e = p.next) == null) {
                        // 将当前结点插入到哈希桶的尾部
                        p.next = newNode(hash, key, value, null);
                        // 如果当前哈希桶的长度大于 TREEIFY_THRESHOLD(默认值是8), 则将当前哈希桶修改红黑树结构
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 找到和当前值key相同的结点
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    // 没有找到，继续往下找
                    p = e;
                }
            }
            // 在链式哈希桶内找到了存在的相同key的元素，如果onlyIfAbsent是true, 返回旧值并将新值设置进去
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        // 在链式哈希桶内没有找到存在相同key的元素，完成了元素的扩充
        ++modCount;
        // 如果size达到一定数量，则将扩充哈希表来减少哈希冲突的概率
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
}
```

### 查找
```java
public class HashMap {
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            // 对当前key进行hash, 并且hash之后的结点不为空
            if (first.hash == hash && // always check first node
                // 当前结点就是需要查找的结点, 返回
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    // 如果当前结点是红黑树, 在红黑树中查找结点
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    // 否则遍历 哈希桶
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        // 找不到返回null
        return null;
    }
}
```

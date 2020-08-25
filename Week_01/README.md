学习笔记

## PriorityQueue 描述
**java.util.PriorityQueue的底层数据结构是小顶堆, 而这个小顶堆是用顺序存储的二叉树来实现的。**
代码如下：
```java
public class PriorityQueue {
    transient Object[] queue;
}
```

#### 增加元素
```java
public class PriorityQueue {
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)  // 注释1
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0) // 注释1
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

}
```
*很明显得可以看到 *
优先队列在加入元素的时候, 会根据构造时有无构造器走不同的流程, 
* 有构造器会调用 {@link java.util.PriorityQueue#siftUpUsingComparator}
* 没有构造器会调用 {@link java.util.PriorityQueue#siftUpComparable}，该方法采用的比较是 <E> 的默认比较器
* 在插入元素的时候，会首先进行扩容，然后执行小顶堆的向上筛选插入步骤，即将该元素放在所有元素之后，然后依次与父节点进行比较（注释1 所在位置），如果该节点比起父节点小，则交换两节点，直到该节点大于父节点，或者该结点已经成为了根节点
* 这样对元素进行插入就保证了根节点是最小（优先级最高）的节点
* 易得，这种优先队列的插入操作的时间复杂度为 O(logn)

#### 删除元素
```java
public class PriorityQueue {
    public E poll() {
        if (size == 0)
            return null;
        int s = --size;
        modCount++;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x);
        return result;
    }
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, (E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }
}
```
优先队列在删除元素的时候 会根据构造时有无构造器走不同的流程, 
* 有构造器会调用 {@link java.util.PriorityQueue#siftDownUsingComparator}
* 没有构造器会调用 {@link java.util.PriorityQueue#siftDownComparable}，该方法采用的比较是 <E> 的默认比较器
* 在删除元素的时候，会返回堆顶的元素queue[0] , 然后将最后一个元素（也就是叶子结点）放到堆顶来，然后将该元素进行下沉筛选，也就是和其孩子结点比较，如果其孩子结点小于该结点，则交换，直到其孩子结点大于该结点或者该结点成为了叶子结点
* 这样就保证了删除该结点后，当前队列依旧是一个优先队列
* 易得，这种优先队列的删除操作的时间复杂度为 O(logn)

#### 访问元素
```java
public class PriorityQueue {
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }
}
```
优先队列访问元素一般只访问堆顶元素 queue[0] 即可。
即访问元素的时间复杂度为O(1)


#### 遍历
优先队列的遍历是通过迭代器完成的
```java
private final class Itr implements Iterator<E> {
    private int cursor = 0; // 当前访问的位置

    private int lastRet = -1; // 上一次访问的位置，留作删除元素

    /**
     * 小顶堆删除元素会改变结点分布，这个变量存储因为删除导致结点的位移在当前访问结点之前，需将该结点加入到这个队列中 留待访问
    **/
    private ArrayDeque<E> forgetMeNot = null; 

    /**
     * next() 进入 forgetMeNot 流程的队头， 如果调用 remove() 方法，将会删除优先队列中的的这个结点
    **/
    private E lastRetElt = null;

    private int expectedModCount = modCount;

    public boolean hasNext() {
        return cursor < size ||
            (forgetMeNot != null && !forgetMeNot.isEmpty());
    }

    @SuppressWarnings("unchecked")
    public E next() {
        if (expectedModCount != modCount)
            throw new ConcurrentModificationException();
        if (cursor < size)
            return (E) queue[lastRet = cursor++];
        if (forgetMeNot != null) {
            lastRet = -1;
            lastRetElt = forgetMeNot.poll();
            if (lastRetElt != null)
                return lastRetElt;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (expectedModCount != modCount)
            throw new ConcurrentModificationException();
        if (lastRet != -1) {
            E moved = PriorityQueue.this.removeAt(lastRet);
            lastRet = -1;
            if (moved == null)
                cursor--;
            else {
                if (forgetMeNot == null)
                    forgetMeNot = new ArrayDeque<>();
                forgetMeNot.add(moved);
            }
        } else if (lastRetElt != null) {
            PriorityQueue.this.removeEq(lastRetElt);
            lastRetElt = null;
        } else {
            throw new IllegalStateException();
        }
        expectedModCount = modCount;
    }
}
```


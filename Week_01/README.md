学习笔记

### 数组与链表对比
数组简单易用，在实现上使用的是连续的内存空间，可以借助 CPU 的缓存机制，预读数组中的数据，所以访问效率更高。而链表在内存中并不是连续存储，所以对 CPU 缓存不友好，没办法有效预读。
**数组的缺点是大小固定**，一经声明就要占用整块连续内存空间。如果声明的数组过大，系统可能没有足够的连续内存空间分配给它，导致“内存不足（out of memory）”。如果声明的数组过小，则可能出现不够用的情况。这时只能再申请一个更大的内存空间，把原数组拷贝进去，非常费时。**链表本身没有大小的限制，天然地支持动态扩容**。

跳表在**有序链表**的基础上，增加了多级索引，通过索引位置的几个跳转，实现数据的快速定位。
当数据量很大时，跳表的查找复杂度就是 O(logN)。

### Array
* 插入 O(n)
* 删除 O(n)
* 随机访问 O(1)

### LinkedList
* 插入 O(1)
* 删除 O(1)
* 随机访问 O(n)

## 操作受限的线性表
### Stack
* 先入后出 FILO(First In, Last Out)
* 适合变量计算前后具有相关性的算法

### Queue
* 先入先出 FIFO(First In, First Out)
* 染色问题

### Deque
* 双端队列 可以从两端添加、删除、获取元素
* LRU缓存

## 优化数据结构
### SkipList
* 插入 O(logn)   *需要判断插入索引*
* 删除 O(logn)   *需要判断删除索引*
* 随机访问 O(logn)*直接索引实现快速访问*
* **基于有序链表**
* 索引可以分级，同一级索引依然有序
* Redis 的Sorted Set就是采用跳表完成的
* 以空间换时间的算法设计思想
* n维无法解决的问题可以提升到(n+1)维


# 作业
* ![用 add first 或 add last 这套新的 API 改写 Deque 的代码](./DequeDemo.java)
* ![分析 Queue 和 Priority Queue 的源码](./QueueSourceAnalyzer.md)
* ![删除排序数组中的重复项](./P26Solution.java)
* ![旋转数组](./P189Solution.java)
* ![合并两个有序链表](./P21Solution.java)
* ![合并两个有序数组](./P88Solution.java)
* ![两数之和](./P1Solution.java)
* ![移动零](./P283Solution.java)
* ![加一](./P66Solution.cpp)
* ![设计循环双端队列](./MyCircularDeque.java)
* ![接雨水](./P66Solution.cpp)


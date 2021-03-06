# 树
树状图是一种数据结构，它是由n（n>=1）个有限结点组成一个具有层次关系的集合。
树是没有环的图。

## 无序树
树中任意结点的子节点没有顺序关系, 即交换任意一个结点的任意孩子结点之后的树与原树相等

## 有序树
树中任意结点的子节点存在顺序关系, 与无序树相对。

### 二叉树
树中任意结点的度小于2的有序树

#### 满二叉树
叶子结点以外的所有节点的度都为2的二叉树。

#### 完全二叉树 
若设二叉树的深度为k，除第 k 层外，其它各层 (1～k-1) 的结点数都达到最大个数，第k 层所有的结点都连续集中在最左边，这就是完全二叉树。

#### 二叉搜索树
对于二叉树的任意一个节点, 它**左子树的所有的结点**都小于该结点, 它**右子树的所有的结点**都大于该结点。

# 图
由一系列顶点和端点在这些顶点上的边构成的数据集合。

## 有向图
边是有方向的图
*每个顶点的入度和出度有可能不一样*

## 无向图
边没有方向的图
*每个顶点的入度和出度一定一致*

## 存储方式
#### 邻接矩阵
![邻接矩阵](https://images0.cnblogs.com/blog2015/685494/201507/031626066545035.png)
#### 邻接表
每个节点后的链表表示该结点可以直接到达的结点
![邻接表](https://images0.cnblogs.com/blog2015/685494/201507/031853062738289.png)
#### 逆邻接表
每个节点后的链表表示可以直接到达该结点的结点
![逆邻接表](https://images0.cnblogs.com/blog2015/685494/201507/031853062738289.png)
#### 十字链表
每个边结点分别需要指向弧头结点的下一个边结点和弧尾结点的下一个边结点。
![十字链表](http://data.biancheng.net/uploads/allimg/190107/2-1Z10H11122456.gif)

# 堆
堆是一系列操作的集合（接口），可以快速找到最大结点/最小结点的数据结构，即可称为大顶堆/小顶堆

|操作|找最小/最大值|删除最小/最大值|插入一个节点|根据key删除结点|合并|
| --- | --- | --- | --- | --- | --- |
| 二叉堆 | O(1) | O(log n) | O(log n) | O(log n) | O(n) |
| 斐波那契堆 | O(1) | O(log n) | O(1) | O(1) | O (1) |
| 严格斐波那契堆 | O(1) | O(log n) | O(1) | O(1) | O (1) |


# 作业
* [写一个关于 HashMap 的小总结](./HashMap.md)
* [有效的字母异位词](./P242Solution.java)
* [两数之和](./P1Solution.java)
* [N 叉树的前序遍历](./P589Solution.java)
* [字母异位词分组](./P49Solution.java)
* [二叉树的中序遍历](./P94Solution.java)
* [二叉树的前序遍历](./P144Solution.java)
* [N 叉树的层序遍历](./P429Solution.java)
* [丑数](./POffer49Solution.java)
* [前 K 个高频元素](./P347Solution.java)
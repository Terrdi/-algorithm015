# 深度优先
**访问第一个结点直至无法访问然后回溯**

### 代码模版
#### 递归
```java
class DFS {
    private Set<TreeNode> visited = new java.util.HashSet<>();
    private void travel(TreeNode root, java.util.function.Consumer consumer){
        if (Objects.nonNull(root) && !visited.contains(root)) {
            consumer.accept(root.val);
            travel(root.left, consumer);
            travel(root.right, consumer);
        }
    }
}
```

```cpp
class DFS {
private:
    map<int, int> visited;

public:
    void dfs(Node* root) {
      // terminator
      if (!root) return ;
    
      if (visited.count(root->val)) {
        // already visited
        return ;
      }
    
      visited[root->val] = 1;
    
      // process current node here. 
      // ...
      for (int i = 0; i < root->children.size(); ++i) {
        dfs(root->children[i]);
      }
      return ;
    }
}
```

```Python
visited = set() 

def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 

	visited.add(node) 

	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```

```javascript
//JavaScript
const visited = new Set()
const dfs = node => {
  if (visited.has(node)) return
  visited.add(node)
  dfs(node.left)
  dfs(node.right)
}
```

#### 非递归
```java
class DFS {
    private Set<TreeNode> visited = new java.util.HashSet<>();
    private void travel(TreeNode root, java.util.function.Consumer consumer){
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();
            while (Objects.nonNull(p) && !visited.contains(p)) {
                 consumer.accept(p.val);
                 visited.add(p);
                 stack.push(p = p.left);
            }
            stack.pop();
            p = stack.peek();
            if (Objects.isNull(p.right) || !visited.contains(p.right)) {
                stack.push(p.right);
            }
        }
    }
}
```

```cpp
//C/C++
//非递归写法：
void dfs(Node* root) {
  map<int, int> visited;
  if(!root) return ;

  stack<Node*> stackNode;
  stackNode.push(root);

  while (!stackNode.empty()) {
    Node* node = stackNode.top();
    stackNode.pop();
    if (visited.count(node->val)) continue;
    visited[node->val] = 1;


    for (int i = node->children.size() - 1; i >= 0; --i) {
        stackNode.push(node->children[i]);
    }
  }

  return ;
}
```
```Python
def DFS(self, tree): 

	if tree.root is None: 
		return [] 

	visited, stack = [], [tree.root]

	while stack: 
		node = stack.pop() 
		visited.add(node)

		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 

	# other processing work 
	...
```


```java
class BinarySearch {
    public int findStartIndex(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else  {
                high = mid;
            }
        }

        return low;
    }
}
```

# 广度优先遍历
遍历一个节点, 将该结点的子节点遍历一遍后再继续遍历子节点的子节点

### 代码模版
```java
//Java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                results.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            allResults.add(results);
        }
        return allResults;
    }
}
```

```cpp
// C/C++
void bfs(Node* root) {
  map<int, int> visited;
  if(!root) return ;

  queue<Node*> queueNode;
  queueNode.push(root);

  while (!queueNode.empty()) {
    Node* node = queueNode.top();
    queueNode.pop();
    if (visited.count(node->val)) continue;
    visited[node->val] = 1;

    for (int i = 0; i < node->children.size(); ++i) {
        queueNode.push(node->children[i]);
    }
  }

  return ;
}
```

```Python
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 
	while queue: 
		node = queue.pop() 
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)
	# other processing work 
```

```javascript
//JavaScript
const bfs = (root) => {
  let result = [], queue = [root]
  while (queue.length > 0) {
    let level = [], n = queue.length
    for (let i = 0; i < n; i++) {
      let node = queue.pop()
      level.push(node.val) 
      if (node.left) queue.unshift(node.left)
      if (node.right) queue.unshift(node.right)
    }
    result.push(level)
  }
  return result
};
```

# 贪心算法（Greedy）
贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。

## 优势
一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求得的答案比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。

## 局限
需要证明通过局部最优可以扩展到全局最优, 部分问题无法使用贪心算法

# 二分查找
二分思想，每次都与区间的中间数据比对大小，缩小查找区间的范围。
![二分查找示意图](https://static001.geekbang.org/resource/image/8b/29/8bce81259abf0e9a06f115e22586b829.jpg)
**二分查找针对的是一个有序的数据集合，查找思想有点类似分治思想。每次都通过跟区间的中间元素对比，将待查找的区间缩小为之前的一半，直到找到要查找的元素，或者区间被缩小为 0。**

#### 时间复杂度
假设数据大小是 n，每次查找后数据都会缩小为原来的一半，也就是会除以 2。最坏情况下，直到查找区间被缩小为空，才停止。
中 n/2k=1 时，k 的值就是总共缩小的次数。而每一次缩小操作只涉及两个数据的大小比较，所以，经过了 k 次区间缩小操作，时间复杂度就是 O(k)。通过 n/2k=1，可以求得 k=log2n，所以时间复杂度就是 **O(logn)** 。——对数时间复杂度

## 二分查找的递归与非递归实现
二分查找最简单的情况就是有序数组中不存在重复的元素。
### 非递归实现
```java
public int bsearch(int[] a, int n, int value) {
  int low = 0;
  int high = n - 1;

  while (low <= high) {
    int mid = (low + high) / 2;
    if (a[mid] == value) {
      return mid;
    } else if (a[mid] < value) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }
  }

  return -1;
}
```
#### 容易出错的地方
* 循环推出条件 low<=high
* mid的取值 mid=(low+high)/2 有可能溢出，应该为 mid = low+((high - low) >> 1)
* low和high的更新 low=mid+1, high=mid-1

### 递归实现
```java

// 二分查找的递归实现
public int bsearch(int[] a, int n, int val) {
  return bsearchInternally(a, 0, n - 1, val);
}

private int bsearchInternally(int[] a, int low, int high, int value) {
  if (low > high) return -1;

  int mid =  low + ((high - low) >> 1);
  if (a[mid] == value) {
    return mid;
  } else if (a[mid] < value) {
    return bsearchInternally(a, mid+1, high, value);
  } else {
    return bsearchInternally(a, low, mid-1, value);
  }
}
```

## 二分查找应用场景的局限性
#### 二分查找依赖的是顺序表结构->数组
主要原因是二分查找算法需要按照下标随机访问元素。
#### 二分查找针对的是有序数据
二分查找对这一点的要求比较苛刻，数据必须是有序的。如果数据没有序，我们需要先排序。前面章节里我们讲到，排序的时间复杂度最低是 O(nlogn)。
如果我们针对的是一组静态的数据，没有频繁地插入、删除，我们可以进行一次排序，多次二分查找。这样排序的成本可被均摊，二分查找的边际成本就会比较低。
#### 二分查找需要有明确的上下界
二分查找是在一定范围内进行查找, 需要确定的是所查找的数据如果存在就一定在这个范围内。
#### 数据量太小不适合二分查找
如果要处理的数据量很小，完全没有必要用二分查找，顺序查找就足够了。
如果数据之间的比较操作非常耗时，不管数据量大小，都适合使用二分查找。
#### 数据量太大不适合二分查找
二分查找的底层需要依赖数组这种数据结构，而数组为了支持随机访问的特性，**要求内存空间连续**，对内存的要求比较苛刻。

# 作业
* [柠檬水找零](./P860Solution.java)
* [买卖股票的最佳时机](./P122Solution.java)
* [分发饼干](./P455Solution.java)
* [模拟行走机器人](./P874Solution.java)
* [单词接龙](./P127Solution.java)
* [岛屿数量](./P200Solution.java)
* [扫雷游戏](./P529Solution.java)
* [跳跃游戏](./P55Solution.java)
* [搜索旋转排序数组](./P33Solution.java)
* [搜索二维矩阵](./P74Solution.java)
* [寻找旋转排序数组中的最小值](./P153Solution.java)
* [单词接龙 II](./P126Solution.java)
* [跳跃游戏 II](./P45Solution.java)
# Trie 树代码模版
```python
class Trie(object):
  
	def __init__(self): 
		self.root = {} 
		self.end_of_word = "#" 
 
	def insert(self, word): 
		node = self.root 
		for char in word: 
			node = node.setdefault(char, {}) 
		node[self.end_of_word] = self.end_of_word 
 
	def search(self, word): 
		node = self.root 
		for char in word: 
			if char not in node: 
				return False 
			node = node[char] 
		return self.end_of_word in node 
 
	def startsWith(self, prefix): 
		node = self.root 
		for char in prefix: 
			if char not in node: 
				return False 
			node = node[char] 
		return True
```

```cpp
class Trie {
    struct TrieNode {
        map<char, TrieNode*>child_table;
        int end;
        TrieNode(): end(0) {}
    };
        
public:
    /** Initialize your data structure here. */
    Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    void insert(string word) {
        TrieNode *curr = root;
        for (int i = 0; i < word.size(); i++) {
            if (curr->child_table.count(word[i]) == 0)
                curr->child_table[word[i]] = new TrieNode();
                
            curr = curr->child_table[word[i]];                
        }
        curr->end = 1;
    }
    
    /** Returns if the word is in the trie. */
    bool search(string word) {
        return find(word, 1);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string prefix) {
        return find(prefix, 0);
    }
private:
    TrieNode* root;
    bool find(string s, int exact_match) {
        TrieNode *curr = root;
        for (int i = 0; i < s.size(); i++) {
            if (curr->child_table.count(s[i]) == 0)
                return false;
            else
                curr = curr->child_table[s[i]];
        }
        
        if (exact_match)
            return (curr->end) ? true : false;
        else
            return true;
    }
};
```

```java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```

```javascript
class Trie {
  constructor() {
    this.root = {};
    this.endOfWord = "$";
  }

  insert(word) {
    let node = this.root;
    for (let ch of word) {
      node[ch] = node[ch] || {};
      node = node[ch];
    }
    node[this.endOfWord] = this.endOfWord;
  }

  search(word) {
    let node = this.root;
    for (let ch of word) {
      if (!node[ch]) return false;
      node = node[ch];
    }
    return node[this.endOfWord] === this.endOfWord;
  }

  startsWith(word) {
    let node = this.root;
    for (let ch of word) {
      if (!node[ch]) return false;
      node = node[ch];
    }
    return true;
  }
}
```

# 并查集代码模版
```python
def init(p): 
	# for i = 0 .. n: p[i] = i; 
	p = [i for i in range(n)] 
 
def union(self, p, i, j): 
	p1 = self.parent(p, i) 
	p2 = self.parent(p, j) 
	p[p1] = p2 
 
def parent(self, p, i): 
	root = i 
	while p[root] != root: 
		root = p[root] 
	while p[i] != i: # 路径压缩 ?
		x = i; i = p[i]; p[x] = root 
	return root
```

```cpp
class UnionFind {
public:
    UnionFind(vector<vector<char>>& grid) {
        count = 0;
        int m = grid.size();
        int n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    parent.push_back(i * n + j);
                    ++count;
                }
                else {
                    parent.push_back(-1);
                }
                rank.push_back(0);
            }
        }
    }

//递归
    int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }


    void unite(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] < rank[rooty]) {
                swap(rootx, rooty);
            }
            parent[rooty] = rootx;
            if (rank[rootx] == rank[rooty]) rank[rootx] += 1;
            --count;
        }
    }


    int getCount() const {
        return count;
    }


private:
    vector<int> parent;
    vector<int> rank;
    int count;
};
```

```Java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```

```javascript
class unionFind {
  constructor(n) {
    this.count = n;
    this.parent = new Array(n);
    for (let i = 0; i < n; i++) {
      this.parent[i] = i;
    }
  }

  find(p) {
    let root = p;
    while (parent[root] !== root) {
      root = parent[root];
    }
    // 压缩路径
    while (parent[p] !== p) {
      let x = p;
      p = this.parent[p];
      this.parent[x] = root;
    }
    return root;
  }

  union(p, q) {
    let rootP = find(p);
    let rootQ = find(q);
    if (rootP === rootQ) return;
    this.parent[rootP] = rootQ;
    this.count--;
  }
}
```

# 双向BFS搜索代码模版
```java
public abstract class DoubleEnd {
    public boolean search(Collection<T> tree, T start, T end) {
        Set<T> allSet = new HashSet<>(tree);
        Set<T> set1 = new HashSet<>(), set2 = new HashSet<>();
        set1.add(start);
        set2.add(end);
        allSet.remove(start);
        allSet.remove(end);
        while (!set1.isEmpty() && !set2.isEmpty()) {
            Set<Node<T>> tmp = null;
            if (set1.size() > set1.size()) {
                tmp = set1;
                set1 = set2;
                set2 = tmp;
            }
            tmp = new HashSet<>();
            for (T t : set1) {
                for (T n : getChild(t)) {
                    if (set2.contains(n)) {
                        return true;
                    } else if (allSet.remove(n)) {
                        tmp.add(n);
                    }
                }
            }
            set1 = tmp;
        }
    }

    public abstract Collection<T> getChild(T t);
}
```

# A*代码模版
```java
public class AStar
{
    public final static int BAR = 1; // 障碍值
    public final static int PATH = 2; // 路径
    public final static int DIRECT_VALUE = 10; // 横竖移动代价
    public final static int OBLIQUE_VALUE = 14; // 斜移动代价
    
    Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
    List<Node> closeList = new ArrayList<Node>();
    
    /**
     * 开始算法
     */
    public void start(MapInfo mapInfo)
    {
        if(mapInfo==null) return;
        // clean
        openList.clear();
        closeList.clear();
        // 开始搜索
        openList.add(mapInfo.start);
        moveNodes(mapInfo);
    }


    /**
     * 移动当前结点
     */
    private void moveNodes(MapInfo mapInfo)
    {
        while (!openList.isEmpty())
        {
            Node current = openList.poll();
            closeList.add(current);
            addNeighborNodeInOpen(mapInfo,current);
            if (isCoordInClose(mapInfo.end.coord))
            {
                drawPath(mapInfo.maps, mapInfo.end);
                break;
            }
        }
    }
    
    /**
     * 在二维数组中绘制路径
     */
    private void drawPath(int[][] maps, Node end)
    {
        if(end==null||maps==null) return;
        System.out.println("总代价：" + end.G);
        while (end != null)
        {
            Coord c = end.coord;
            maps[c.y][c.x] = PATH;
            end = end.parent;
        }
    }


    /**
     * 添加所有邻结点到open表
     */
    private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
    {
        int x = current.coord.x;
        int y = current.coord.y;
        // 左
        addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
        // 上
        addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
        // 右
        addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
        // 下
        addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
        // 左上
        addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
        // 右上
        addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
        // 右下
        addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
        // 左下
        addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
    }


    /**
     * 添加一个邻结点到open表
     */
    private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
    {
        if (canAddNodeToOpen(mapInfo,x, y))
        {
            Node end=mapInfo.end;
            Coord coord = new Coord(x, y);
            int G = current.G + value; // 计算邻结点的G值
            Node child = findNodeInOpen(coord);
            if (child == null)
            {
                int H=calcH(end.coord,coord); // 计算H值
                if(isEndNode(end.coord,coord))
                {
                    child=end;
                    child.parent=current;
                    child.G=G;
                    child.H=H;
                }
                else
                {
                    child = new Node(coord, current, G, H);
                }
                openList.add(child);
            }
            else if (child.G > G)
            {
                child.G = G;
                child.parent = current;
                openList.add(child);
            }
        }
    }


    /**
     * 从Open列表中查找结点
     */
    private Node findNodeInOpen(Coord coord)
    {
        if (coord == null || openList.isEmpty()) return null;
        for (Node node : openList)
        {
            if (node.coord.equals(coord))
            {
                return node;
            }
        }
        return null;
    }




    /**
     * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
     */
    private int calcH(Coord end,Coord coord)
    {
        return Math.abs(end.x - coord.x)
                + Math.abs(end.y - coord.y);
    }
    
    /**
     * 判断结点是否是最终结点
     */
    private boolean isEndNode(Coord end,Coord coord)
    {
        return coord != null && end.equals(coord);
    }


    /**
     * 判断结点能否放入Open列表
     */
    private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
    {
        // 是否在地图中
        if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
        // 判断是否是不可通过的结点
        if (mapInfo.maps[y][x] == BAR) return false;
        // 判断结点是否存在close表
        if (isCoordInClose(x, y)) return false;


        return true;
    }


    /**
     * 判断坐标是否在close表中
     */
    private boolean isCoordInClose(Coord coord)
    {
        return coord!=null&&isCoordInClose(coord.x, coord.y);
    }


    /**
     * 判断坐标是否在close表中
     */
    private boolean isCoordInClose(int x, int y)
    {
        if (closeList.isEmpty()) return false;
        for (Node node : closeList)
        {
            if (node.coord.x == x && node.coord.y == y)
            {
                return true;
            }
        }
        return false;
    }
}
```

```python
def AstarSearch(graph, start, end):
	pq = collections.priority_queue() # 优先级 —> 估价函数
	pq.append([start]) 
	visited.add(start)
	while pq: 
		node = pq.pop() # can we add more intelligence here ?
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
   unvisited = [node for node in nodes if node not in visited]
		pq.push(unvisited)
```

```cpp
class Node {
public:
    int x;
    int y;
    bool operator< (const Node &A) {
        // 
    }
};

void generate_related_nodes(Node &node) {
    // 
}

void process(Node &node) {
    // 
}

void AstarSearch(vector<vector<int>>& graph, Node& start, Node& end) {
    vector<vector<bool> > visited(graph.size(), vector<bool>(graph[0].size(), false));
    priority_queue<Node> q;
    q.push(start);

    while (!q.empty()) {
        Node cur = q.top();
        q.pop();
        visited[cur.x][cur.y] = true;


        process(node);
        vector<Node> nodes = generate_related_nodes(node) 
        for (auto node : nodes) {
            if (!visited[node.x][node.y]) q.push(node);
        }
    }

    return ;
}
```

```javascript
function aStarSearch(graph, start, end) {
  // graph 使用二维数组来存储数据
  let collection = new SortedArray([start], (p1, p2) => distance(p1) - distance(p2));

  while (collection.length) {
    let [x, y] = collection.take();
    if (x === end[0] && y === end[1]) {
      return true;
    }

    insert([x - 1, y]);
    insert([x + 1, y]);
    insert([x, y - 1]);
    insert([x, y + 1]);
  }
  return false;

  function distance([x, y]) {
    return (x - end[0]) ** 2 - (y - end[1]) ** 2;
  }

  function insert([x, y]) {
    if (graph[x][y] !== 0) return;
    if (x < 0 || y < 0 || x >= graph[0].length || y > graph.length) {
      return;
    }
    graph[x][y] = 2;
    collection.insert([x, y]);
  }
}


class SortedArray {
  constructor(data, compare) {
    this.data = data;
    this.compare = compare;
  }

  // 每次取最小值
  take() {
    let min = this.data[0];

    let minIndex = 0;
    for (let i = 1; i < this.data.length; i++) {
      if (this.compare(min, this.data[i]) > 0) {
        min = this.data[i];
        minIndex = i;
      }
    }
    this.data[minIndex] = this.data[this.data.length - 1];
    this.data.push();

    return min;
  }

  insert(value) {
    this.data.push(value);
  }

  get length() {
    return this.data.length;
  }
}
```

# 作业
* [爬楼梯](./P70Solution.java)
* [实现 Trie (前缀树)](./P208Solution.java)
* [朋友圈](./P547Solution.java)
* [岛屿数量](./P200Solution.java)
* [被围绕的区域](./P130Solution.java)
* [有效的数独](./P36Solution.java)
* [括号生成](./P22Solution.java)
* [单词接龙](./P127Solution.java)
* [最小基因变化](./P433Solution.java)
* [单词搜索 II](./P212Solution.java)
* [N 皇后](./P51Solution.java)
* [解数独](./P37Solution.java)
# 递归
**基本上，所有的递归问题都可以用递推公式来表示。**
### 递归需要满足的三个条件
* 一个问题的解可以分解为几个子问题的解
* 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
* 存在递归终止条件

### 递归最关键的点
* 写出递推公式
* 找到终止条件
*************************
**写递归代码的关键就是找到如何将大问题分解为小问题的规律，并且基于此写出递推公式，然后再推敲终止条件，最后将递推公式和终止条件翻译成代码。**
**编写递归代码的关键是，只要遇到递归，我们就把它抽象成一个递推公式，不用想一层层的调用关系，不要试图用人脑去分解递归的每个步骤。**

### 递归代码模版
```java
public void recur(int level, int param) { 

  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }

  // process current logic 
  process(level, param); 

  // drill down 
  level = level + 1;
  recur(level, newParam); 

  // restore current status if needed
 level = level - 1;
}
```
```cpp
// C/C++
void recursion(int level, int param) { 
  // recursion terminator
  if (level > MAX_LEVEL) { 
  // process result 
    return ; 
  }

  // process current logic 
  process(level, param);

  // drill down 
  level = level + 1;
  recursion(level, param);

  // reverse the current level status if needed
  level = level - 1;
}
```
```javascript
// JavaScript
const recursion = (level, params) =>{
    // recursion terminator
    if(level > MAX_LEVEL){
    process_result
    return
    }
    // process current level
    process(level, params)
    //drill down
    level = level + 1;
    recursion(level, params)
    //clean current level status if needed
    level = level - 1;
}
```
```python
# Python
def recursion(level, param1, param2): 
    # recursion terminator 
    if level > MAX_LEVEL: 
        process_result 
        return 

    # process logic in current level 
    process(level, data...) 

    # drill down 
    level = level + 1
    self.recursion(level, param1, param2) 

    # reverse the current level status if needed
    level = level - 1
```

### 递归代码要警惕重复计算
为了避免重复计算，可以通过一个数据结构（比如散列表）来保存已经求解过的子问题答案。

# 分治
分治法是一种很重要的算法。字面上的解释是“分而治之”，就是把一个复杂的问题分成两个或更多的相同或相似的子问题，再把子问题分成更小的子问题……直到最后子问题可以简单的直接求解，原问题的解即子问题的解的合并。
##### 分治法所能解决的问题一般具有以下几个特征：
* 该问题的规模缩小到一定的程度就可以容易地解决
* 该问题可以分解为若干个规模较小的相同问题，即该问题具有最优子结构性质。
* 利用该问题分解出的子问题的解可以合并为该问题的解；
* 该问题所分解出的各个子问题是相互独立的，即子问题之间不包含公共的子子问题。

### 代码模版
```java
private static int divide_conquer(Problem problem) {
    if (problem == NULL) {
        int res = process_last_result();
        return res;
    }

    subProblems = split_problem(problem)

    res0 = divide_conquer(subProblems[0])
    res1 = divide_conquer(subProblems[1])

    result = process_result(res0, res1);

    return result;
}
```
```cpp
int divide_conquer(Problem *problem, int params) {
  // recursion terminator
  if (problem == nullptr) {
    process_result
    return return_result;
  } 

  // process current problem
  subproblems = split_problem(problem, data)
  subresult1 = divide_conquer(subproblem[0], p1)
  subresult2 = divide_conquer(subproblem[1], p1)
  subresult3 = divide_conquer(subproblem[2], p1)

  // merge
  result = process_result(subresult1, subresult2, subresult3)
  // revert the current level status
 
  return 0;
}
```
```python

# Python
def divide_conquer(problem, param1, param2, ...): 
    # recursion terminator 
    if problem is None: 
        print_result 
        return 

    # prepare data 
    data = prepare_data(problem) 
    subproblems = split_problem(problem, data) 

    # conquer subproblems 
    subresult1 = self.divide_conquer(subproblems[0], p1) 
    subresult2 = self.divide_conquer(subproblems[1], p1) 
    subresult3 = self.divide_conquer(subproblems[2], p1) 

    # process and generate the final result 
    result = process_result(subresult1, subresult2, subresult3, …)

    # revert the current level states
```
```javascript
//Javascript
const divide_conquer = (problem, params) => {
    // recursion terminator
    if (problem == null) {
        process_result
        return
    }
    // process current problem
    subproblems = split_problem(problem, data)
    subresult1 = divide_conquer(subproblem[0], p1)
    subresult2 = divide_conquer(subproblem[1], p1)
    subresult3 = divide_conquer(subproblem[2], p1)
  
    // merge
    result = process_result(subresult1, subresult2, subresult3)
    // revert the current level status
}
```

# 回溯
回溯法采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。

*回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：*
* *找到一个可能存在的正确的答案*
* *在尝试了所有可能的分步方法后宣告该问题没有答案。*
在最坏的情况下，回溯法会导致一次复杂度为指数时间的计算。

# 作业
* [二叉树的最近公共祖先](./P236Solution.java)
* [从前序与中序遍历序列构造二叉树](./P105Solution.java)
* [组合](./P77Solution.java)
* [全排列](./P46Solution.java)
* [全排列 II](./P47Solution.java)
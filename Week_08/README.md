# 位运算
## 常用的高级位运算
* x ^ 0 = x
* x ^ 1s = ~x // 注意 1s = ~0
* x ^ (~x) = 1s
* x ^ x = 0
* c = a ^ b => a ^ c = b, b ^ c = a // 交换两个数
* a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c // associative 
* 将 x 最右边的 n 位清零：x & (~0 << n)
* 获取 x 的第 n 位值（0 或者 1）： (x >> n) & 1
* 获取 x 的第 n 位的幂值：x & (1 << n)
* 仅将第 n 位置为 1：x | (1 << n)
* 仅将第 n 位置为 0：x & (~ (1 << n))
* 将 x 最高位至第 n 位（含）清零：x & ((1 << n) - 1)

* 判断奇偶：
    * x % 2 == 1 —> (x & 1) == 1
    * x % 2 == 0 —> (x & 1) == 0
* x >> 1 —> x / 2 *即： x = x / 2; —> x = x >> 1; mid = (left + right) / 2; —> mid = (left + right) >> 1;*
* X = X & (X-1) 清零最低位的 1
* X & -X => 得到最低位的 1
* X & ~X => 0

# 布隆过滤器
一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。
可以快速判断一个元素不在集合中
* 优点：空间效率和查询时间都远远超过一般的数据结构
* 缺点：有一定的误识别率和删除困难 

# 排序
## 插入排序
```java
public void insertSort(int[] nums) {
    for (int i = 1;i < nums.length;i++) {
        int val = nums[i], j = i;
        while (j > 0 && nums[j - 1] > val) {
            nums[j] = nums[--j];
        }
        nums[j] = val;
    }
}
```

## 归并排序
```java
public void mergeSort(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
}

private void mergeSort(int[] nums, int left, int right) {
    if (left >= right) {
        return;
    }
    int mid = left + ((right - left) >> 1);
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    int[] cache = new int[right - left + 1];
    int r = mid + 1, index = 0;
    for (int i = left;i <= mid;i++) {
        while (r <= right && nums[r] < nums[i]) cache[index++] = nums[r++];
        cache[index++] = nums[i];
    }
    System.arraycopy(cache, 0, nums, left, index);
}
```

## 选择排序
```java
public void selectSort(int[] nums) {
    for (int i = 0;i < nums.length;i++) {
        for (int j = i + 1;j < nums.length;j++) {
            if (nums[i] > nums[j]) {
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
        }   
    }
}
```

## 快速排序
```java
public void quickSort(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
}

private void quickSort(int[] nums, int left, int right) {
    int l = left, r = right, x = nums[l];
    while (l < r) {
        while (l < r && nums[r] >= x) r--;
        if (l < r) {
            nums[l++] = nums[r];
        }   
        while (l < r && nums[l] <= x) l++;
        if (l < r) {
            nums[r--] = nums[l];
        }
    }
    nums[l] = x;
    quickSort(nums, left, l - 1);
    quickSort(nums, l + 1, right);
}
```

## 冒泡排序
```java
public void BubbleSort(int[] nums) {
    for (int i = 0;i < nums.length;i++) {
        boolean flag = true;
        for (int j = 1;j < nums.length - i;j++) {
            if (nums[j] > nums[j - 1]) {
                nums[j - 1] ^= nums[j];
                nums[j] ^= nums[j - 1];
                nums[j - 1] ^= nums[j];
                flag = false;
            }
        }
        if (flag) break;
    }
}
```

# 堆排序
```java
public void heapifySort(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        heapify(nums, i);
    }
    for (int i = nums.length - 1; i > 0; i--) {
        heapify(nums, 0, i);
    }
}

private void heapify(int[] nums, int i) {
    int parent = (i - 1) >> 1;
    while (parent >= 0 && nums[parent] < nums[i]) {
        nums[parent] ^= nums[i];
        nums[i] ^= nums[parent];
        nums[parent] ^= nums[i];
        i = parent;
        parent = (i - 1) >> 1;
    }
}

private void heapify(int[] nums, int i, int len) {
    int val = nums[len];
    nums[len] = nums[i];
    nums[i] = val;
    int left = (i << 1) + 1, right = left + 1;
    while (left < len && nums[i] <= nums[left] || right < len && nums[i] <= nums[right]) {
        if (right >= len || nums[left] >= nums[right]) {
            nums[left] += nums[i];
            nums[i] = nums[left] - nums[i];
            nums[left] -= nums[i];
            i = left;
        } else {
            nums[right] += nums[i];
            nums[i] = nums[right] - nums[i];
            nums[right] -= nums[i];
            i = right;
        }
        left = (i << 1) + 1;
        right = left + 1;
    }
}
```

## 常见排序比较
|排序方法|时间复杂度(平均)|时间复杂度(最坏)|时间复杂度(最好)|空间复杂度|稳定性|
|:---:|:---:|:---:|:---:|:---:|:---:|
|插入排序|O(n²)|O(n²)|O(n)|O(1)|稳定|
|希尔排序|O(n^(1.3))|O(n²)|O(n)|O(1)|不稳定|
|选择排序|O(n²)|O(n²)|O(n²)|O(1)|不稳定|
|堆排序|O(nlogn)|O(nlogn)|O(nlogn)|O(1)|不稳定|
|冒泡排序|O(n²)|O(n²)|O(n)|O(1)|稳定|
|快速排序|O(nlogn)|O(n²)|O(nlogn)|O(nlogn)|不稳定|
|归并排序|O(nlogn)|O(nlogn)|O(nlogn)|O(n)|稳定|
|计数排序|O(n+k)|O(n+k)|O(n+k)|O(n+k)|稳定|
|桶排序|O(n+k)|O(n²)|O(n)|O(n+k)|稳定|
|基数排序|O(n*k)|O(n*k)|O(n*k)|O(n+k)|稳定|

# 作业
* [位 1 的个数](./P191Solution.java)
* [2 的幂](./P231Solution.java)
* [颠倒二进制位](./P190Solution.java)
* [数组的相对排序](./P1122Solution.java)
* [有效的字母异位词](./P242Solution.java)
* [LRU 缓存机制](./P146Solution.java)
* [合并区间](./P56Solution.java)
* [N 皇后](./P51Solution.java)
* [N 皇后 II](./P52Solution.java)
* [翻转对](./P493Solution.java)
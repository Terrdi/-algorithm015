## 快速排序
```java
public void quickSort(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
}

private void quickSort(int[] nums, int left, int right) {
    int l = left, r = right, x = nums[l];
    while (l < r) {
        while (l < r && s[r] >= x) r--;
        if (l < r) {
            s[l] = s[r];
            l++;
        }   
        while (l < r && s[l] <= x) l++;
        if (l < r) {
            s[r] = s[l];
            r--;
        }
    }
    s[l] = x;
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
                nums[j] += nums[j - 1];
                nums[j - 1] = nums[j] - nums[j - 1];
                nums[j] = nums[j] - nums[j - 1];
                flag = false;
            }
        }
        if (flag) break;
    }
}
```

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
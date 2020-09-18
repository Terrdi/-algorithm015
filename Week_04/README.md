学习笔记

```java
class BinarySearch {
    public int findStartIndex(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] <= nums[high]) {
                high = mid - 1;
            } else  {
                low = mid + 1;
            }
        }

        if (low < nums.length - 1 && nums[low] > nums[low + 1])
            low++;

        return low;
    }
}
```


# 作业
* [柠檬水找零](./P860Solution.java)
* [买卖股票的最佳时机](./P122Solution.java)
* [分发饼干](./P455Solution.java)
* [模拟行走机器人](./P874Solution.java)
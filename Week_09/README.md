```math
dp[0][0] = 1;
dp[i][j] = obstacleGrid[i][j] > 0 ? 0 : (i > 0 ? dp[i - 1][j] : 0) + (j > 0 ? 0 : dp[i][j - 1]);
```

**字符串相关算法和数组有点类似**

# 作业
* [字符串中的第一个唯一字符](./P387Solution.java)
* [反转字符串 II](./P541Solution.java)
* [翻转字符串里的单词](./P151Solution.java)
* [反转字符串中的单词 III](./P557Solution.java)
* [仅仅反转字母](./P917Solution.java)
* [同构字符串](./P205Solution.java)
* [验证回文字符串 Ⅱ](./P680Solution.java)
* [最长上升子序列](./P300Solution.java)
* [解码方法](./P91Solution.java)
* [字符串转换整数 (atoi)](./P8Solution.java)
* [最长有效括号](./P32Solution.java)
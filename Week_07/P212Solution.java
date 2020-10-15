class Trie {
    String word;
    List<Trie> childs;

    public Trie() {
        this.word = null;
        this.childs = new ArrayList<>();
    }

    public Trie(String... words) {
        this();
        this.insert(words);
    }

    public void insert(CharSequence... strs) {
        final Trie root = this;
        for (CharSequence str : strs) {
            Trie tmp = root;
            for (int i = 0;i < str.length();i++) {
                char ch = str.charAt(i);
                while (tmp.childs.size() <= ch) tmp.childs.add(new Trie());
                tmp = tmp.childs.get(ch);
            }
            tmp.word = str.toString();
        }
    }
}

class Solution {
    /**
     * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
     *
     * 时间复杂度 O(average(len(word)) * mn)
     * 空间复杂度 O(words.length * average(len(word)))
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        List<String> list = new ArrayList<>(words.length);
        trie.insert(words);
        for (int i = 0;i < board.length;i++) {
            for (int j = 0;j < board[i].length;j++) {
                _dfs(trie, board, i, j, list);
            }
        }
        return list;
    }

    private void _dfs(Trie trie, char[][] board, int i, int j, List<String> result) {
        _dfs(trie, board, i, j, result, new HashSet<>());
    }

    private void _dfs(Trie trie, char[][] board, final int i, final int j, List<String> result, Set<Pair<Integer, Integer>> visited) {
        final Pair<Integer, Integer> pair = new Pair<>(i, j);
        if (i >= 0 && j >= 0 && i < board.length && j < board[i].length
                && trie.childs.size() > board[i][j] && visited.add(pair) ) {
            trie = trie.childs.get(board[i][j]);
            if (trie.word != null) {
                result.add(trie.word);
                trie.word = null;
            }

            _dfs(trie, board, i - 1, j, result, visited);
            _dfs(trie, board, i + 1, j, result, visited);
            _dfs(trie, board, i, j - 1, result, visited);
            _dfs(trie, board, i, j + 1, result, visited);
            visited.remove(pair);
        }

    }
}
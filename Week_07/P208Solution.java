class Trie {
    /**
     * store words
     */
    private Map<Character, Trie> trie = null;

    /**
     * 当前节点是否可以结束
     */
    private boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        trie = new ConcurrentHashMap<>();
    }

    /** Inserts a word into the trie. */
    public void insert(CharSequence word) {
        Trie cur = this;
        for (int i = 0;i < word.length();i++) {
            char ch = word.charAt(i);
            cur = cur.putNext(ch);
        }
        cur.end();
    }

    /** Returns if the word is in the trie. */
    public boolean search(CharSequence word) {
        try {
            Trie cur = this;
            for (int i = 0;i < word.length();i++) {
                cur = cur.next(word.charAt(i));
            }
            return cur.isEnd();
        } catch (NullPointerException e) {
            return false;
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(CharSequence prefix) {
        try {
            Trie cur = this;
            for (int i = 0;i < prefix.length();i++) {
                cur = cur.next(prefix.charAt(i));
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * set the trie end
     */
    private void end() {
        this.isEnd = true;
    }

    /**
     * @return true if the prefix word exists
     */
    private boolean isEnd() {
        return this.isEnd;
    }

    private Trie putNext(Character ch) {
        trie.putIfAbsent(ch, new Trie());
        return trie.get(ch);
    }

    private Trie next(Character ch) {
        return java.util.Optional.of(trie.get(ch)).get();
    }
}
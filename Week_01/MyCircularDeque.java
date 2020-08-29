class MyCircularDeque {
    private int[] values = null;
    private int rear = -1;
    private int front = -1;
    private final int k;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.k = k;
        this.values = new int[k + 1];
        this.values[this.k] = this.front = this.rear = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (this.isFull()) {
            return false;
        }
        this.front = (this.front - 1 + k) % k;
        this.values[this.front] = value;
        this.values[k]++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (this.isFull()) {
            return false;
        }
        this.values[this.rear++] = value;
        this.rear %= this.k;
        this.values[k]++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (this.isEmpty()) {
            return false;
        }
        this.front = (this.front + 1) % this.k;
        this.values[k]--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (this.isEmpty()) {
            return false;
        }
        this.rear = (this.rear - 1 + k) % this.k;
        this.values[k]--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.values[this.front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.values[(this.rear - 1 + k) % this.k];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return this.values[k] == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return this.values[k] == k;
    }
}
class Solution {
    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) return l2;
        if (Objects.isNull(l2)) return l1;

        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }

        ListNode head = null;
        ListNode cur = null;
        if (l1.val > l2.val) {
            cur = head = l2;
            l2 = l2.next;
        } else {
            cur = head = l1;
            l1 = l1.next;
        }
        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            if (l1.val > l2.val) {
                cur = cur.next = l2;
                l2 = l2.next;
            } else {
                cur = cur.next = l1;
                l1 = l1.next;
            }
        }
        cur.next = Objects.isNull(l1) ? l2 : l1;

        return head;
    }
}
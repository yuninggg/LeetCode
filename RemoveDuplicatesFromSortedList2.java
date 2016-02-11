/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of the linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            boolean duplicated = false;
            while (head.next != null && head.val == head.next.val) {
                duplicated = true;
                head = head.next;
            }
            if (!duplicated) {
                pre.next = head;
                pre = pre.next;
            }
            head = head.next;
        }
        pre.next = head;
        return dummy.next;
    }
}

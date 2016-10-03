


public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null ) {
            return l2;
        } else if (l2 == null ) {
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode curr = result;
        int carry = 0;
        while(l1 != null && l2 != null) {
            curr.next = new ListNode ( (l1.val + l2.val + carry) % 10 );
            curr = curr.next;
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
        }
        while (carry != 0 && curr.next != null) {
            curr = curr.next;
            int sum = curr.val + carry;
            carry = (sum) / 10;
            curr.val = sum % 10;
            
            // 要注意上下文之间的变量是不是被之前的语句改变了!!!!!非常重要啊
            
        }
        if (carry != 0) {
            curr.next = new ListNode (carry);
        }
        return result.next;
    }
}
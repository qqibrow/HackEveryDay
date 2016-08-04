package algorithm;

/**
 * Created by lniu on 5/8/16.
 */
public class InsertionSortedList {
        public ListNode insertionSortList(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }

            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode curr = head.next;
            head.next = null;
            while(curr != null) {
                ListNode prev = dummy;
                ListNode n = head;
                while (n != curr && n.val < curr.val) {
                    prev = n;
                    n = n.next;
                }
                if(n == curr) {
                    ListNode temp = curr.next;
                    curr.next = null;
                    curr = temp;
                } else {
                    // insert curr between prev and n
                    ListNode next = curr.next;
                    prev.next = curr;
                    curr.next = n;
                    curr = next;
                }

            }
            return dummy.next;
        }

    public static void main(String[] args) {
        new InsertionSortedList().insertionSortList(new ListNode(5, new ListNode(1)));
    }

}

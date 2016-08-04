package algorithm;

import java.util.HashSet;

/**
 * Created by lniu on 3/28/16.
 */
public class LinkListPractice {
    class LinkListNode {
        LinkListNode next = null;
        int data;
        
    }

    void removeDuplicates(LinkListNode n) {
        HashSet<Integer> hashSet = new HashSet<>();
        LinkListNode prev = null;
        while (n != null) {
            if(hashSet.contains(n.data)) {
                prev.next = n.next;
            } else {
                hashSet.add(n.data);
                prev = n;
            }
            n = n.next;
        }
    }

    public LinkListNode findNthToLastElement(LinkListNode head, int n) {
        LinkListNode prev = head;
        for(int i = 0; i < n; ++i) {
            if(head == null) {
                return null;
            }
            head = head.next;
        }
        while(head != null) {
            head = head.next;
            prev = prev.next;
        }
        return prev;
    }

    public LinkListNode findLoop(LinkListNode head) {
        return null;

    }

    public class Node<T> {
        T val;
        Node next = null;
        Node(T s) {
            val = s;
        }

        Node(T s, Node n) {
            val = s;
            next = n;
        }
    }

    public class Stack<T> {
        Node head;
        void push(T val) {
            Node<T> newHead = new Node(val);
            if(head == null) {
                head = newHead;
            } else {
                newHead.next = head;
                head = newHead;
            }
        }

        void pop() {
            if(head != null) {
                head = head.next;
            }
        }

        T top() {
            return null;
        }

    }
}

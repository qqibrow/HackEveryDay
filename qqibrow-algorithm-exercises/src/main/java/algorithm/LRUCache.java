package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by lniu on 5/28/16.
 */

public class LRUCache {

    public static class KeyValue {
        int key;
        int value;
        public KeyValue(int k, int v) {
            key = k;
            value = v;
        }
    }

    public static class ListNode {
        ListNode prev;
        ListNode next;
        KeyValue keyvalue;

        public ListNode(KeyValue kv) {
            keyvalue = kv;
        }

    }

    public static class DoubleLinkedList {
        ListNode dummyHead = new ListNode(new KeyValue(Integer.MAX_VALUE, Integer.MAX_VALUE));
        ListNode dummyTail = new ListNode(new KeyValue(Integer.MIN_VALUE, Integer.MIN_VALUE));
        int count = 0;

        public DoubleLinkedList() {
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }
        public void addToHead(ListNode node) {
            if(node != dummyHead.next) {
                ListNode prevHead = dummyHead.next;
                prevHead.prev = node;

                dummyHead.next = node;
                node.prev = dummyHead;

                node.next = prevHead;
                count++;
            }

        }

        public synchronized void remove(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
            count--;
        }

        public synchronized  void moveToHead(ListNode node) {
            remove(node);
            addToHead(node);
        }

        public ListNode tail() {
            return dummyTail.prev;
        }
    }

    int cap;
    DoubleLinkedList l;
    Map<Integer, ListNode> keyToNode;

    public LRUCache(int capacity) {
        cap = capacity;
        l = new DoubleLinkedList();
        keyToNode = new HashMap<>();
    }

    public int get(int key) {
        if(keyToNode.containsKey(key)) {
            ListNode node = keyToNode.get(key);
            int value = node.keyvalue.value;
            l.moveToHead(node);
            return value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if(keyToNode.containsKey(key)) {
            ListNode node = keyToNode.get(key);
            node.keyvalue.value = value;
            l.moveToHead(node);
        } else {
            ListNode newNode = new ListNode(new KeyValue(key, value));
            keyToNode.put(key, newNode);
            l.addToHead(newNode);

            if(keyToNode.size() > cap) {
                ListNode tail = l.tail();
                keyToNode.remove(tail.keyvalue.key);
                l.remove(tail);
            }
        }
    }

    public static void main(String[] args) {
        /*
        LRUCache cache = new LRUCache(1);
        cache.set(2, 1);
        cache.get(2);
        cache.set(3, 2);
        cache.get(2);
        */
        Collections.max(Arrays.asList());
        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(30);
        l.add(200);
        ListIterator<Integer> it = l.listIterator();
        it.next();
        it.add(10);
        l.stream().forEach(System.out::println);

    }
}

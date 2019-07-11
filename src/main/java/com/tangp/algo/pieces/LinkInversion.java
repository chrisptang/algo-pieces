package com.tangp.algo.pieces;

/**
 * 单向链表的反转；
 * 时间复杂度O(n), 空间复杂度O(1);
 *
 * @author tangp
 */
public class LinkInversion {
    static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            if (null != next) {
                return data + " -> " + next.toString();
            }
            return data + " -> null;";
        }
    }

    public static <T> Node<T> inverseLink(Node<T> link) {
        if (link == null || link.next == null) {
            return link;
        }

        Node<T> head = link;
        while (link.next != null) {
            Node<T> n = link.next;
            link.next = n.next;
            n.next = head;
            head = n;
        }
        return head;
    }

    protected static <T> Node<T> reverseLink(Node<T> head) {
        if (null == head || null == head.next) {
            return head;
        }
        Node temp = head.next;
        Node result = reverseLink(temp);
        temp.next = head;
        head.next = null;
        return result;
    }

    public static void main(String[] args) {
        Node head = new Node(5), t = head;
        int i = 5;
        while (i-- > 0) {
            t.next = new Node(i);
            t = t.next;
        }

        System.out.println("Original link:" + head.toString());
        head = reverseLink(head);
        System.out.println("Reversed link:" + head.toString());
    }
}

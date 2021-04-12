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

    /**
     * 循环法
     *
     * @param link
     * @param <T>
     * @return
     */
    public static <T> Node<T> reverseLink(Node<T> link) {
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


    /**
     * 递归法；
     *
     * @param link
     * @param <T>
     * @return
     */
    public static <T> Node<T> reverseLink2(Node<T> link) {
        if (link == null || link.next == null) {
            return link;
        }

        Node<T> head = link;
        Node<T> rest = reverseLink2(head.next);
        head.next.next = head;

        /* tricky step -- see the diagram */
        head.next = null;

        /* fix the head pointer */
        return rest;
    }
}

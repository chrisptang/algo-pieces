package com.tangp.algo.pieces;

import java.util.ArrayList;

/**
 * 单向链表的反转；
 * 时间复杂度O(n), 空间复杂度O(1);
 *
 * @author tangp
 */
public class LinkInversion {
    static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
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

    public static void main(String[] args) {
        Node<Integer> link = new Node<>(1);
        link.next = new Node<>(2);
        link.next.next = new Node<>(3);
        link.next.next.next = new Node<>(4);
        link.next.next.next.next = new Node<>(5);

        ArrayList<String> linkData = new ArrayList<>();
        link = inverseLink(link);
        while (link != null) {
            linkData.add(String.valueOf(link.data));
            link = link.next;
        }
        System.out.println(String.join(" -> ", linkData));
    }
}

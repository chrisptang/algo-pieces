package com.tangp.algo.pieces;

/**
 * 判断一个单向链表是否有环；
 */
public class IsLinkCircled {
    public static void main(String[] args) {
        // 创建一个环：1>2>3>4>5>3;
        Node<Integer> head = new Node<>(1);
        Node<Integer> nodeToConnect = null;
        Node<Integer> previours = head;
        for (int index = 2; index < 5; index++) {
            Node<Integer> cur = new Node<>(index);
            previours.next = cur;
            if (index == 3) {
                nodeToConnect = cur;
            }
            previours = cur;
        }
        previours.next = nodeToConnect;

        System.out.println(isLinkCircled(head));
    }

    protected static boolean isLinkCircled(Node<?> head) {
        if (null == head || head.next == null) {
            return false;
        }
        Node<?> firstPointer = head, secondaryPointer = head;
        while (firstPointer != null && secondaryPointer != null && secondaryPointer.next != null) {
            firstPointer = firstPointer.next;
            secondaryPointer = secondaryPointer.next.next;
            if (firstPointer == secondaryPointer) {
                System.out.println(firstPointer.data);
                return true;
            }
        }
        return false;
    }

    protected static class Node<T> {
        protected T data;

        protected Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}

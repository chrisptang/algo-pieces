package com.tangp.algo.pieces;

import com.tangp.algo.pieces.entities.Node;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        Node<Integer> link = new Node<>(1, new Node(2, new Node(2, new Node(1))));
        System.out.println(link);
        System.out.println(isPalindrome(link));

        link = new Node<>(1, new Node(2));
        System.out.println(link);
        System.out.println(isPalindrome(link));

        link = new Node<>(1, new Node(2, new Node(1)));
        System.out.println(link);
        System.out.println(isPalindrome(link));

        link = new Node<>(1, new Node(2, new Node(3, new Node(2, new Node(1)))));
        System.out.println(link);
        System.out.println(isPalindrome(link));

        link = new Node<>(1, new Node(2, new Node(3, new Node(3, new Node(2, new Node(1))))));
        System.out.println(link);
        System.out.println(isPalindrome(link));
    }

    /**
     * 先将单向链表转成两个子链表，将其中一个子链表反转；
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static boolean isPalindrome(Node head) {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }//得到长度；

        //反转前1/2个子链表；
        Node link1 = head, link2 = null;
        for (int i = 0; i < length / 2; i++) {
            Node t = link1.next;
            if (i == (length / 2 - 1)) {
                if (length % 2 == 1) {
                    if (t.next.next == null) {
                        link1.next = null;
                        link2 = t.next;
                    } else {
                        link2 = t.next.next;
                    }
                } else {
                    link2 = t.next;
                }
                t.next = null;
                break;
            }
            link1.next = t.next;
            t.next = head;
            link1 = t;
        }
        while (link1 != null || link2 != null) {
            if (link1 != null && link2 != null && link1.data != link2.data) {
                return false;
            }
            if (link1 != null && link2 != null) {
                link1 = link1.next;
                link2 = link2.next;
                continue;
            } else if (link1 != null || link2 != null) {
                return false;
            }
        }

        return true;
    }
}

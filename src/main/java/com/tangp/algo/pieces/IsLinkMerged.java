package com.tangp.algo.pieces;

import com.tangp.algo.pieces.entities.Node;

/**
 * 判断两个单向链表是否相交，同时找出交点。
 * 有多种方式：
 * 1、暴力法：O(n*n)，两层循环的遍历两个链表；
 * 2、空间法：O(n)额外空间，使用Set来存储遍历过两条链表的节点，判断是否重复；
 * 3、差值法：先定位两条链表的长度，然后以短的一条长度为起始位置开始遍历；无额外空间，时间复杂度O(n)；
 * <p>
 * 本类使用第三种方法；
 */
public class IsLinkMerged {
    public static void main(String[] args) {
        // 先构建两条相交的链表，和一条不相交链表；
        // 1-2-3-4-5；
        // 6-7-4-5；
        // 8-9-10；
        Node<Integer> link1 = new Node<>(1), t = link1, mergeNode = null;
        for (int i = 2; i <= 5; i++) {
            t.next = new Node<>(i);
            t = t.next;
            if (t.data == 3) {
                mergeNode = t;
            }
        }
        Node<Integer> link2 = new Node<>(6);
        link2.next = new Node<>(7);
        link2.next.next = mergeNode;

        Node<Integer> link3 = new Node<>(8), t2 = link3;
        for (int i = 9; i <= 10; i++) {
            t2.next = new Node<>(i);
            t2 = t2.next;
        }
        System.out.println(isLinksMerged(link1, link2));
        System.out.println(isLinksMerged(link1, link3));
        System.out.println(isLinksMerged(link2, link3));
    }


    /**
     * 如果有返回，则说明两个link有交点；
     *
     * @param link1
     * @param link2
     * @return
     */
    public static Node isLinksMerged(Node link1, Node link2) {
        if (link1 == null || link2 == null) {
            return null;
        }
        int length1 = 0, length2 = 0;
        Node t1 = link1, t2 = link2;
        while (t1 != null || t2 != null) {
            if (t1 != null) {
                length1++;
                t1 = t1.next;
            }
            if (t2 != null) {
                length2++;
                t2 = t2.next;
            }
        }
        Node longer = length1 >= length2 ? link1 : link2, shorter = length1 < length2 ? link1 : link2;
        for (int i = 0; i < Math.abs(length1 - length2); i++) {
            longer = longer.next;
        }
        while (longer != null && shorter != null) {
            if (longer == shorter) {
                return longer;
            } else {
                longer = longer.next;
                shorter = shorter.next;
            }
        }
        return null;
    }
}

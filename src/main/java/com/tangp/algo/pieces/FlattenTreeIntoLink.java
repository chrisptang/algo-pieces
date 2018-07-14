package com.tangp.algo.pieces;

public class FlattenTreeIntoLink {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void flatten(TreeNode root) {
        if (root.right != null && (root.right.right != null || root.right.left != null)) {
            flatten(root.right);
        }

        if (root.left != null && (root.left.left != null || root.left.right != null)) {
            flatten(root.left);
        }

        TreeNode n = root.left;
        while (n != null && n.right != null) {
            n = n.right;
        }
        if (n != null) {
            n.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1), two = new TreeNode(2), three = new TreeNode(3),
                four = new TreeNode(4), five = new TreeNode(5), six = new TreeNode(6);
        one.left = two;
        one.right = five;
        two.left = three;
        two.right = four;
        five.right = six;

        flatten(one);
        TreeNode n = one;
        while (n != null) {
            System.out.print(n.val);
            System.out.print(",");
            System.out.print(n.left);
            System.out.print(",");
            n = n.right;
        }
    }
}

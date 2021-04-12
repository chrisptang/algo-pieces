package com.tangp.algo.pieces.entities;

public class Node<T> {
    public T data;

    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "(" + data + ") -> " + next;
    }
}

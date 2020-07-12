package com.tangp.algo.pieces.entities;

public class Node<T> {
    public T data;

    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{data:" + data + ", next:" + next + "}";
    }
}

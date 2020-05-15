package com.tangp.algo.pieces;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class GcAndReferences {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<int[]> referenceQueue = new ReferenceQueue<>();

        Reference reference = new VeryLargeObject(referenceQueue);
        while (!reference.isEnqueued()) {
            System.out.println("continuously creating dummy object...");
            new VeryLargeObject(referenceQueue);
        }
    }

    protected static class VeryLargeObject extends PhantomReference<int[]> {

        public VeryLargeObject(ReferenceQueue<? super int[]> q) {
            this(50, q);
        }

        public VeryLargeObject(int kb, ReferenceQueue<? super int[]> q) {
            // 一个int 占有4 byte；
            super(new int[kb * 1024 / 4], q);
        }
    }
}

package com.tangp.algo.pieces;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteBufferTest {

    public static void main(String[] args) {
        String text = "你好😊";
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(40);
        byteBuffer.put(text.getBytes());
        System.out.println(byteBuffer.toString());
        byte[] buff = new byte[byteBuffer.position()];
        byteBuffer.rewind();
        byteBuffer.get(buff);
        System.out.println(new String(buff));
        System.out.println(Arrays.toString(text.getBytes()));
    }
}

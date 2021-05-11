package com.ethen.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class HelloFileChannel02 {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\helloFileChannel.txt");
        FileInputStream input = new FileInputStream(file);
        FileChannel channel = input.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        channel.read(buffer);
        System.err.println(new String(buffer.array()));
    }
}

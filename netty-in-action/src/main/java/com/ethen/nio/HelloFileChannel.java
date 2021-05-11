package com.ethen.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class HelloFileChannel {
    public static void main(String[] args) throws IOException {
        String str = "hello1234455,台积电。";
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\helloFileChannel.txt");
        // 输出流 -> channel
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 创建缓冲区 byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将string放入缓冲区
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        // 对缓冲区进行flip
        byteBuffer.flip();
        // 将Buffer数据写入到channel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}

package com.ethen.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// 使用一个Buffer拷贝文件
public class HelloFileChannel03 {
    public static void main(String[] args) throws IOException {

        File file = new File("d:\\helloFileChannel.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel inputChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        inputChannel.read(byteBuffer);
        // 写文件
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\helloFileChannel02.txt");
        // 切换读模式
        byteBuffer.flip();
        // fixme 注意持续读写时 clear
        // fixme byteBuffer.clear();
        FileChannel outputChannel = fileOutputStream.getChannel();
        outputChannel.write(byteBuffer);
        fileInputStream.close();
        fileOutputStream.close();
    }
}

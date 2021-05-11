package com.ethen.nio;

import java.nio.IntBuffer;

public class HelloBuffer {
    public static void main(String[] args) {
        // 初始化Buffer
        IntBuffer intBuffer = IntBuffer.allocate(6);

        // 往Buffer写入
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 3);
        }

        // 切换
        intBuffer.flip();
        // 读取
        for (int j = 0; j < intBuffer.capacity(); j++) {
            System.err.println(intBuffer.get());
        }
    }
}

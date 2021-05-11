package com.ethen.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) throws IOException {
        // 创建一个线程池
        // 如果有客户端连接 就创建一个线程 与之通信
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.err.println("服务器启动了...");
        while (true) {
            printCurrentThread();
            System.err.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.err.println("连接到一个客户端！！");
            pool.execute(() -> {
                // fixme 和客户端通信业务
                handleRequest(socket);

            });
        }
    }

    // 处理业务逻辑
    private static void handleRequest(Socket socket) {
        System.err.println("开始执行业务逻辑...");
        printCurrentThread();
        try (InputStream input = socket.getInputStream()) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = input.read(buf, 0, buf.length)) > 0) {
                System.err.print("接收到客户端消息：" + new String(buf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printCurrentThread() {
        System.err.println("线程Id: " + Thread.currentThread().getId() + ", 线程name: " + Thread.currentThread().getName());
    }
}

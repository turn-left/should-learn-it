package com.ethen.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO处理TCP请求
 */
public class HelloNIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 创建selector对象
        Selector selector = Selector.open();
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 把serverSocketChannel注册到selector,关注OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) {
                System.err.println("服务器等待了1秒，无连接！");
                continue;
            }
            // 如果返回大于0，就获取相关selectionKeys集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 处理连接事件
                if (selectionKey.isAcceptable()) {
                    // 接收请求事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketChannel注册到selector,同时给socketChannel关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                // 处理请求
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    // 获取channel关联到的 Buffer
                    ByteBuffer attachment = (ByteBuffer) selectionKey.attachment();
                    // 将channel中的内容写入Buffer中
                    channel.read(attachment);
                    System.err.println("接收到请求：" + new String(attachment.array()));
                }
                // 手动移除掉事件 避免重复处理
                iterator.remove();
            }
        }
    }
}

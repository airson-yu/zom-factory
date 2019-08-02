package com.zom.factory.zfactory.zlab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IOTest {

    public static void main(String[] args) throws IOException {
        doit();
    }

    public static void doit() throws IOException {
        File file = new File("D:/work/data.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String string = "java nio";
        buffer.put(string.getBytes());
        buffer.flip(); //此处必须要调用buffer的flip方法
        channel.write(buffer);
        channel.close();
        outputStream.close();
    }

    public static void nio() {
        RandomAccessFile aFile = null;
        try {

            aFile = new RandomAccessFile("src/nio.txt", "rw");
            // channel获取数据
            FileChannel fileChannel = aFile.getChannel();
            // 初始化Buffer，设定Buffer每次可以存储数据量
            // 创建的Buffer是1024byte的，如果实际数据本身就小于1024，那么limit就是实际数据大小
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // channel中的数据写入Buffer
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while (bytesRead != -1) {
                // Buffer切换为读取模式
                buf.flip();
                // 读取数据
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                // 清空Buffer区
                buf.compact();
                // 继续将数据写入缓存区
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*public static void selector() throws IOException {
        Selector selector = Selector.open();
        Channel channel  = selectionKey.channel();
        channel.configureBlocking(false);
        SelectionKey key2 = channel.register(selector, SelectionKey.OP_READ);
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }

    }*/
}

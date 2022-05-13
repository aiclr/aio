package org.bougainvilleas.base.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer
{
    public static void main(String[] args) {
        try(
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
        ) {
            //bind 端口
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            //把serverSocketChannel注册到selector,关心OP_ACCEPT连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while(true){
                //等待2秒，如果没事件返回
                if(selector.select(2000)==0){
                    System.err.println("服务器等待2秒无连接");
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()){
                    //获取SelectionKey--事件
                    SelectionKey selectionKey = keyIterator.next();
                    System.err.println("有事件发生"+selectionKey.hashCode());
                    //连接事件发生才会生成socketChannel
                    if(selectionKey.isAcceptable()){
                        //此处已经确认知道发生了连接事件，所以accept不会阻塞
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        //将客户端socketchannel设置为非阻塞
                        socketChannel.configureBlocking(false);
                        //声明接收数据的buffer
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        //将生成的socketchannel注册到selector,设置为OP_READ事件，绑定buffer
                        socketChannel.register(selector,SelectionKey.OP_READ,byteBuffer);
                    }
                    //读事件发生
                    if(selectionKey.isReadable()){
                        //通过事件selectionKey反向获取channel
                        SocketChannel channel = (SocketChannel)selectionKey.channel();
                        //获取绑定的buffer
                        ByteBuffer buffer =(ByteBuffer) selectionKey.attachment();
                        /**
                         将buffer数据读取到channel，
                         大多数情况是返回一个大于等于0的值，表示将多少数据读入byteBuffer缓冲区
                         然而，当客户端   正常    断开连接的时候，它就会返回-1。
                         虽然这个断开连接信号也是可读数据(会使得isReadable()为true)，
                         但是
                         这个信号无法被读入byteBuffer，
                         也就是说一旦返回-1，那么无论再继续读多少次都是-1，
                         会引发可读事件isReadable(),会一直死循环读取buffer
                         */
                        int read = channel.read(buffer);
                        if(read==-1){//-1标识读完数据可以关闭通道，一次网络连接中断
                            //注销事件，会将key从selector中移除
//                        selectionKey.channel();
                            //关闭通道
                            channel.close();
                        }else {
                            System.err.println(new String(buffer.array()));
                        }
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

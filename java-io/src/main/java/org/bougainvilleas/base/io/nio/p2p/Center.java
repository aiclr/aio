package org.bougainvilleas.base.io.nio.p2p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Center {

  private static final int port = 6666;
  private static final boolean blocking = false;

  public static void main(String[] args) {
    //保存 所有客户端信息
    List<String> pList = new ArrayList<>();

    try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
         Selector selector = Selector.open()) {

      //bind 端口
      serverSocketChannel.socket().bind(new InetSocketAddress(port));
      //设置非阻塞
      serverSocketChannel.configureBlocking(blocking);
      //把serverSocketChannel注册到selector,关心OP_ACCEPT连接事件
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.err.println("===>" + serverSocketChannel.hashCode());
      while (true) {
        //等待2秒，如果没事件返回
        if (selector.select(2000) == 0) {
          System.err.println("服务器等待2秒无连接");
          continue;
        }
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
        while (keyIterator.hasNext()) {
          //获取SelectionKey--事件
          SelectionKey selectionKey = keyIterator.next();
          System.err.println("有事件发生" + selectionKey.hashCode());
          //连接事件发生才会生成socketChannel
          if (selectionKey.isAcceptable()) {
            //获取客户端 SocketChannel 此处已经确认知道发生了连接事件，所以accept不会阻塞
            SocketChannel socketChannel = serverSocketChannel.accept();
            //将客户端socketchannel设置为非阻塞
            socketChannel.configureBlocking(false);
            //声明接收数据的buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //将生成的socketchannel注册到selector,设置为OP_READ事件，绑定buffer 之后从客户端读取数据
            socketChannel.register(selector, SelectionKey.OP_READ, byteBuffer);
            System.err.println("get connection from" + socketChannel);
          }
          //读事件发生
          if (selectionKey.isReadable()) {
            //通过事件selectionKey反向获取channel
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            //获取绑定的buffer
            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
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
            int read = socketChannel.read(byteBuffer);//position 会移动
            //-1标识读完数据可以关闭通道，一次网络连接中断
            if (read == -1) {
              //注销事件，会将key从selector中移除
//              selectionKey.channel();
              //关闭通道
              socketChannel.close();
            } else {
              byteBuffer.flip();
              byte[] readBytes = new byte[byteBuffer.limit()];
              byteBuffer.get(readBytes);
              byteBuffer.clear();
              String readStr = new String(readBytes);
              pList.add(readStr);
              P2PUtils.showPList(pList);
              System.err.println("get data from " + socketChannel);
//              socketChannel.register(selector, SelectionKey.OP_WRITE, byteBuffer);
            }
          }
          //写事件发生
//          if (selectionKey.isWritable()) {
//            //获取客户端 SocketChannel 此处已经确认知道发生了连接事件，所以accept不会阻塞
//            SocketChannel socketChannel = serverSocketChannel.accept();
//            //获取绑定的buffer
//            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
//            for (String p : pList) {
//              buffer.clear();
//              byte[] bytes = p.getBytes(StandardCharsets.UTF_8);
//              buffer.put(bytes);
//              //设置 数据长度
//              buffer.limit(bytes.length);
//              socketChannel.write(buffer);
//              //读事件发生后 才写入数据到客户端
//              socketChannel.register(selector, SelectionKey.OP_READ, buffer);
//            }
//            System.err.println("send data to " + socketChannel);
//          }
          keyIterator.remove();
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}

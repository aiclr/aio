package org.bougainvilleas.base.io.nio.p2p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class P1 {

  public static void main(String[] args) {

    //保存 所有客户端信息
    List<String> pList = new ArrayList<>();
    try (SocketChannel socketChannel = SocketChannel.open()) {

      InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
      socketChannel.configureBlocking(false);
      if (!socketChannel.connect(address)) {
        while (!socketChannel.finishConnect()) {
          System.err.println("连接需要时间，客户端不会阻塞，可以做其他工作");
        }
      }
      //声明接收数据的buffer
      ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
      String msg = "{\"ip\":\"127.0.0.1\",\"port\":6668}";
      byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
      byteBuffer.clear();
      byteBuffer.put(bytes);
      byteBuffer.limit(bytes.length);
      //发送服务器读取前 flip()
      byteBuffer.flip();
      socketChannel.write(byteBuffer);
      //发送服务器后 byteBuffer 前先清空
      byteBuffer.clear();
      //读
      new Thread(() -> {
        //等待 服务端下发数据
        while (true) {
          try {
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
              break;
            }else if(read > 0){
              //重新读取
              byteBuffer.flip();
              byte[] readBytes = new byte[byteBuffer.limit()];
              byteBuffer.get(readBytes);
              byteBuffer.clear();
              String readStr = new String(readBytes);
              pList.add(readStr);
              System.err.println("get data from " + socketChannel);
              System.err.println("data " + readStr);
            }
          }catch (IOException ex){
            throw new RuntimeException(ex);
          }
        }
        //断开连接后 即break 会运行到此处 可以退出客户端
        System.exit(0);
      }).start();

      while (true){
        //保持main
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}

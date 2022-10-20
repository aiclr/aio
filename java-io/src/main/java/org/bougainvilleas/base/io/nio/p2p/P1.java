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
      String msg="{\"ip\":\"127.0.0.1\",\"port\":6668}";
      byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
      byteBuffer.clear();
      byteBuffer.put(bytes);
      byteBuffer.limit(bytes.length);
      //发送服务器读取前 flip()
      byteBuffer.flip();
      socketChannel.write(byteBuffer);

      //服务端数据写入 byteBuffer 前先清空
//      byteBuffer.clear();
//      while (socketChannel.read(byteBuffer) != -1){
//        byte[] readBytes=new byte[byteBuffer.limit()];
//        for (int i = 0; i < readBytes.length; i++) {
//          readBytes[i]=byteBuffer.get();
//        }
//        pList.add(new String(readBytes));
//        P2PUtils.showPList(pList);
//      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

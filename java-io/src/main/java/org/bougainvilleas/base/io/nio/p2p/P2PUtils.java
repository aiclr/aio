package org.bougainvilleas.base.io.nio.p2p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.List;

public class P2PUtils {

  public static ServerSocketChannel ServerSocketInit(int port,boolean blocking) {
    try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
      //bind 端口
      serverSocketChannel.socket().bind(new InetSocketAddress(port));
      //设置非阻塞
      serverSocketChannel.configureBlocking(blocking);
      return serverSocketChannel;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    throw new RuntimeException("服务器注册失败");
  }

  public static Selector SelectorOpen() {
    try (Selector selector = Selector.open()) {
      return selector;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    throw new RuntimeException("服务器注册失败");
  }


  public static void showPList(List<String> pList) {
    for (String p : pList) {
      System.err.println(p);
    }
  }
}

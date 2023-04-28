package org.bougainvilleas.base.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 使用BIO模型编写一个服务器端，监听6666端口，当有客户端链接时，就启动一个线程与之通讯
 * 要求使用线程池机制改善，可以连接多个客户端
 * 服务器短可以接收客户端发送的数据（客户端使用telnet工具）
 *
 * Blocking I/O 传统java io编程，其相关的类和接口在java.io
 * 同步阻塞，服务器实现为一个连接一个线程，客户端有连接请求是服务器短就需要启动一个线程进行处理
 * 如果这个连接不做任何事情，会造成不必要的线程开销，可以通过线程池机制改善
 * BIO适合连接数目比较小且固定的架构，这种方式对服务器资源要求比较高，并发局限与应用中，jdk1.4以前的唯一选择，程序简单易理解
 *
 * BIO简单流程
 * 1.服务器端启动一个ServerSocket
 * 2.客户端启动Socket对服务器进行通信，默认情况下服务器端需要对每个客户建立一个线程与之通讯
 * 3.客户端发出请求后，先咨询服务器是否有线程响应如果没有则会等待，或被拒绝
 * 4.如果有响应，客户端线程会等待请求结束后再继续执行
 *
 * 1.每个请求都需要创建独立的线程，与对应的客户端进行数据Read、业务处理、数据Write
 * 2.并发数较大时，需要创建大量线程来处理连接
 * 3.连接建立后，如果当前线程暂时没有数据可读，则线程阻塞在Read操作上，造成线程资源两浪费
 */
public class BIOServer
{

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try ( ServerSocket serverSocket =new ServerSocket(6666)){
            System.err.println("server is starting...");
            while (true) {
                //当没有客户端连接会阻塞在accept()
                final Socket socket = serverSocket.accept();
                System.out.println("连接客户端");
                executorService.execute(() -> handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            //当链接后没有消息发送会阻塞在read（）
            while (inputStream.read(bytes) != -1) {
                System.err.println(new String(bytes, 0, inputStream.read(bytes)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.err.println("客户端关闭");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

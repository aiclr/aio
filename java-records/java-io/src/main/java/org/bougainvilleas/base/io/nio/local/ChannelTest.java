package org.bougainvilleas.base.io.nio.local;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Channel:用于源节点与目标节点的连接，java nio中负责缓冲区中数据的传输
 * Channel本身不存储数据，因此需要配合缓冲区进行传输
 * <p>
 * Channel实现类
 * java.nio.channels.Channel接口
 * FileChanel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 * <p>
 * 获取通道
 * java针对支持Channel的类提供getChannel()方法
 * 本地IO：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * jdk7,NIO.2针对各种Channel提供静态方法open()
 * jdk7,NIO.2 Files工具类newByteChannel()
 * <p>
 * 通道之间数据传输
 * transferForm()
 * transferTo()
 * <p>
 * 分散读取与聚集写入
 * Scattering Reads 将通道中的数据分散到多个缓冲区中
 * Gathering Writes 将多个缓冲区中的数据聚集到通道中
 * <p>
 * 字符集 Charset
 * 编码 字符串-->字节数组
 * 解码 字节数组-->字符串
 */
public class ChannelTest
{
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
//        test5();
//        test6();
//        test4();
//        test3("/home/caddy/Downloads/blackarch-linux-full-2020.12.01-x86_64.iso",
//                "/disk/ssd/test/5.iso");
//        test2(1000 * 1000 * 1024,
//                "/home/caddy/Downloads/blackarch-linux-full-2020.12.01-x86_64.iso",
//                "/disk/ssd/test/5.iso");
        test2(6000,
                "/home/caddy/gc1.png",
                "/home/caddy/gc1-1.png");
        System.out.println(System.currentTimeMillis() - start);
//        test1();
    }

    //复制文件非直接缓冲区
    public static void test1() {
        FileInputStream fis = null;
        FileOutputStream fos  = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            fis = new FileInputStream("/home/caddy/Downloads/kali-linux-2020.4-live-amd64.iso");
            fos = new FileOutputStream("/home/caddy/22.iso");
            //获取通道
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();
            //分配缓冲区大小
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //从通道写入数据到缓冲区
            while (fisChannel.read(buf) != -1) {
                //切换为读模式
                buf.flip();
                //缓冲区中的数据写入通道中
                fosChannel.write(buf);
                buf.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fosChannel != null)
                    fosChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fisChannel != null)
                    fisChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 复制文件直接缓冲区
     * 直接在堆外内存（物理）修改，操作系统不需要再拷贝一次
     * MappedByteBuffer
     *
     * mode 读写模式
     * position 直接修改的其实位置
     * size 映射内存的大小,最大值不能超过Integer.MAX_VALUE
     * MappedByteBuffer map(MapMode mode,long position, long size)
     *
     * @param l
     * @param path
     * @param newFilePath
     */
    public static void test2(long l,String path,String newFilePath) {
        MappedByteBuffer inmapbuf;
        MappedByteBuffer outmapbuf;
        int byteSize = (int) l;
        byte[] bytes = new byte[byteSize];
//        long l=102400000l;//100M=100*1000*1024byte
        try (FileChannel fisChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ);
             FileChannel fosChannel = FileChannel.open(Paths.get(newFilePath), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)){
            long filesize = fisChannel.size();//返回值单位为byte

            for (long i = 0; i < fisChannel.size(); i += l, filesize -= l) {
                l = i + l > fisChannel.size() ? filesize : l;
                inmapbuf = fisChannel.map(FileChannel.MapMode.READ_ONLY, i, l);
                outmapbuf = fosChannel.map(FileChannel.MapMode.READ_WRITE, i, l);
                if (byteSize > inmapbuf.limit()) {
                    bytes = new byte[inmapbuf.limit()];
                }
                inmapbuf.get(bytes);
                //会修改path文件，不会修改newFilePath
                // 需要设置：
                // FileChannel fisChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ,StandardOpenOption.WRITE);
                // inmapbuf = fisChannel.map(FileChannel.MapMode.READ_WRITE, i, l);
//                inmapbuf.put(1,(byte)'-');
                outmapbuf.put(bytes);
                //会修改newFilePath，不会修改path文件
                outmapbuf.put(1,(byte)'-');

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void test2plus(long l,String path,String newFilePath) {
        MappedByteBuffer inmapbuf;
        int byteSize = (int) l;
        byte[] bytes = new byte[byteSize];
        try (FileChannel fisChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ,StandardOpenOption.WRITE);
             FileChannel fosChannel = FileChannel.open(Paths.get(newFilePath), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE))
        {
            long filesize = fisChannel.size();//返回值单位为byte数
            for (long i = 0; i < fisChannel.size(); i += l, filesize -= l) {
                l = i + l > fisChannel.size() ? filesize : l;
                inmapbuf = fisChannel.map(FileChannel.MapMode.READ_ONLY, i, l);
                if (byteSize > inmapbuf.limit()) {
                 bytes = new byte[inmapbuf.limit()];
                }
                inmapbuf.get(bytes);
                //会修改path文件，会修改newFilePath
                // 需要设置：
                // FileChannel fisChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ,StandardOpenOption.WRITE);
                // inmapbuf = fisChannel.map(FileChannel.MapMode.READ_WRITE, i, l);

                //inmapbuf.put(1,(byte)'-');
                inmapbuf.flip();
                fosChannel.write(inmapbuf);
                inmapbuf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //通道之间数据传输 直接缓冲区 15g 文件直接停电,可能是固态太热了，掉盘断电java
    public static void test3(String path,String newFilePath) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(newFilePath), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        //下面二选一
        //inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        outChannel.close();
        inChannel.close();
    }

    //分散读取 聚集写入
    public static void test4() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("/home/caddy/redis/conf/redis.conf", "rw");

        FileChannel channel1 = raf1.getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        ByteBuffer[] bufs = {buf1, buf2};
        //分散读取
        channel1.read(bufs);
        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("###SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("/home/caddy/redis2.conf", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);

        channel2.close();
        channel1.close();
        raf2.close();
        raf1.close();

    }

    //字符集
    public static void test6() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");
        //编码器
        CharsetEncoder encoder = gbk.newEncoder();
        //解码器
        CharsetDecoder decoder = gbk.newDecoder();

        CharBuffer cb = CharBuffer.allocate(1024);
        cb.put("冷冷的冰雨在脸上胡乱的拍");
        //切换读模式
        cb.flip();
        //编码
        ByteBuffer eb = encoder.encode(cb);
        for (int i = 0; i < eb.limit(); i++) {
            System.out.println(eb.get());
        }
        //切换读模式
        eb.flip();
        CharBuffer decode = decoder.decode(eb);
        System.out.println(decode.toString());


        Charset utf8 = Charset.forName("UTF-8");
        eb.flip();
        CharBuffer decode1 = utf8.decode(eb);
        System.out.println(decode1.toString());

        Charset gbk1 = Charset.forName("GBK");
        eb.flip();
        CharBuffer decode2 = gbk1.decode(eb);
        System.out.println(decode2.toString());


    }

    public static void test5() {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}

package org.bougainvilleas.base.dsaa.tree.huffman;

import lombok.ToString;

import java.io.*;
import java.util.*;

/**
 * huffman Coding 一种编码方式
 * 电讯通信经典应用
 * 广泛用于数据文件压缩，压缩率通常在20%~90%之间（重复字越多，压缩率越高）
 * huffman code是可变字长编码（VLC）的一种，1952年提出的编码方法，称之为最佳编码
 * <p>
 * eg:
 * 待处理字符串
 * i like like like java do you like a java
 * asci编码二进制的长度为539
 * 无损压缩后的
 * huffman编码长度为133
 * <p>
 * 统计各字符出现的次数
 * d=1 y=1 u=1 j=2 v=2 o =2 l=4 k=4 e=4 i=5 a=5 空格=9
 * 按照上面字符出现次数，构建一棵huffmanTree，出现次数=权值
 * <p>
 * 构建huffmanTree时比较权值的规则不同生成的树也不同，相等的权值怎么排序会影响树的形状
 * 每个字符的编码会不一样
 * 但是wpl不会变===huffman编码长度 不会变=133
 * <p>
 * <p>
 * <p>
 * 根据huffmanTree进行编码，向左路径为0,向右路径为1
 * 40
 * 0/         \1
 * 17           23
 * 0/   1\       0/  1\
 * 8  (空格)9   10  13
 * 。。。。。。
 * <p>
 * 空格 9的编码即为01
 * 如果8也是某个字符，则8的编码为00
 * 分析，huffmanTree，原有数据，都在叶子结点，每个叶子结点的路径肯定不一样，
 * 这样就不会出现多义编码
 * <p>
 * eg：多义编码
 * 空格=01
 * i= 010
 * i空格=01001
 * 当解析时，
 * 扫描前两位01,解析成了空格
 * 扫描前3位010,解析成了i
 * 这就出现了多义
 * huffmanTree处理后的字符编码，
 * 由于每个字符数据都在叶子结点，
 * 任一字符编码的前几位（父结点），绝对不会是其他字符编码
 */
public class HuffmanCoding {

    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
//        testBit();
//        String str = "i like like like java do you like a java java java like java like you";
//        byte[] data = str.getBytes(StandardCharsets.UTF_8);
//        byte[] zip = huffmanZip(data);
        //解码
//        byte[] decode = decode(zip,huffmanCodes);
//        System.err.println(Arrays.toString(decode));
//        String result = new String(decode);
//        System.err.println(result);

        //图片压缩测试当图片颜色很丰富压缩率不高，当图片很单调时压缩率还可以
        //色彩丰富
//        String srcFile="/home/caddy/Pictures/bg/KUN_1858.NEF";//25M
//        String dstFile="/home/caddy/DSC_0099.zip";//25M
//        zipFile(srcFile,dstFile);
        //单调图片
//        String srcFile2="/home/caddy/Pictures/20201203_bak/KUN_1858.NEF"; //43M
//        String dstFile2="/home/caddy/KUN_1858.NEF.zip";//29M
//        zipFile(srcFile2,dstFile2);

        //文件解压缩
//        String srcFile = "/home/caddy/KUN_1858.NEF.zip";//29M
//        String dstFile = "/home/caddy/KUN_1858.NEF";//43M
//        unzipFile(srcFile, dstFile);


    }

    /**
     * @param srcFile 待解压缩文件路径
     * @param dstFile 解压缩后文件路径
     */
    public static void unzipFile(String srcFile, String dstFile) {

        try (
                InputStream is = new FileInputStream(srcFile);
                ObjectInputStream ois = new ObjectInputStream(is);
                OutputStream os=new FileOutputStream(dstFile)
        ) {
            byte[] huffmanBytes =(byte[]) ois.readObject();
            Map<Byte, String> huffmanCode=(Map<Byte, String>)ois.readObject();
            byte[] decode = decode(huffmanBytes,huffmanCode);
            //写数据到文件
            os.write(decode);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    /**
     * 压缩文件
     * 注意事项：
     *  如果文件本身就是经过压缩处理的，那么huffman编码再压缩效率不会有明显变化，比如视频，ppt等
     *  huffman编码是按字节来处理的，因此可以处理所有的文件
     *  如果一个文件中的内容重复数据不多，压缩效果也不明显
     *
     * @param srcFile 待压缩文件路径
     * @param dstFile 压缩后文件路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        try (
                FileInputStream is = new FileInputStream(srcFile);
                FileOutputStream os = new FileOutputStream(dstFile);
                ObjectOutputStream oos = new ObjectOutputStream(os)
        ) {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            //压缩文件
            byte[] huffmanZip = huffmanZip(bytes);
            //以  对象流  的方式写入huffman编码，是为了以后恢复源文件使用
            oos.writeObject(huffmanZip);
            oos.writeObject(huffmanCodes);//huffman编码也要写入压缩文件，解码时需要使用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * zip 的最后一个不需要补位,且最后一位肯定是1开头，因为最后一位生成的时候肯定不会是0开头
     *
     * @param zip
     * @return 原来字符串对应的byte数组
     */
    private static byte[] decode(byte[] zip,Map<Byte, String> huffmanCode) {
        boolean flag = true;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < zip.length; i++) {
            if (i == zip.length - 1) {
                flag = false;
            }
            builder.append(byteToBitString(zip[i], flag));
        }
//        System.err.println("使用huffmanCodes解码字符串为\n"+builder.toString());

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        String ss = "01234567";
//        System.err.println(ss.substring(1,3));
//        System.err.println(builder.substring(1,3));
        //遍历得到原始字符串的byte[]
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < builder.length(); ) {
            int count = 1;
            boolean bol = true;
            Byte b = null;
            while (bol) {
                //public String substring(int start, int end)  start ,end是下标 [start,end) 包含start ，不包含end
                //abcdef,substring(1,3)==bc
                String str = builder.substring(i, i + count);
                b = map.get(str);
                if (b == null) {
                    count++;
                } else {
                    bol = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte by[] = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            by[i] = list.get(i);
        }
        return by;
    }


    /**
     * 将byte转成二进制的字符串
     * <p>
     * 如果原数据的长度不是8的倍数，即最后一位的长度不是8位（不够8位肯定是个正数，因为满8位且第一位是1才会是负数）
     * 不能进行补位，否则解码肯定失败，所以设置一个flag，确定是否补高位
     *
     * @param b    byte
     * @param flag 是否需要补高位，true需要补高位，false不需要补高位
     * @return b对应的二进制字符串（按的是补码返回）
     */
    private static String byteToBitString(byte b, boolean flag) {
        int tmp = b;
        //需要补高位
        if (flag) {
            tmp |= 256;
        }
        String s = Integer.toBinaryString(tmp);//返回tmp对应的二进制的补码
        //正数和0需要补高位
        if (flag) {
            return s.substring(s.length() - 8);
        } else return s;
    }


    /**
     * @param data 原始字符串对应的数组
     * @return 压缩后的数组
     */
    private static byte[] huffmanZip(byte[] data) {
        //获取huffmanTree
        DataNode root = createHuffmanTree(getNodes(data));
        //前序遍历huffmanTree
//        System.err.println("前序遍历huffmanTree");
//        preOrder(root);
        //获取huffmanCodes
        //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
        getCodes(root);
//        System.err.println("根据huffmanTree获取的huffman编码为： \n" + huffmanCodes);
//        System.err.println("压缩前byte数组长度=" + data.length);
//        System.err.println("压缩前byte数组\n" + Arrays.toString(data));
        //使用huffmanCodes，编码str
        byte[] zip = zip(data);
//        System.err.println("压缩后byte数组长度=" + zip.length);
//        System.err.println("压缩后byte数组\n" + Arrays.toString(zip));
        return zip;
    }

    /**
     * 使用huffmanCode压缩原文
     *
     * @param data 待压缩字符串 的 byte[]40个
     *             [i, ,l,i,k,e, ,l,i,k,e, ,l,i,k,e, ,j,a,v,a, ,d,o, ,y,o,u, ,l,i,k,e, ,a, ,j,a,v,a]
     *             是各字母对应的asci码值
     *             [105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
     * @return 压缩后的 byte[] 17个
     * [8, -121, -88, -121, -88, -121, -87, -115, 47, 110, 72, 122, 115, -68, 36, -114, 92]
     */
    public static byte[] zip(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (Byte b : data) {
            builder.append(huffmanCodes.get(b));
        }
        String str = builder.toString();
//        System.err.println("使用huffmanCodes转换后的机器码字符串为：\n" + str);
        //将编码后的字符串，转成机器码，补码，反码
        // byte 8位，第一位符号位不动 0正1负
        // 1 0101000 求补码(-1)--->1 0100111 求反码---> 1 1011000 转十进制---> -2^6+2^4+2^3=-88
        // -88转机器码 负号占首位，8位10进制 1 1011000求反码---> 1 0100111求补码+1--->10101000即机器码
//        System.err.println((byte) Integer.parseInt("10101000", 2));//一定要强制转换为byte
        //现在需要将编码的字符串 8个一组 转换为10进制 存放到 byte[]，这样才能起到压缩的作用
        //byte[] 数组长度=(str.length()+7)/8
        // 如果刚好是8的倍数，+7不会改变结果===>(16+7)/8=2
        // 如果不是8的倍数,+7肯定会使结果+1===>(12+7)/8=2
        byte[] bytes = new byte[(str.length() + 7) / 8];
        int index = 0;
        for (int i = 0; i < str.length(); i += 8) {
            if (i + 8 < str.length()) {
                bytes[index] = (byte) Integer.parseInt(str.substring(i, i + 8), 2);
            } else {
                //正数的二进制，不会是0开头，解码时候最后一位不要补高位即可
                bytes[index] = (byte) Integer.parseInt(str.substring(i), 2);
            }
            index++;
        }
        return bytes;
    }


    private static void getCodes(DataNode root) {
        if (root == null) {
            return;
        }
//        getCodes(root,"",sb);
        getCodes(root.left, "0", sb);
        getCodes(root.right, "1", sb);
    }


    /**
     * 遍历huffmanTree,往左为0,往右为1,使用StringBuilder拼接获取编码
     *
     * @param node          huffmanTree 根结点
     * @param code          路径：左子结点=0,右子结点=1
     * @param stringBuilder 拼接code
     */
    public static void getCodes(DataNode node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);
        if (node != null) {
            //非有效结点就去遍历子树
            if (node.data == null) {
                //递归
                getCodes(node.left, "0", builder);
                getCodes(node.right, "1", builder);
            } else {
                //找到有效结点
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }


    /**
     * @param nodes 待转换huffmanTree的数据
     * @return huffmanTree根结点
     */
    public static DataNode createHuffmanTree(List<DataNode> nodes) {
        while (nodes.size() > 1) {
            //从小到大
            Collections.sort(nodes);
//            System.err.println(nodes);
            //取出权值最小的结点
            DataNode leftNode = nodes.get(0);
            //取出权值次小的结点
            DataNode rightNode = nodes.get(1);

            DataNode parent = new DataNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //从集合中删除处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static List<DataNode> getNodes(byte[] bytes) {
        List<DataNode> nodes = new ArrayList<>();

        Map<Byte, Integer> count = new HashMap<>();
        for (byte b : bytes) {
            if (count.containsKey(b)) {
                Integer sum = count.get(b);
                count.put(b, sum + 1);
            } else {
                count.put(b, 1);
            }
        }
        for (Byte b : count.keySet()) {
            DataNode dataNode = new DataNode(b, count.get(b));
            nodes.add(dataNode);
        }
        return nodes;
    }

    //前序遍历
    public static void preOrder(DataNode root) {
        if (root == null) {
            System.err.println("空树");
        }
        root.postOrder();
    }

    public static void testBit() {
        System.err.println("测试byte转二进制字符串");
        //二进制正数和负数 同256 按位或运算，
        // 或运算 该位有1结果即是1
        //正数1000      补高位 0 0000 1000 | 1 0000 0000=1 0000 1000
        //负数1010 1000 补高位 0 1010 1000 | 1 0000 0000=1 1010 1000
        //如果只取后8位，可见，正数补位到8位，负数不变
        System.err.println(" 8 应该是0000 1000===>" + Integer.toBinaryString(8 | 256));
        System.err.println(" 2 应该是0000 0010===>" + Integer.toBinaryString(2 | 256));
        System.err.println(" 1 应该是0000 0001===>" + Integer.toBinaryString(1 | 256));
        System.err.println(" 0 应该是0000 0000===>" + Integer.toBinaryString(0 | 256));
        /**
         * 负数 第一位为符号位1,先不管符号求得二进制原码，按位取反得反码，反码+1的补码，最后加上符号位，即机器码
         * 正数 原码，反码，补码相同
         */
        //负数需要截取后八位
        System.err.println("-1 应该是1111 1111===>" + Integer.toBinaryString(-1));
        System.err.println("-2 应该是1111 1110===>" + Integer.toBinaryString(-2));
        System.err.println("-88应该是1010 1000===>" + Integer.toBinaryString(-88));


    }
}

@ToString(exclude = {"left", "right"})
class DataNode implements Comparable<DataNode> {
    Byte data;//ASCI码 a=97 空格' '=32
    int weight;
    DataNode left;
    DataNode right;

    //前序遍历
    public void postOrder() {
        System.err.println(this);
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
    }

    public DataNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(DataNode o) {
        return this.weight - o.weight;
    }
}

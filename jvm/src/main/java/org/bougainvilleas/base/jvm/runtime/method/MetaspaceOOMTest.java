package org.bougainvilleas.base.jvm.runtime.method;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * *************************此处有大坑*********************************************
 * gradle compile时无法加载到rt.jar
 * 所以需要引入相关rt.jar
 * 引入方式参考jvm模块下的build.gradle文件
 *
 * Metaspace OOM
 * JDK7
 * -XX:PermSize=10M -XX:MaxPermSize=10m
 *
 * JDK8
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * 3331
 * Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
 *
 * JDK8
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M -XX:-UseCompressedClassPointers
 * 9344
 * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
 * 作者：大宽宽
 * 链接：https://www.zhihu.com/question/268392125/answer/350249173
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * 在Java8以前，有一个选项是UseCompressedOops。所谓OOPS是指“ordinary object pointers“，就是原始指针。
 * Java Runtime可以用这个指针直接访问指针对应的内存，做相应的操作（比如发起GC时做copy and sweep）。
 * 那么Compressed是啥意思？
 * 64bit的JVM出现后，OOPS的尺寸也变成了64bit，比之前的大了一倍。
 * 这会引入性能损耗——占的内存double了，并且同尺寸的CPU Cache要少存一倍的OOPS。
 * 于是就有了UseCompressedOops这个选项。
 * 打开后，OOPS变成了32bit。
 * 但32bit的base是8，所以能引用的空间是32GB——这远大于目前经常给jvm进程内存分配的空间。
 * 一般建议不要给JVM太大的内存，因为Heap太大，GC停顿实在是太久了。
 * 所以很多开发者喜欢在大内存机器上开多个JVM进程，每个给比如最大8G以下的内存。
 * 从JDK6_u23开始UseCompressedOops被默认打开了。因此既能享受64bit带来的好处，又避免了64bit带来的性能损耗。
 * 当然，如果你有机会使用超过32G的堆内存，记得把这个选项关了。
 * 到了Java8，永久代被干掉了，有了“meta space”的概念，存储jvm中的元数据，包括byte code，class等信息。
 * Java8在UseCompressedOops之外，额外增加了一个新选项叫做UseCompressedClassPointer。
 * 这个选项打开后，class信息中的指针也用32bit的Compressed版本。
 * 而这些指针指向的空间被称作“Compressed Class Space”。
 * 默认大小是1G，但可以通过“CompressedClassSpaceSize”调整。
 * 如果你的java程序引用了太多的包，有可能会造成这个空间不够用，
 * 于是会看到java.lang.OutOfMemoryError: Compressed class space
 * 这时，一般调大CompreseedClassSpaceSize就可以了
 *
 *
 * JVM 有个功能是 CompressedOops ，目的是为了在 64bit 机器上使用 32bit 的原始对象指针来节约成本（减少内存/带宽使用），提高性能（提高 Cache 命中率）。
 * 使用了这个压缩功能，每个对象中的 Klass* 字段就会被压缩成 32bit（不是所有的 oop 都会被压缩的），
 * 众所周知 Klass* 指向的 Klass 在永久代（Java7 及之前）。
 * 但是在 Java8 及之后，永久代没了，出现了Metaspace，于是之前压缩指针 Klass* 指向的这块 Klass 区域有了一个名字 —— Compressed Class Space。
 * Compressed Class Space 是 Metaspace 的一部分，默认大小为 1G。
 * 所以其实 Compressed Class Space 这个名字取得很误导，压缩的并不是 Klass，而是 Klass*。
 * JVM 也为 compressed class pointers（Klass*）多了俩选项：
 * -XX:+UseCompressedClassPointers（压缩开关）
 * -XX:CompressedClassSpaceSize（Compressed Class Space 空间大小限制）。
 * 堆大小要是大于 32G，CompressedOops 自动关闭，CompressedClassPointers 也会关闭的，关闭就没有 Compressed Class Space 了，这块就是 Class Space 了。
 * -XX:CompressedClassSpaceSize 大小的设置也是有限制，因为压缩开关是受制于 32G，所以这个自然也是不能大于 32G
 * hotspot 规定了这个参数不准大于 3G。
 * 如果遇到了 `java.lang.OutOfMemoryError: Compressed class space`，就是类太多了，需要结合具体情况去选择 JVM 调优还是 bug 排查。
 */
public class MetaspaceOOMTest extends ClassLoader{

    public static void main(String[] args) {
        int j=0;
        try {
            MetaspaceOOMTest test = new MetaspaceOOMTest();
            for (int i = 0; i < 10000; i++) {
                ClassWriter classWriter=new ClassWriter(0);
                //指明版本号，修饰符，类名，包名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                byte[] code = classWriter.toByteArray();
                //extends CLassLoader 类加载
                test.defineClass("Class"+i,code,0,code.length);
                j++;
            }
        }finally {
            System.out.println(j);
        }
    }
}

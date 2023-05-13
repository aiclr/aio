#include <stdio.h>
#include "org_bougainvilleas_base_jvm_runtime_NativeMethodTest.h"

/**
 * 需要手动指定头文件 /lib/jvm/java-17-openjdk/include/jni.h 和/lib/jvm/java-17-openjdk/include/linux/jni_md.h
 * 共享库编译 gcc -fPIC -I /lib/jvm/java-17-openjdk/include/ -I /lib/jvm/java-17-openjdk/include/linux -shared Sayhi.c -o libsayhi.so
 * 将共享库放到java能访问到的目录下 sudo cp libsayhi.so /usr/lib/
 */
JNIEXPORT void JNICALL Java_org_bougainvilleas_base_jvm_runtime_NativeMethodTest_sayHi(JNIEnv *, jobject, jstring, jstring)
{
    printf("hi\n");
}
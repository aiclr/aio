package org.bougainvilleas.ilg.study

import com.sun.management.OperatingSystemMXBean

import java.lang.management.ManagementFactory

def getGC() {
    def garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans()
    garbageCollectorMXBeans.forEach { it ->
        println(it)
        println(it.getName())
        println(it.getCollectionTime())
        println(it.getCollectionCount())
    }
}

def getOS() {
    OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean
    println(os)
    println(os.getArch())// 系统架构
    println(os.getVersion()) //系统版本
    println(os.getName()) // 系统名称
    println(String.format("%.2f%%", os.getSystemLoadAverage())) // 上一分钟的系统负载
    println(os.getAvailableProcessors())//jvm可用处理器数

    println(os.getTotalPhysicalMemorySize()/1024/1024/1024)
    println(os.getFreePhysicalMemorySize()/1024/1024/1024)
    println(os.getCommittedVirtualMemorySize()/1024/1024/1024)

    println(os.getSystemCpuLoad())
    println(os.getProcessCpuLoad())
    println(os.getProcessCpuTime())


    println(os.getFreeSwapSpaceSize())
    println(os.getTotalSwapSpaceSize())

}


def getHeap() {
    def memoryMXBean = ManagementFactory.getMemoryMXBean()
    def heap = memoryMXBean.getHeapMemoryUsage()
    def nonHeap = memoryMXBean.getNonHeapMemoryUsage()
    println(heap.getInit() / 1024 / 1024)//堆内存使用情况
    println(heap.getMax() / 1024 / 1024)//堆内存使用情况
    println(heap.getUsed() / 1024 / 1024)//堆内存使用情况
    println(heap.getCommitted() / 1024 / 1024)//堆内存使用情况
//    println()
//    println(nonHeap.getInit()/1024/1024)//堆外内存使用情况
//    println(nonHeap.getMax())//堆外内存使用情况
//    println(nonHeap.getUsed()/1024/1024)//堆外内存使用情况
//    println(nonHeap.getCommitted()/1024/1024)//堆外内存使用情况


    def memoryManagerMXBeans = ManagementFactory.getMemoryManagerMXBeans()
    memoryManagerMXBeans.each {println(it.getMemoryPoolNames())}
    def memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans()
    memoryPoolMXBeans.each {println(it.getName())}


}

def getClassLoader() {
    def classLoadingMXBean = ManagementFactory.getClassLoadingMXBean()
    println(classLoadingMXBean.getLoadedClassCount())
    println(classLoadingMXBean.getTotalLoadedClassCount())
    println(classLoadingMXBean.getUnloadedClassCount())

}


def getRuntime() {
    def runtimeMXBean = ManagementFactory.getRuntimeMXBean()
    println(runtimeMXBean.getBootClassPath())
    println(runtimeMXBean.getClassPath())
    println(runtimeMXBean.getLibraryPath())
    println(runtimeMXBean.getName())// '进程id'+@+'主机名'  29243@mi
    println(String.format('%tF',runtimeMXBean.getStartTime())+'\u0020'+String.format('%tT',runtimeMXBean.getStartTime()))//启动时间点 ms 1675244177087
    println(runtimeMXBean.getUptime()+'ms')//运行时间 ms 1359
    runtimeMXBean.getSystemProperties().each { k, v ->


        switch (k) {
            case 'user.timezone':
                println("$k: $v")
                break
            case 'java.vm.name':
                println("$k: $v")
                break
            case 'java.version':
                println("$k: $v")
                break
            case 'java.io.tmpdir':
                println("$k: $v")
                break
            case 'java.class.path':
                println("$k: $v")
                break
            case 'java.home':
                println("$k: $v")
                break
            case 'os.name':
                println("$k: $v")
                break
            case 'os.version':
                println("$k: $v")
                break
            case 'os.arch':
                println("$k: $v")
                break
        }
//        if (k.startsWith('java'))
//            println("$k: $v")
//
//        if (k.startsWith('user'))
//            println("$k: $v")
//          if (k.startsWith('os'))
//            println("$k: $v")
    }
//    println(runtimeMXBean.getSystemProperties())
//    println(runtimeMXBean.getInputArguments())

}

def getIP() {
    def inetAddress = InetAddress.getLocalHost()
    // linux 获取结果为 127.0.0.1
    println(inetAddress.getHostAddress())
    println(inetAddress.getHostName())
    println(inetAddress.getCanonicalHostName())


    def networkInterfaces = NetworkInterface.getNetworkInterfaces()
    while (networkInterfaces.hasMoreElements()) {
        def networkInterface = networkInterfaces.nextElement()
        if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
            continue
        }
        println(networkInterface.getName())
        def inetAddresses = networkInterface.getInetAddresses()
        while (inetAddresses.hasMoreElements()) {
            def address = inetAddresses.nextElement()
            if(null != address && address instanceof Inet4Address){
                println(address.getHostAddress())
            }
        }
    }
}


def getFS(){
    def roots = File.listRoots()
    roots.each {
        println(it.getPath())
        if(it.getPath() == '/'){
            println(it.getTotalSpace()/1024/1024/1024)
            println(it.getTotalSpace()/1024/1024/1024-it.getFreeSpace()/1024/1024/1024)
            println(it.getUsableSpace()/1024/1024/1024)
            println(it.getFreeSpace()/1024/1024/1024)
        }

    }
}

getGC()
println()
getOS()
println()
getHeap()
println()
getClassLoader()
println()
getRuntime()
println()
getIP()
println()
getFS()

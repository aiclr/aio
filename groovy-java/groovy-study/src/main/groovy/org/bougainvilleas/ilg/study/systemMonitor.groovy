#!/usr/bin/env groovy
package org.bougainvilleas.ilg.study

import com.sun.management.OperatingSystemMXBean

import java.lang.management.ManagementFactory

long GB=(1024*1024*1024)
int MB=(1024*1024)
int KB=1024

OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean
def os_info=os.getName()+'\u0020'+os.getVersion()+'\u0020'+os.getArch()+'\u0020'+os.getAvailableProcessors()+'\u0020core(s)'
println(os_info)

def runtimeMXBean = ManagementFactory.getRuntimeMXBean()
def systemProperties = runtimeMXBean.getSystemProperties()
println(systemProperties.get("java.vm.name")+'\u0020"'+systemProperties.get("java.version")+'"')
println(systemProperties.get("java.runtime.name")+'\u0020(build\u0020'+systemProperties.get("java.runtime.version")+')');
println(systemProperties.get("java.vm.name")+'\u0020(build\u0020'+systemProperties.get("java.vm.version")+'\u0020'+systemProperties.get("java.vm.info")+')')
def java_runtime='start time:\u0020'+String.format('%tF',runtimeMXBean.getStartTime())+'\u0020'+String.format('%tT',runtimeMXBean.getStartTime())+'\u0020up time\u0020'+String.format('%.1f',runtimeMXBean.getUptime()/1000)+'second(s)'
println(java_runtime)


Map<String,String> ipMap=new HashMap<>()
def networkInterfaces = NetworkInterface.getNetworkInterfaces()
while (networkInterfaces.hasMoreElements()) {
    def networkInterface = networkInterfaces.nextElement()
    if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
        continue
    }
    def inetAddresses = networkInterface.getInetAddresses()
    while (inetAddresses.hasMoreElements()) {
        def address = inetAddresses.nextElement()
        if(null != address && address instanceof Inet4Address){
            ipMap.put(networkInterface.getName(),address.getHostAddress())
        }
    }
}
println(ipMap)

//cpu time 时钟速度为 3.2 GHz 的 CPU 每秒执行 32 亿个周期
def os_load='system load average\u0020'+String.format("%.2f%%", os.getSystemLoadAverage())+'\u0020system cpu load\u0020'+String.format("%.2f%%", os.getSystemCpuLoad())+'\u0020process cpu load\u0020'+String.format("%.2f%%", os.getProcessCpuLoad())+'\u0020process cpu time\u0020'+
        String.format("%.2f", os.getProcessCpuTime()/(10**9))+'GHz'
println(os_load)

def memory='memory total\u0020'+String.format("%.1f",os.getTotalPhysicalMemorySize()/GB)+'GB\u0020free\u0020'+String.format("%.1f",os.getFreePhysicalMemorySize()/GB)+'GB\u0020committed\u0020'+String.format("%.1f",os.getCommittedVirtualMemorySize()/GB)+'GB\u0020swap total\u0020'+String.format("%.1f",os.getTotalSwapSpaceSize()/GB)+'GB\u0020swap free\u0020'+String.format("%.1f",os.getFreeSwapSpaceSize()/GB)+'GB'
println(memory)

def roots = File.listRoots()
def FS='file system: total '+String.format('%.0f',roots[0].totalSpace/GB)+'GB\u0020free '+String.format('%.0f',roots[0].freeSpace/GB)+'GB'
println(FS)


def memoryMXBean = ManagementFactory.getMemoryMXBean()
def heap = memoryMXBean.getHeapMemoryUsage()
def nonHeap = memoryMXBean.getNonHeapMemoryUsage()
def java_heap='java heap: init\u0020'+String.format("%.0f", heap.getInit()/MB)+'MB\u0020max\u0020'+String.format("%.0f", heap.getMax()/MB)+'MB\u0020used\u0020'+String.format("%.0f", heap.getUsed()/MB)+'MB\u0020committed\u0020'+String.format("%.0f", heap.getCommitted()/MB)+'MB'
println(java_heap)
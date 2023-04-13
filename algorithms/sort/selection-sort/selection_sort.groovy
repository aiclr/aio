int[] selectionSort(int[] arr) {
    int len = arr.length
    for (int i = 0; i < len - 1; i++) {
        int minIndex = i
        for (int j = i + 1; j < len; j++) {
            if (arr[minIndex] > arr[j])
                minIndex = j
        }
        if (minIndex != i) {
            arr[minIndex] = arr[minIndex] ^ arr[i]
            arr[i] = arr[minIndex] ^ arr[i]
            arr[minIndex] = arr[minIndex] ^ arr[i]
            System.out.println(arr)
        }
    }
}

//创建 GroovyShell实例时，将当前脚本的 Binding 对象传递给它（每个脚本执行时都有一个Binding对象）
//被调用脚本就可以使用（读取和设置）发起调用脚本所知道的变量
// 更多信息请参考:
// ../../../groovy-java/groovy-study/src/main/groovy/org/bougainvilleas/ilg/study/chapter10/Script2a.groovy
shell = new GroovyShell(binding)

// groovy bubble_sort.groovy 10
// 获取命令行参数 10
length = Integer.valueOf(this.args[0])
// 获取 随机未排序数组
arr = shell.evaluate(new File("../commons/random.groovy"))
System.out.println(arr)
//返回arr 以供后续调用
selectionSort(arr)
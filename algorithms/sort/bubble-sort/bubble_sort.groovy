int[] bubbleSort(int[] arr) {
    boolean bool = true;
    for (int i = 1; i < arr.length && bool; i++) {
        bool = false;
        for (int j = 0; j < arr.length - i; j++) {
            if (arr[j] > arr[j + 1]) {
                arr[j] = arr[j] ^ arr[j + 1];
                arr[j + 1] = arr[j] ^ arr[j + 1];
                arr[j] = arr[j] ^ arr[j + 1];
                bool = true;
            }
        }
        System.out.println(arr);
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
//返回arr 以供后续调用
bubbleSort(arr)
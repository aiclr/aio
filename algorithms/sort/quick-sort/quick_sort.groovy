int[] quickSort(int[] arr,int start,int end) {
    int pivot = arr[end]
    int i=start,j=start
    while(j<end){
        if(arr[j]<pivot){
            if(i!=j){
                arr[i]=arr[i]^arr[j]
                arr[j]=arr[i]^arr[j]
                arr[i]=arr[i]^arr[j]
            }
            i++
        }
        j++
    }
    if(i!=j){
        arr[i]=arr[i]^arr[end]
        arr[end]=arr[i]^arr[end]
        arr[i]=arr[i]^arr[end]
    }
    System.out.println(arr)
    if(i>start+1)
        quickSort(arr,start,i-1)
    if(i<end-1)
        quickSort(arr,i+1,end)
}

//创建 GroovyShell实例时，将当前脚本的 Binding 对象传递给它（每个脚本执行时都有一个Binding对象）
//被调用脚本就可以使用（读取和设置）发起调用脚本所知道的变量
// 更多信息请参考:
// ../../../groovy-java/groovy-study/src/main/groovy/org/bougainvilleas/ilg/study/chapter10/Script2a.groovy
shell = new GroovyShell(binding)

// groovy quick_sort.groovy 10
// 获取命令行参数 10
length = Integer.valueOf(this.args[0])
// 获取 随机未排序数组
arr = shell.evaluate(new File("../commons/random.groovy"))
System.out.println(arr)
//返回arr 以供后续调用
quickSort(arr,0,length-1)
//高级插入排序 shell
int[] shellSort(int[] arr) {
    int length = arr.length;
    int j,temp;
    //gap 根据数组长度计算增量
    // 依据 gap 对待排序数组分组，
    // 0 1 2 3 4 5 6 7 8 9
    // 第一轮 分组 gap=5
    // 0-5 1-6 2-7 3-8 4-9
    // 第二轮 分组 gap=2
    // 0-2-4-6-8   1-3-5-7-9
    // 第三轮 分组 gap=1
    // 0-1-2-3-4-5-6-7-8-9
    // 每组都按插入排序进行排序
    for (int gap = length / 2; gap > 0; gap /= 2) {
        //结合插入排序思想
        for (int i=gap; i < length; i++) {
            if (arr[i] < arr[i - gap]) {
                j = i;
                temp = arr[i];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap]
                    j -= gap;
                }
                arr[j] = temp;
                System.out.println(arr)
            }
        }
    }
}

shell = new GroovyShell(binding)
// groovy shell_sort.groovy 10
// 获取命令行参数 10
length = Integer.valueOf(this.args[0])
// 获取 随机未排序数组
arr = shell.evaluate(new File("../commons/random.groovy"))
System.out.println(arr);
shellSort(arr)
// groovy shell_sort.groovy 10
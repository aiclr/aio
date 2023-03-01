int[] insertionSort(int[] arr) {
    int index;
    int temp;

    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > arr[i - 1])
            continue;

        temp = arr[i];
        index = i - 1;

        while (index >= 0 && temp < arr[index]) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[index + 1] = temp;
        System.out.println(arr)
    }
}

shell = new GroovyShell(binding)
// groovy insertion_sort.groovy 10
// 获取命令行参数 10
length = Integer.valueOf(this.args[0])
// 获取 随机未排序数组
arr = shell.evaluate(new File("../commons/random.groovy"))

insertionSort(arr)
// groovy insertion_sort.groovy 10
int[] mock = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]

void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                arr[j]=arr[j]^arr[j+1]
                arr[j+1]=arr[j]^arr[j+1]
                arr[j]=arr[j]^arr[j+1]
            }
        }
    }
    System.out.println(arr)
}

bubbleSort(mock)
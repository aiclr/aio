int[] randomArray(int length){
    Random r=new Random();
    int[] result=new int[length];
    for(int i=0;i<length;i++)
        result[i]=r.nextInt(length);
    return result;
}

void bubbleSort(int[] arr) {
    boolean bool=true;
    for (int i=1;i<arr.length && bool;i++) {
        bool=false;
        for (int j = 0; j < arr.length - i; j++) {
            if (arr[j] > arr[j + 1]) {
                arr[j]=arr[j]^arr[j+1];
                arr[j+1]=arr[j]^arr[j+1];
                arr[j]=arr[j]^arr[j+1];
                bool=true;
            }
        }
        System.out.println(arr);
    }
}

bubbleSort(randomArray(Integer.valueOf(this.args[0])))
// groovy bubble_sort.groovy 10

println();

def randomArrayGroovy(int length){
    def r=new Random()
    def result=new int[length]

    length.times {
        result[it] = r.nextInt(length) 
    }
        
    return result
}

def bubbleSortGroovy(int[] arr) {
    def bool=true
    for (def i=1;i<arr.length && bool;i++) {
        bool=false
        0.step(arr.length - i, 1) { j ->
            if (arr[j] > arr[j + 1]) {
                arr[j] = arr[j] ^ arr[j + 1]
                arr[j + 1] = arr[j] ^ arr[j + 1]
                arr[j] = arr[j] ^ arr[j + 1]
                bool = true
            }
        }
        println(arr)
    }
}

bubbleSortGroovy(randomArrayGroovy(Integer.valueOf(this.args[0])))
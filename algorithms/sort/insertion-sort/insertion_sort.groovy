int[] randomArray(int length){
    Random r=new Random();
    int[] result=new int[length];
    for(int i=0;i<length;i++)
        result[i]=r.nextInt(length);
    return result;
}

void insertionSort(int[] arr){
    int index;
    int temp;

    for(int i=1;i<arr.length;i++){
        if(arr[i]>arr[i-1])
            continue;
        
        temp=arr[i];
        index=i-1;
        
        while (index>=0 && temp<arr[index]){
            arr[index+1]=arr[index];
            index--;
        }
        arr[index+1]=temp;
        System.out.println(arr)
    }
}

insertionSort(randomArray(Integer.valueOf(this.args[0])))
// groovy insertion_sort.groovy 10

println();

def randomArrayGroovy(int length){
    def r=new Random()
    def result=new int[length]

    length.times {
        result[it] = r.nextInt(length) 
    }
        
    return result
}

def insertionSortGroovy(int[] arr) {
    def index;
    def temp;

    for(def i=1;i<arr.length;i++){
        if(arr[i]>arr[i-1])
            continue;
        
        temp=arr[i];
        index=i-1;
        
        while (index>=0 && temp<arr[index]){
            arr[index+1]=arr[index];
            index--;
        }
        arr[index+1]=temp;
        println(arr)
    }
}

insertionSortGroovy(randomArrayGroovy(Integer.valueOf(this.args[0])))
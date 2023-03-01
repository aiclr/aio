int[] randomArray(int length) {
    Random r = new Random();
    int[] result = new int[length];
    for (int i = 0; i < length; i++)
        result[i] = r.nextInt(length);
    return result;
}

// groovy 脚本 最后以后 默认为返回值
randomArray(length)
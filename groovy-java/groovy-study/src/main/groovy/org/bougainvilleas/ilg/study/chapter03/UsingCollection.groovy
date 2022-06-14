package org.bougainvilleas.ilg.study.chapter03

class UsingCollection{
    static void main(String[] args) {
        ArrayList<String> lst = new ArrayList<>()
        Collection<String> col=lst

        lst.add("a")
        lst.add("b")
        lst.add("c")
        //调用 ArrayList.remove(int var1)
        lst.remove(0);


        //groovy 跟 java不同 ,
        // groovy 先 找到Collection.remove(Object var1)
        // 参数类型不匹配
        // 再找到 ArrayList.remove(int var1)
        // 然后 调用被路由到 ArrayList.remove(int var1)
        col.remove(0);
        System.err.println(lst.size())
        System.err.println(col.size())
    }
}
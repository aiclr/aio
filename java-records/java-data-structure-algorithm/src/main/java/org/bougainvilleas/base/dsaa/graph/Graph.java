package org.bougainvilleas.base.dsaa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 图---多对多的关系
 * 有向图：顶点之间有方向，A到B A->B  不能B->A
 * 无向图：顶点之间链接是没有方向的 A到B,B也可到A
 * 带权图：也叫网，比如地图，城市之间的距离可以作为权，这样地图就是个带权图
 *
 * 图的表示方式两种：
 * 1.邻接矩阵（二维数组）
 *      1.1）邻接矩阵需要为每个顶点都分配n个边的空间，其实很多边可能都不存在，会造成空间的一定损失
 * 2.邻接表（HashTable），数组加链表、数组加树
 *      2.1）邻接表的实现只关心存在的边，不关心不存在的边，没有空间浪费，
 *      2.2）eg
 *         数据 数组  链表
 *          A   0    1-2
 *          B   1    0-2-3-4
 *          C   2    0-1
 *          D   3    1
 *          E   4    1
 *
 * 地铁网
 * 图的顶点 vertex可以有0个或多个相邻顶点
 * 顶点之间的链接是边 edge
 * 路径
 * eg 无向图
 *       B
 *   /   \   \
 * C    /  \   E
 *  \  /    \
 *   A       D
 * A到E的路径
 * A-B-E
 * A-C-B-E
 *

 *
 */
public class Graph
{
    //顶点，此处使用字符串
    private List<String> vertexList;
    //使用矩阵保存顶点之间关系
    private int [][] edges;
    //边总数
    private int numOfEdges;

    //遍历，记录某个点是否被访问
    private boolean isVisited[];

    public static void main(String[] args) {
        /**
         *       B
         *   /   \   \
         * C    /  \   E
         *  \  /    \
         *   A       D
         * 矩阵表示边如下
         *    A  B  C  D  E
         * A [0, 1, 1, 0, 0]
         * B [1, 0, 1, 1, 1]
         * C [1, 1, 0, 0, 0]
         * D [0, 1, 0, 0, 0]
         * E [0, 1, 0, 0, 0]
         *
         */
        String[] vertexs={"A","B","C","D","E"};
        Graph graph = new Graph(5);
        for (int i = 0; i < vertexs.length; i++) {
            graph.insertVertex(vertexs[i]);
        }
        //A-B
        graph.insertEdges(0,1,1);
        //A-C
        graph.insertEdges(0,2,1);
        //B-C
        graph.insertEdges(1,2,1);
        //B-D
        graph.insertEdges(1,3,1);
        //B-E
        graph.insertEdges(1,4,1);
        System.err.println("一共有"+ graph.getNumOfEdges()+"条边");
        System.err.println("一共有"+ graph.getVertexNum()+"个顶点");
        System.err.println("3表示的数据是"+ graph.getVertexByIndex(3));
        System.err.println(graph.getVertexByIndex(3)+"到"+graph.getVertexByIndex(1)+"的权值是"+graph.getWeight(3,1));
        graph.display();

        /**
         * 图的遍历
         * eg
         *       A
         *    /    \
         *   B     C
         *  /\    /\
         * D  E  F  G
         *  \/
         *  H
         */
        System.err.println("图的遍历");
        String[] vertexs2={"A","B","C","D","E","F","G","H"};
        Graph graph2 = new Graph(8);
        for (int i = 0; i < vertexs2.length; i++) {
            graph2.insertVertex(vertexs2[i]);
        }
        //A-B
        graph2.insertEdges(0,1,1);
        //A-C
        graph2.insertEdges(0,2,1);
        //B-D
        graph2.insertEdges(1,3,1);
        //B-E
        graph2.insertEdges(1,4,1);
        //C-F
        graph2.insertEdges(2,5,1);
        //C-G
        graph2.insertEdges(2,6,1);
        //D-H
        graph2.insertEdges(3,7,1);
        //D-H
        graph2.insertEdges(4,7,1);

        System.err.println("一共有"+ graph2.getNumOfEdges()+"条边");
        System.err.println("一共有"+ graph2.getVertexNum()+"个顶点");
        System.err.println("3表示的数据是"+ graph2.getVertexByIndex(3));
        System.err.println(graph2.getVertexByIndex(3)+"到"+graph2.getVertexByIndex(1)+"的权值是"+graph.getWeight(3,1));
        graph2.display();
//        graph2.dfs();
        graph2.bsf();


    }

    /**
     * 构造器初始化图
     * @param n 顶点个数
     */
    public Graph(int n) {
        //n*n的矩阵，二维数组
        edges=new int[n][n];
        //保存顶点数据
        vertexList=new ArrayList<>();
        //边的数量初始化
        numOfEdges=0;
        isVisited=new boolean[n];
    }

    /**
     * 保存顶点
     * @param vertex 顶点数据
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 记录顶点之间关系
     * @param v1 表示数据1 在vertexList内的下标 第几个数据
     * @param v2 表示数据2 在vertexList内的下标 第几个数据
     * @param weight 表示v1到v2的路径的权重
     *               比如v1=广州，v2=北京，weight可以用来保存广州到北京的距离
     */
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }

    /**
     * @return 得到边数
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * @return 得到顶点数
     */
    public int getVertexNum(){
        return vertexList.size();
    }

    /**
     * 根据下标获取数据
     * @param index 下标，第几个数据
     * @return 数据
     */
    public String getVertexByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 返回v1和v2的权值
     * @param v1 顶点1 的下标
     * @param v2 顶点2 的下标
     * @return v1与v2这条边的权值 如果为0则表示v1,v2之间没有边
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
//        return edges[v2][v1];
    }


    public void display(){
        for (int[] link:edges) {
            System.err.println(Arrays.toString(link));
        }
    }

//深度遍历 DFS Depth first search
//广度遍历 BFS Broad first search

    /**
     * 获取第一个邻接结点的下标
     * 遍历当前行，找到下一个邻接结点
     * @param index 矩阵某一行
     * @return 第一个邻接结点的下标, 否则返回-1
     */
    public int getFistNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            //如果当前结点的下一个结点存在（值大于0）
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点下标，获取下一个邻接结点
     * 遍历v1行，从v2+1列开始
     * @param v1 前一个邻接结点下标
     * @param v2 前一个邻接结点下标
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }


    /**
     * 深度优先:
     * 递归
     * 从A开始遍历，先输出A，isVisited[]记录A已遍历过
     * 然后找下一个相邻结点得B，然后输出B，isVisited[]记录B已遍历过
     * 然后找B的下一个相邻结点得A
     *      但是A已经遍历过（isVisited[]有记录），
     *      再找B的下下个相邻结点，即继续遍历B行，得D
     * 然后找D的下一个相邻结点，先得到已经输出过的B结点，再找的到H
     * 找H的相邻结点，D已输出，所以得到E
     * 找E的相邻结点B,H都输出过，然后回溯当前栈到D，再回溯到B,B会找到E已输出,B的相邻结点递归结束
     * 继续回溯到A,找A的另一个相邻结点得C
     *
     * 至于重载bsf方法加了个循环，是因为图不一定是连续的，如没有A结点，图变成独立的两部分
     *
     * A->B->D->H->E->C->F->G
     *       A
     *    /    \
     *   B     C
     *  /\    /\
     * D  E  F  G
     *  \/
     *  H
     * @param isVisited 记录是否被访问过
     * @param i 开始结点
     */
    public void dfs(boolean[] isVisited,int i){
        System.err.print(getVertexByIndex(i)+"->");
        isVisited[i]=true;
        int w = getFistNeighbor(i);
        while (w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            w=getNextNeighbor(i,w);
        }
    }

    /**
     * 重载
     */
    public void dfs(){
        //回溯，每个结点都深度遍历一遍 可能整个图不是连续的
        for (int i = 0; i < getVertexNum(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    /**
     * 广度优先 BroadFirstSearch
     * 一层一层遍历
     *
     *       A
     *    /    \
     *   B     C
     *  /\    /\
     * D  E  F  G
     *  \/
     *  H
     *  如从A开始，输出A并记录已访问
     *  将A加入一个队列（先进先出）
     *  队列非空继续执行，否则算法结束
     *  出队列得A,找A的第一个邻接点，B记录已访问并将B入队
     *          找A的第二个邻接点，C记录已访问并将C入队
     *  出队得B,D入队，E入队
     *  出队得C，F入队，G入队
     *  出队得D,H入队
     *  出队的E,出队的F,出队得G,出队得H
     *  队列空算法结束
     */
    private void bsf(boolean[] isVisited,int i){
        int u;//队列的头结点对应的下标
        int w;//邻接点下标
        Queue<Integer> queue = new ArrayBlockingQueue<>(getVertexNum()-1);
        System.err.printf("%s=>",getVertexByIndex(i));
        isVisited[i]=true;
        queue.add(i);
        while (!queue.isEmpty()){
           u = queue.poll();
           w=getFistNeighbor(u);
           while (w!=-1){
               if(!isVisited[w]){
                   System.err.printf("%s=>",getVertexByIndex(w));
                   isVisited[w]=true;
                   queue.add(w);
               }
               //同层的下一个结点
               w= getNextNeighbor(u, w);//广度

           }
        }
    }

    /**
     * 重载用for包一下
     */
    public void bsf(){
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                bsf(isVisited,i);
            }
        }
    }


}

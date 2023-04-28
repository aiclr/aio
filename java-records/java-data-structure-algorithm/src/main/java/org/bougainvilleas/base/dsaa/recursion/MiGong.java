package org.bougainvilleas.base.dsaa.recursion;

/**
 * 递归解决迷宫回溯的问题
 * <p>
 * 最短路径
 * <p>
 * 思路，定义所有走路策略，记录所有的2,2最少的则为最短路径
 * 共4*3*2*1=24种，不能上下×× 左右××开头，会stackOverFlowError ---死循环
 * 上（-1,0） 下（1,0），左（0,-1）右（0,+1）
 * 24种情况放到数组内，
 * <p>
 * <p>
 * 1墙，0路
 * 1 1 1 1 1 1 1
 * 1 0 0 0 0 0 1
 * 1 0 0 0 0 0 1
 * 1 1 1 0 0 0 1
 * 1 0 0 0 0 0 1
 * 1 0 0 0 0 0 1
 * 1 0 0 0 0 0 1
 * 1 1 1 1 1 1 1
 */
public class MiGong {
    public static void main(String[] args) {

        System.err.println(2 & 1);
        System.err.println(3 & 1);
        System.err.println(1 & 1);
        System.err.println(0 & 1);
        System.err.println(-1 & 1);
        System.err.println(-2 & 1);
        System.err.println("-----------");


        int[][] suanfa = getSuanfa();

        int[] result=new int[24];

        for (int i = 0; i < suanfa.length; i++) {
            int[][] map = initMap();
            for (int j = 0; j < 8; j++) {
                System.err.print(suanfa[i][j]+" ");
            }
            System.err.println();
            setWayAll(map,1,1,suanfa[i]);
            result[i]=show(map);
        }
        System.err.println();
//模拟回溯，多加几个墙
//        map[1][4] = 1;
//        map[2][4] = 1;
//        map[3][4] = 1;
//        map[4][4] = 1;
//        map[5][4] = 1;
//        map[5][3] = 1;

//        map[2][2]=1;
//        map[4][3]=1;
//        map[3][4]=1;
//        map[2][4]=1;

//        setWay(map,1,1);
//        setWay2(map, 1, 1);
    }

    public static boolean setWayAll(int[][] map, int i, int j,int[] x) {

        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                show(map);
                if (setWayAll(map, i + x[0], j+x[1],x)) {
                    return true;
                } else if (setWayAll(map,i + x[2], j+x[3],x)){
                    return true;
                } else if (setWayAll(map, i + x[4], j+x[5],x)){
                    return true;
                } else if (setWayAll(map, i + x[6], j+x[7],x)){
                    return true;
                } else {
                    map[i][j] = 3;
                    show(map);
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 上（-1,0） 下（1,0），左（0,-1）右（0,+1）
     */
    public static int[][] getSuanfa() {
        int[] up = {-1, 0};//0
        int[] down = {1, 0};//1
        int[] left = {0, -1};//2
        int[] right = {0, 1};//3

        int[][] suanfa = new int[24][8];

        for (int j = 0; j < 24; j++) {
            //
            suanfa[j][0] = getX(j / 6);
            suanfa[j][1] = getY(j / 6);
            if (j / 6 == 0) {
                suanfa[j][2] = getX(j / 6 + j / 2 + 1);
                suanfa[j][3] = getY(j / 6 + j / 2 + 1);
            }
            //0-1
            if (j / 2 == 0) {
                //根据奇数偶数判断,使用&位操作，性能更优秀  &按位与运算   0010  & 0001 = 0001
                //相同位数都是1,则该位为1,二进制
                //1
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = left[0];
                    suanfa[j][5] = left[1];
                    suanfa[j][6] = right[0];
                    suanfa[j][7] = right[1];
                } else {//0
                    suanfa[j][4] = right[0];
                    suanfa[j][5] = right[1];
                    suanfa[j][6] = left[0];
                    suanfa[j][7] = left[1];
                }
            }
            //2-3
            if (j / 2 == 1) {
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = down[0];
                    suanfa[j][5] = down[1];
                    suanfa[j][6] = right[0];
                    suanfa[j][7] = right[1];
                } else {
                    suanfa[j][4] = right[0];
                    suanfa[j][5] = right[1];
                    suanfa[j][6] = down[0];
                    suanfa[j][7] = down[1];
                }
            }
            //4-5
            if (j / 2 == 2) {
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = down[0];
                    suanfa[j][5] = down[1];
                    suanfa[j][6] = left[0];
                    suanfa[j][7] = left[1];
                } else {
                    suanfa[j][4] = left[0];
                    suanfa[j][5] = left[1];
                    suanfa[j][6] = down[0];
                    suanfa[j][7] = down[1];
                }
            }

            if (j / 2 == 3) {
                suanfa[j][2] = up[0];
                suanfa[j][3] = up[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = left[0];
                    suanfa[j][5] = left[1];
                    suanfa[j][6] = right[0];
                    suanfa[j][7] = right[1];
                } else {
                    suanfa[j][4] = right[0];
                    suanfa[j][5] = right[1];
                    suanfa[j][6] = left[0];
                    suanfa[j][7] = left[1];
                }
            }
            if (j / 2 == 4) {
                suanfa[j][2] = left[0];
                suanfa[j][3] = left[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = up[0];
                    suanfa[j][5] = up[1];
                    suanfa[j][6] = right[0];
                    suanfa[j][7] = right[1];
                } else {
                    suanfa[j][4] = right[0];
                    suanfa[j][5] = right[1];
                    suanfa[j][6] = up[0];
                    suanfa[j][7] = up[1];
                }
            }
            if (j / 2 == 5) {
                suanfa[j][2] = right[0];
                suanfa[j][3] = right[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = up[0];
                    suanfa[j][5] = up[1];
                    suanfa[j][6] = left[0];
                    suanfa[j][7] = left[1];
                } else {
                    suanfa[j][4] = left[0];
                    suanfa[j][5] = left[1];
                    suanfa[j][6] = up[0];
                    suanfa[j][7] = up[1];
                }
            }
            if (j / 2 == 6) {
                suanfa[j][2] = up[0];
                suanfa[j][3] = up[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = down[0];
                    suanfa[j][5] = down[1];
                    suanfa[j][6] = right[0];
                    suanfa[j][7] = right[1];
                } else {
                    suanfa[j][4] = right[0];
                    suanfa[j][5] = right[1];
                    suanfa[j][6] = down[0];
                    suanfa[j][7] = down[1];
                }
            }
            if (j / 2 == 7) {
                suanfa[j][2] = down[0];
                suanfa[j][3] = down[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = up[0];
                    suanfa[j][5] = up[1];
                    suanfa[j][6] = right[0];
                    suanfa[j][7] = right[1];
                } else {
                    suanfa[j][4] = right[0];
                    suanfa[j][5] = right[1];
                    suanfa[j][6] = up[0];
                    suanfa[j][7] = up[1];
                }
            }
            if (j / 2 == 8) {
                suanfa[j][2] = right[0];
                suanfa[j][3] = right[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = up[0];
                    suanfa[j][5] = up[1];
                    suanfa[j][6] = down[0];
                    suanfa[j][7] = down[1];
                } else {
                    suanfa[j][4] = down[0];
                    suanfa[j][5] = down[1];
                    suanfa[j][6] = up[0];
                    suanfa[j][7] = up[1];
                }
            }
            if (j / 2 == 9) {
                suanfa[j][2] = up[0];
                suanfa[j][3] = up[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = left[0];
                    suanfa[j][5] = left[1];
                    suanfa[j][6] = down[0];
                    suanfa[j][7] = down[1];
                } else {
                    suanfa[j][4] = down[0];
                    suanfa[j][5] = down[1];
                    suanfa[j][6] = left[0];
                    suanfa[j][7] = left[1];
                }
            }
            if (j / 2 == 10) {
                suanfa[j][2] = down[0];
                suanfa[j][3] = down[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = up[0];
                    suanfa[j][5] = up[1];
                    suanfa[j][6] = left[0];
                    suanfa[j][7] = left[1];
                } else {
                    suanfa[j][4] = left[0];
                    suanfa[j][5] = left[1];
                    suanfa[j][6] = up[0];
                    suanfa[j][7] = up[1];
                }
            }
            if (j / 2 == 11) {
                suanfa[j][2] = right[0];
                suanfa[j][3] = right[1];
                if ((j & 1) == 1) {//如果为奇数
                    suanfa[j][4] = up[0];
                    suanfa[j][5] = up[1];
                    suanfa[j][6] = down[0];
                    suanfa[j][7] = down[1];
                } else {
                    suanfa[j][4] = down[0];
                    suanfa[j][5] = down[1];
                    suanfa[j][6] = up[0];
                    suanfa[j][7] = up[1];
                }
            }
        }
//        show(suanfa);
        return suanfa;

    }

    public static int getX(int i) {
        int[] up = {-1, 0};
        int[] down = {1, 0};
        int[] left = {0, -1};
        int[] right = {0, 1};
        switch (i) {
            case 0:
                return up[0];
            case 1:
                return down[0];
            case 2:
                return left[0];
            case 3:
                return right[0];
            default:
                return 0;
        }
    }

    public static int getY(int i) {
        int[] up = {-1, 0};
        int[] down = {1, 0};
        int[] left = {0, -1};
        int[] right = {0, 1};
        switch (i) {
            case 0:
                return up[1];
            case 1:
                return down[1];
            case 2:
                return left[1];
            case 3:
                return right[1];
            default:
                return 0;
        }
    }


    public static int[][] initMap() {
        int[][] map = new int[8][7];
        //第一行，最后一行都是1,表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //第一列，最后一列都是1,表示墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        show(map);
        return map;
    }

    public static int show(int[][] map) {
        int a = map.length;
        int b = map[0].length;
        int num = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (map[i][j] == 2) {
                    num++;
                }
                System.err.print(map[i][j] + " ");
            }
            System.err.println();
        }
        return num;
    }


    /**
     * 递归回溯来给小球找路
     * 如果小球能到map[6][5]则说明通路找到
     * 当map[i][j] 为0表示该点没有走过，当为1表示墙，2表示通路可以走，3表示该点已经走过但是走不通
     * 走路策略，先判断下是否通，不通则判断右是否通，依次判断上，左，如果该点走不通则回溯
     * 不同策略，路径不同
     *
     * @param map 地图
     * @param i,j 初始位置
     *            1 1 1 1 1 1 1
     *            1 2 0 0 0 0 1
     *            1 2 2 2 0 0 1
     *            1 1 1 2 0 0 1
     *            1 0 0 2 0 0 1
     *            1 0 0 2 0 0 1
     *            1 0 0 2 2 2 1
     *            1 1 1 1 1 1 1
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {//下
                    return true;
                } else if (setWay(map, i, j + 1)) {//右
                    return true;
                } else if (setWay(map, i - 1, j)) {//上
                    return true;
                } else if (setWay(map, i, j - 1)) {//左
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 1 1 1 1 1 1 1
     * 1 2 2 2 2 2 1
     * 1 0 0 0 0 2 1
     * 1 1 1 0 0 2 1
     * 1 0 0 0 0 2 1
     * 1 0 0 0 0 2 1
     * 1 0 0 0 0 2 1
     * 1 1 1 1 1 1 1
     *
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay2(map, i, j + 1)) {//右
                    return true;
                } else if (setWay2(map, i + 1, j)) {//下
                    return true;
                } else if (setWay2(map, i - 1, j)) {//上
                    return true;
                } else if (setWay2(map, i, j - 1)) {//左
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}

package org.bougainvilleas.base.dsaa.algorithm;


/**
 * KruskalCase algorithm
 * 克鲁斯卡尔算法
 *
 * 修路问题本质是求最小生成树问题
 * Minimum Cost Spanning Tree
 * 给定一个带权无向连通图，如何选取一个生成树，使树上所有边上的权总和为最小，这叫做最小生成树
 *
 * n个顶点，n-1个边
 * 普里母算法和克鲁斯卡尔算法 都是求最小生成树的算法
 *
 * 思路，先找最小权两个点，在找次小权的两个点
 * 注意几个点不能构成一个回路
 * 回路
 * A-B-C-D-E
 * 如果出现了最小权是：A-C，A-D，A-F，C-F都不能选，因为会构成回路
 *
 * 终点不唯一
 *  A的终点是E
 *  B的终点是E
 *  C的终点是E
 *  D的终点是E
 *  E的终点是E
 *
 * 加入的边的两个顶点不能都是同一个终点，否则将构成回路
 *
 */
public class KruskalCase {
}

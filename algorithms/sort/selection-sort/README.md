# Selection Sort

> Problem Statement
> > Given an unsorted array of **n** elements, write a function to sort the array
>
> Approach
> - select the smallest element from the array
> - put it at the beginning of the array
> - then select the smallest array from the remaining<sub>剩余的</sub> unsorted list
> - append it to the sorted array at the beginning
> - keep doing this for every element of the array
> - repeat the above process n times
>
> Time Complexity
> > O(n^2) Worst case performance \
> > O(n^2) Best-case performance \
> > O(n^2) Average performance
>
> Space Complexity
> > O(1) Worst case

## swap

> 异或运算： 不引入第三个变量交换位置

```text
u = 3
v = 2
二进制异或过程如下
u = u ^ v = 11 ^ 10 = 01
v = u ^ v = 01 ^ 10 = 11 = 3
u = u ^ v = 01 ^ 11 = 10 = 2

```

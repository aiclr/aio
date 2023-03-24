# Quick Sort

> Tony Hoare in 1959 found **Quick Sort**.
>
> Problem Statement
> > Given an unsorted array of **n** elements, write a function to sort the array
>
> Approach
> - Make the right-most<sub>最右</sub> index value pivot<sub>核心;枢轴</sub>
> - partition the array using pivot value
> - quicksort left partition recursively<sub>递归地</sub>
> - quicksort right partition recursively
>
> Time Complexity
> > O(n^2) Worst case performance \
> > O(n log n) Best-case performance \
> > O(n log n) Average performance
>
> Space Complexity
> > O(logn) Worst case

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

# Bubble Sort

> The term **Bubble Sort** was first used by ***Iverson, K*** in 1962. \
> 
> Problem Statement
> > Given an unsorted array of **n** elements, write a function to sort the array
> 
> Approach
> 1. select the first element of the array
> 2. compare it with its next element
> 3. if it is larger than the next element then swap them
> 4. else do nothing
> 5. keep doing this for every index of the array
> 6. repeat the above process **n** times.
> 
> Time Complexity
> > O(n^2) Worst case performance \
> > O(n) Best-case performance \
> > O(n^2) Average performance
> 
> Space Complexity
> > O(1) Worst case

## swap

> 异或运算： 不引入第三个变量交换位置

```
u = 3
v = 2
二进制异或过程如下
u = u ^ v = 11 ^ 10 = 01
v = u ^ v = 01 ^ 10 = 11 = 3
u = u ^ v = 01 ^ 11 = 10 = 2

```
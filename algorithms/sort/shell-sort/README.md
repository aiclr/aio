# Shell Sort

> `Donald Shell` found \
> Problem Statement
> > Given an array of **n** elements, write a function to sort the array in increasing order.
> 
> Approach
> 1. start with the initial gap, g `g=length/2`
> 2. go through the first (n - g) elements in the array
> 3. compare the element with the next element that is g distance away
> 4. swap the two elements if the first element is bigger
> 5. decrease the gap and repeat until gap = 1 `gap/=2`
> 
> Time Complexity \
> Time complexity is dependent on the gap sequences. Below time complexities are based on the gap sequences of `n/2^k`
> > O(n^2) Worst case performance \
> > O(n) Best-case performance \
> > O(n^2) Average performance
> 
> Space Complexity
> > O(1) Worst case
>
> 高级插入排序

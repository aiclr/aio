# Insertion Sort

> Problem Statement
> > Given an array of **n** elements, write a function to sort the array in increasing order.
> 
> Approach
> 1. Define a "key" index, the subarray to the left of which is sorted.
> 2. Initiate "key" as 1, ie. the second element of array(as there is only one element to left of the second element, which can be considered as sorted array with one element).
> 3. If value of element at (key - 1) position is less than value of element at (key) position; increment "key".
> 4. Else move elements of sorted subarray that are greater than value of element at "key" to one position ahead of their current position. Put the value of element at "key" in the newly created void.
> > 先挑选出待插入数据项的索引 index 和内容 temp，然后依次与有序数组比较 \
> > 目的是找到插入位置，将 temp 放到插入位置。\
> > 过程中依次移动比 temp 大的元素，为 temp 腾出位置
> 
> Time Complexity
> > О(n^2) comparisons, О(n^2) swaps -- Worst Case \
> > O(n) comparisons, O(1) swaps -- Best Case 
> 
> Space Complexity
> > O(1) -- (No extra space needed, sorting done in place)

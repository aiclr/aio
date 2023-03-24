package main

import (
	"fmt"
	"os"
	"strconv"

	"bougainvilleas.org/commons"
)

// go run quick_sort.go 10
func main() {
	length, err := strconv.Atoi(os.Args[1])
	if err != nil {
		panic(err)
	}
	mock := commons.RandomArray(length)
	// mock := [5]int{1, 2, 3, 4, 5}
	fmt.Println(mock)
	quick(mock, 0, length-1)

}

func quick(arr []int, start, end int) {

	pivot := arr[end]
	i := start
	j := start
	for j < end {
		if arr[j] < pivot {
			if i != j {
				commons.Swap(&arr[i], &arr[j])
			}
			i++
		}
		j++
		fmt.Println(arr)
	}
	if i != j {
		commons.Swap(&arr[i], &arr[end])
	}
	fmt.Println(arr)
	if i > start+1 {
		quick(arr, start, i-1)
	}
	if i < end-1 {
		quick(arr, i+1, end)
	}
}

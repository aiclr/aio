package main

import (
	"fmt"
	"os"
	"strconv"

	"bougainvilleas.org/commons"
)

// go run bubble_sort.go 10
func main() {

	length, err := strconv.Atoi(os.Args[1])
	if err != nil {
		panic(err)
	}
	mock := commons.RandomArray(length)
	bubble(mock, length)
}

func bubble(arr []int, length int) {
	// 已有序则快速结束
	sorted := true
	for i := 1; i < length && sorted; i++ {
		sorted = false
		for j := 0; j < length-i; j++ {
			if arr[j] > arr[j+1] {
				commons.Swap(&arr[j], &arr[j+1])
				sorted = true
			}
		}
		fmt.Println(arr)
	}
}

package main

import (
	"fmt"
	"os"
	"strconv"

	"bougainvilleas.org/commons"
)

func selection(arr []int, length int) {
	for i := 0; i < length-1; i++ {
		minIndex := i
		for j := i + 1; j < length; j++ {
			if arr[minIndex] > arr[j] {
				minIndex = j
			}
		}
		if minIndex != i {
			commons.Swap(&arr[minIndex], &arr[i])
			fmt.Println(arr)
		}
	}
}

func main() {
	length, err := strconv.Atoi(os.Args[1])
	if err != nil {
		panic(err)
	}
	mock := commons.RandomArray(length)
	selection(mock, length)
}

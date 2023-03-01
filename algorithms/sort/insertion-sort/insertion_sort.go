package main

import (
	"fmt"
	"os"
	"strconv"

	"bougainvilleas.org/commons"
)

func main() {
	length, err := strconv.Atoi(os.Args[1])
	if err != nil {
		panic(err)
	}
	mock := commons.RandomArray(length)
	insertion(mock, length)
}

func insertion(arr []int, length int) {
	var index int
	var temp int
	for i := 1; i < length; i++ {
		if arr[i] > arr[i-1] {
			continue
		}
		temp = arr[i]
		index = i - 1
		for index >= 0 && temp < arr[index] {
			arr[index+1] = arr[index]
			index--
		}
		arr[index+1] = temp
		fmt.Println(arr)
	}
}

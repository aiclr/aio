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
	fmt.Println(mock)
	shell(mock, length)
}

func shell(arr []int, length int) {
	var j, temp int
	for gap := length / 2; gap > 0; gap /= 2 {
		for i := gap; i < length; i++ {
			if arr[i] < arr[i-gap] {
				j = i
				temp = arr[i]
				for j-gap >= 0 && temp < arr[j-gap] {
					arr[j] = arr[j-gap]
					j -= gap
				}
				arr[j] = temp
				fmt.Println(arr)
			}

		}
	}
}

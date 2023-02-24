package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

// go run bubble_sort.go 10
func main() {

	length, err := strconv.Atoi(os.Args[1])
	if err != nil {
		panic(err)
	}
	mock := randomArray(length)
	bubble(mock, length)
}

// 异或运算 交换位置
func swap(first *int, next *int) {
	*first = *first ^ *next
	*next = *first ^ *next
	*first = *first ^ *next
}

func randomArray(length int) []int {
	// 设置随机数种子
	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)

	result := make([]int, length)

	for i := 0; i < length; i++ {
		result[i] = r1.Intn(length)
	}
	return result
}

func bubble(arr []int, length int) {
	// 已有序则快速结束
	sorted := true
	for i := 1; i < length && sorted; i++ {
		sorted = false
		for j := 0; j < length-i; j++ {
			if arr[j] > arr[j+1] {
				swap(&arr[j], &arr[j+1])
				sorted = true
			}
		}
		fmt.Println(arr)
	}
}

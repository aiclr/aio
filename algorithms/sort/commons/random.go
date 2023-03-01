package commons

import (
	"math/rand"
	"time"
)

// 异或运算 交换位置
func Swap(first *int, next *int) {
	*first = *first ^ *next
	*next = *first ^ *next
	*first = *first ^ *next
}

func RandomArray(length int) []int {
	// 设置随机数种子
	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)
	result := make([]int, length)
	for i := 0; i < length; i++ {
		result[i] = r1.Intn(length)
	}
	return result
}

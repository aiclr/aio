package main

import "fmt"

func main() {
	mock := [10]int{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
	length := len(mock)
	for i := 0; i < length; i++ {
		for j := 0; j < length-1-i; j++ {
			if mock[j] > mock[j+1] {
				mock[j] = mock[j] ^ mock[j+1]
				mock[j+1] = mock[j] ^ mock[j+1]
				mock[j] = mock[j] ^ mock[j+1]
			}
		}
	}
	fmt.Println(mock)
}

package main

import (
	"fmt"
	"time"
)

// time go run select.go
// received one
// received: two
// go run select.go  0.17s user 0.10s system 12% cpu 2.158 total
// total <3s 并发执行 总时间少于三秒
func main() {
	c1 := make(chan string)
	c2 := make(chan string)
	go func() {
		time.Sleep(1 * time.Second)
		c1 <- "one"
	}()
	go func() {
		time.Sleep(2 * time.Second)
		c2 <- "two"
	}()
	for i := 0; i < 2; i++ {
		select {
		case msg1 := <-c1:
			fmt.Println("received", msg1)
		case msg2 := <-c2:
			fmt.Println("received:", msg2)
		}
	}
}

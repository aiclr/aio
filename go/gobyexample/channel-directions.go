package main

import "fmt"

// pings chan<- string 表明主要用于往pings写入数据
func ping(pings chan<- string, msg string) {
	pings <- msg
}

// pings <-chan string 表明主要用于从pings接受数据
// pongs chan<- string 表明主要用于往pongs写入数据
func pong(pings <-chan string, pongs chan<- string) {
	msg := <-pings
	pongs <- msg
}
func main() {
	pings := make(chan string, 1)
	pongs := make(chan string, 1)
	ping(pings, "passed message")
	pong(pings, pongs)
	fmt.Println(<-pongs)
}

package main

import (
	"fmt"
	"time"
)

func worker(done chan bool) {
	fmt.Print("working...")
	time.Sleep(time.Second)
	fmt.Println("done")
	done <- true
}
func main() {
	done := make(chan bool, 1)
	go worker(done)
	//If you removed the `<- done` line from this program, the program would exit before the worker even started
	<-done //阻塞 等待 goroutine 完成

	//if <-done {
	//	fmt.Println("finished")
	//}

}

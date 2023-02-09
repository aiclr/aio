package main

import (
	"fmt"
	"time"
)

func main() {
	jobs := make(chan int, 5)
	done := make(chan bool)

	go func() {
		for {
			//the more value will be false if jobs has been closed
			j, more := <-jobs
			if more {
				fmt.Println("received job", j)
			} else {
				fmt.Println("received all jobs")
				done <- true
				return
			}

		}
	}()

	for j := 1; j <= 3; j++ {
		jobs <- j
		fmt.Println("sent job", j)
		//增加延迟，增强并发的实验效果
		time.Sleep(time.Millisecond)
	}
	close(jobs)
	fmt.Println("sent all jobs")
	//If you removed the `<- done` line from this program, the program would exit before the worker even started
	//阻塞 等待 goroutine 完成
	<-done
}

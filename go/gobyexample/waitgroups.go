package main

import (
	"fmt"
	"sync"
	"time"
)

// This is the function we’ll run in every goroutine.
func worker(id int) {
	fmt.Printf("Worker %d starting\n", id)
	//Sleep to simulate(模拟) an expensive(昂贵的) task
	time.Sleep(time.Second)
	fmt.Printf("Worker %d done\n", id)
}

func main() {
	//This WaitGroup is used to wait for all the goroutines launched here to finish.
	//Note: if a WaitGroup is explicitly(显式地) passed into functions, it should be done by pointer
	var wg sync.WaitGroup
	//Launch several goroutines and increment the WaitGroup counter for each
	for i := 1; i <= 5; i++ {
		wg.Add(1)

		//Avoid(避免) re-use of the same i value in each goroutine closure.
		i := i

		//Wrap(包装) the worker call in a closure(闭包) that makes sure to tell the WaitGroup that this worker is done.
		//This way the worker itself does not have to be aware(知道) of the concurrency(并发) primitives(原语) involved in its execution.
		// 工作程序本身不必知道其执行中涉及的并发原语
		go func() {
			// defer 一般用于资源的释放和异常的捕捉
			// 跟在defer后面的语言将会在程序进行最后的return之后再执行
			// 在defer归属的函数即将返回时，将延迟处理的语句按defer的逆序进行执行
			// 先被defer的语句最后被执行，最后被defer的语句，最先被执行 类似 stack 结构 FILO 先进后出
			//源码：
			//func (wg *WaitGroup) Done() {
			//	wg.Add(-1)
			//}
			defer wg.Done()
			worker(i)
		}()
	}
	//Block until the WaitGroup counter goes back to 0; all the workers notified they’re done
	wg.Wait()
}

//The order of workers starting up and finishing is likely to be different for each invocation.
//Note that this approach has no straightforward way to propagate errors from workers. For more advanced use cases, consider using the errgroup package

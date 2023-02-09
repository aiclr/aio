package main

import (
	"fmt"
	"math/rand"
	"sync/atomic"
	"time"
)

// In the previous example we used explicit(显式的) locking with mutexes(互斥锁) to synchronize access to shared state across multiple goroutines.
// Another option is to use the built-in(内置的) synchronization features(特性) of goroutines and channels to achieve(实现) the same result.
// This channel-based(基于channel) approach(方法) aligns(使一致) with Go’s ideas of sharing memory by communicating and having each piece of data owned by exactly(确切地) 1 goroutine

// In this example our state will be owned by a single goroutine.
// This will guarantee(确保) that the data is never corrupted(引起错误) with concurrent(并发) access.
// In order to read or write that state, other goroutines will send messages to the owning goroutine and receive corresponding(相应的) replies(回复).
// These readOp and writeOp structs encapsulate(封装) those requests and a way for the owning goroutine to respond.
type readOp struct {
	key  int
	resp chan int
}
type writeOp struct {
	key  int
	val  int
	resp chan bool
}

func main() {

	//As before we’ll count(计算) how many operations(操作数) we perform(执行).
	var readOps uint64
	var writeOps uint64
	//The reads and writes channels will be used by other goroutines to issue(发出) read and write requests, respectively(各自).
	// 利用 channel block 机制
	reads := make(chan readOp)
	writes := make(chan writeOp)

	//Here is the goroutine that owns the state, which is a map as in the previous example but now private to the stateful goroutine.
	//This goroutine repeatedly selects on the reads and writes channels, responding to requests as they arrive.
	//A response is executed by first performing(执行) the requested operation
	//and then sending a value on the response channel resp to indicate(表明) success (and the desired value in the case of reads).
	go func() {
		var state = make(map[int]int)
		// 当main线程结束时，goroutines 即使未执行完毕也会退出
		for {
			select {
			case read := <-reads:
				read.resp <- state[read.key]
			case write := <-writes:
				state[write.key] = write.val
				write.resp <- true
			}
		}
	}()
	// 100 个 goroutines 每个 goroutines 停顿 1ms 主程序运行 1000ms
	// 100 * 1000 =100 000
	//This starts 100 goroutines to issue reads to the state-owning goroutine via the reads channel.
	//Each read requires constructing a readOp, sending it over the reads channel, and then receiving the result over the provided resp channel
	for r := 0; r < 100; r++ {
		go func() {
			// 当main线程结束时，goroutines 即使未执行完毕也会退出
			for {
				read := readOp{
					// func Intn(n int) int { return globalRand.Intn(n) }
					//返回[0,5)的随机数
					key:  rand.Intn(5),
					resp: make(chan int)}
				reads <- read
				//阻塞 等待 持有 state 的 goroutine 写入 channel 完毕
				<-read.resp
				atomic.AddUint64(&readOps, 1)
				time.Sleep(time.Millisecond)
			}
		}()
	}
	// 10 个 goroutines 每个 goroutines 停顿 1ms 主程序运行 1000ms
	// 10 * 1000 =10 000
	//We start 10 writes as well, using a similar(相似的) approach(方法).
	for w := 0; w < 10; w++ {
		go func() {
			// 当main线程结束时，goroutines 即使未执行完毕也会退出
			for {
				write := writeOp{
					key:  rand.Intn(5),
					val:  rand.Intn(100),
					resp: make(chan bool)}
				writes <- write
				//阻塞 等待 持有 state 的 goroutine 完成
				<-write.resp
				atomic.AddUint64(&writeOps, 1)
				time.Sleep(time.Millisecond)
			}
		}()
	}

	time.Sleep(time.Second)
	//Finally, capture and report the op counts
	readOpsFinal := atomic.LoadUint64(&readOps)
	fmt.Println("readOps:", readOpsFinal)
	writeOpsFinal := atomic.LoadUint64(&writeOps)
	fmt.Println("writeOps:", writeOpsFinal)

	// channel 协程安全 每次只有一个 goroutine 访问channel
	c1 := make(chan int)

	for i := 0; i < 5; i++ {
		go func() {
			val := rand.Intn(5)
			fmt.Println("+++", val)
			c1 <- val
			time.Sleep(time.Millisecond)
		}()
	}
	go func() {
		for {
			select {
			case b1 := <-c1:
				fmt.Println(b1)
				time.Sleep(time.Millisecond)
			}
		}
	}()
	time.Sleep(10 * time.Millisecond)

}

//Running our program shows that the goroutine-based state management example completes about 80,000 total operations
//For this particular(特别的) case the goroutine-based approach(方法) was a bit more involved(复杂的) than the mutex-based one.
//It might be useful in certain(某种) cases though, for example where you have other channels involved or when managing multiple such mutexes would be error-prone(易于出错的).
//You should use whichever approach feels most natural, especially with respect to(关于) understanding the correctness(正确性) of your program

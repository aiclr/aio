package main

import (
	"fmt"
	"time"
)

// Rate limiting is an important mechanism(机制) for controlling resource utilization(利用) and maintaining(维持) quality of service.
// Go elegantly(优雅地) supports rate limiting with goroutines, channels, and tickers.
func main() {
	//First we’ll look at basic rate limiting.
	//Suppose we want to limit our handling of incoming requests.
	//We’ll serve(提供) these requests off a channel of the same name
	requests := make(chan int, 5)
	for i := 1; i <= 5; i++ {
		requests <- i
	}
	close(requests)
	//This limiter channel will receive a value every 200 milliseconds.
	//This is the regulator(监管者) in our rate limiting scheme(方案)
	limiter := time.Tick(200 * time.Millisecond)

	for req := range requests {
		////By blocking on a receive from the limiter channel before serving each request, we limit ourselves to 1 request every 200 milliseconds.
		<-limiter
		fmt.Println("request", req, time.Now())
	}

	//We may want to allow short bursts(爆满) of requests in our rate limiting scheme while preserving(维持) the overall(总体) rate limit.
	//We can accomplish(实现) this by buffering our limiter channel.
	//This burstyLimiter channel will allow bursts of up to 3 events
	burstyLimiter := make(chan time.Time, 3)
	//Fill up the channel to represent(表示) allowed bursting.
	for i := 0; i < 3; i++ {
		burstyLimiter <- time.Now()
	}

	go func() {
		//Every 200 milliseconds we’ll try to add a new value to burstyLimiter, up to its limit of 3.
		for t := range time.Tick(200 * time.Millisecond) {
			burstyLimiter <- t
		}
	}()

	burstyRequests := make(chan int, 5)
	//Now simulate(模拟) 5 more incoming requests.
	for i := 1; i <= 5; i++ {
		burstyRequests <- i
	}
	close(burstyRequests)
	//The first 3 of these will benefit(得益于) from the burst(爆满) capability(能力) of burstyLimiter
	for req := range burstyRequests {
		<-burstyLimiter
		fmt.Println("request", req, time.Now())
	}
}

//Running our program we see the first batch of requests handled once every ~200 milliseconds as desired.
//For the second batch of requests we serve the first 3 immediately because of the burstable rate limiting,
//then serve the remaining 2 with ~200ms delays each.

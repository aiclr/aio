package main

import (
	"fmt"
	"time"
)

func main() {
	//Timers represent a single event in the future.
	//You tell the timer how long you want to wait, and it provides a channel that will be notified at that time.
	//This timer will wait 2 seconds.
	timer1 := time.NewTimer(2 * time.Second)
	//The <-timer1.C blocks on the timer’s channel C until it sends a value indicating(表明) that the timer fired.
	<-timer1.C
	fmt.Println("Timer1 fired")

	timer2 := time.NewTimer(time.Second)
	go func() {
		<-timer2.C
		fmt.Println("Timer2 fired")
	}()
	//you can cancel the timer before it fires.
	stop2 := timer2.Stop()
	if stop2 {
		fmt.Println("Timer2 stopped")
	}
	//The first timer will fire ~2s after we start the program,
	// but the second timer should be stopped before it has a chance to fire.
	time.Sleep(2 * time.Second)
}

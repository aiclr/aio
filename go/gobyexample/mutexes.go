package main

import (
	"fmt"
	"sync"
)

// Container holds a map of counters;
// since we want to update it concurrently(同时) from multiple goroutines, we add a Mutex to synchronize access.
// Note that mutexes(互斥器) must not be copied, so if this struct is passed(传递) around, it should be done by pointer.
type Container struct {
	mu       sync.Mutex
	counters map[string]int
}

// method
func (c *Container) inc(name string) {
	//Lock the mutex before accessing counters;
	c.mu.Lock()
	//unlock it at the end of the function using a defer statement.
	defer c.mu.Unlock()
	c.counters[name]++
}

func main() {
	c := Container{
		//Note that the zero value of a mutex is usable as-is, so no initialization(初始化) is required here.
		counters: map[string]int{"a": 0, "b": 0},
	}

	var wg sync.WaitGroup

	//This function increments(增加) a named counter in a loop
	doIncrement := func(name string, n int) {
		for i := 0; i < n; i++ {
			c.inc(name)
		}
		wg.Done()
	}
	//Run several goroutines concurrently;
	//note that they all access the same Container, and two of them access the same counter
	wg.Add(3)
	go doIncrement("a", 10000)
	go doIncrement("a", 10000)
	go doIncrement("b", 10000)
	//Wait for the goroutines to finish
	wg.Wait()
	fmt.Println(c.counters)
}

//Running the program shows that the counters updated as expected. map[a:20000 b:10000]

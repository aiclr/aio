package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

func main() {

	var ops uint64

	var wg sync.WaitGroup

	for i := 0; i < 50; i++ {
		wg.Add(1)

		go func() {
			for c := 0; c < 1000; c++ {
				//To atomically increment the counter we use AddUint64,
				//giving it the memory address of our ops counter with the & syntax
				atomic.AddUint64(&ops, 1)
			}
			wg.Done()
		}()
	}

	wg.Wait()

	fmt.Println("ops:", ops)
	fmt.Println("func AddUint64(addr *uint64, delta uint64) (new uint64)")
	var i uint64 = 100
	var x uint64 = 2
	// 加2
	atomic.AddUint64(&i, 2)
	fmt.Println(i)
	// 加x
	atomic.AddUint64(&i, x)
	fmt.Println(i)
	// 减x
	atomic.AddUint64(&i, ^(x - 1))
	fmt.Println(i)
	// 减x
	atomic.AddUint64(&i, -x)
	fmt.Println(i)

}

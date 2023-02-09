package main

import "fmt"

func main() {
	queue := make(chan string, 2)
	queue <- "one"
	queue <- "two"
	close(queue)

	//Because we closed the channel above, the iteration(迭代) terminates(终止) after receiving the 2 elements.
	for elem := range queue {
		fmt.Println(elem)
	}
}

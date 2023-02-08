package main

import "fmt"

// 默认 channel 不缓存 相应的接受就绪时 才会发送数据到channel
// 本例设置缓存2个值
func main() {
	messages := make(chan string, 2)
	messages <- "buffered"
	messages <- "channel" // 最多缓存2个值
	fmt.Println(<-messages)
	fmt.Println(<-messages) // <-messages 读取后还可以继续 messages <- 写入数据

	messages <- "a" //可以缓存一个值
	fmt.Println(<-messages)
	messages <- "b"
	fmt.Println(<-messages)

	messages <- "c"
	messages <- "d"
	fmt.Println(<-messages)
	fmt.Println(<-messages)
}

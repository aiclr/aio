package greetings

import "fmt"

func Hello(name string) string {
     //var message string
     //message = fmt.Sprintf("Hi, %v. Welcome!",name)
     // := 操作符 功能与上面两行相同
     message := fmt.Sprintf("Hi, %v. Welcome!",name)
     return message
}

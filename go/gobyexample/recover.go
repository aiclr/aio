package main

import "fmt"

func mayPanic() {
	panic("a problem")
}

func main() {
	//recover must be called within a deferred function.
	//When the enclosing(封闭的) function panics(恐慌),
	//the defer will activate and a recover(恢复) call within it will catch the panic
	defer func() {
		//The return value of recover is the error raised in(根据...生成的) the call to panic
		if r := recover(); r != nil {
			fmt.Println("Recovered.Error:\n", r)
		}
	}()
	mayPanic()
	fmt.Println("After mayPanic()")
}

//Go makes it possible to recover from a panic, by using the recover built-in function.
//A recover can stop a panic from aborting the program and let it continue with execution instead.
//
//An example of where this can be useful:
//a server wouldn’t want to crash(崩溃) if one of the client connections exhibits(出现) a critical(严重的) error.
//Instead, the server would want to close that connection and continue serving other clients.
//In fact, this is what Go’s net/http does by default for HTTP servers.

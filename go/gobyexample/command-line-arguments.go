package main

import (
	"fmt"
	"os"
)

func main() {

	//os.Args provides access to raw command-line arguments.
	//Note that the first value in this slice is the path to the program,
	//and os.Args[1:] holds the arguments to the program
	argsWithProg := os.Args
	argsWithoutProg := os.Args[1:]
	//You can get individual(单独的) args with normal indexing.
	arg := os.Args[3]

	fmt.Println(argsWithProg)
	fmt.Println(argsWithoutProg)
	fmt.Println(arg)
}

//go run command-line-arguments.go a b c d

//go build command-line-arguments.go
//./command-line-arguments a b c d

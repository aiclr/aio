package main

import (
	"fmt"
	"os"
)

// Use os.Exit to immediately exit with a given status
func main() {
	//defers will not be run when using os.Exit,
	//so this fmt.Println will never be called.
	defer fmt.Println("!")
	//Exit with status 3.
	os.Exit(3)
}

//Note that unlike e.g. C,
//Go does not use an integer return value from main to indicate exit status.
//If you’d like to exit with a non-zero status you should use os.Exit

// If you run exit.go using go run, the exit will be picked up by go and printed.
//go run exit.go
//echo $?
//1

//By building and executing a binary you can see the status in the terminal.
//go build exit.go
//./exit
// echo $?
// 3

//$$ is the PID of the current process.

//$? is the return code of the last executed command.

//$# is the number of arguments in $*

//$* is the list of arguments passed to the current proces

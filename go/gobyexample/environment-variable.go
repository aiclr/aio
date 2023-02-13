package main

import (
	"fmt"
	"os"
	"strings"
)

func main() {
	//To set a key/value pair, use os.Setenv.
	os.Setenv("FOO", "1")
	//To get a value for a key, use os.Getenv.
	fmt.Println("FOO:", os.Getenv("FOO"))
	//This will return an empty string if the key isn’t present in the environment
	fmt.Println("BAR:", os.Getenv("BAR"))

	fmt.Println()
	//Use os.Environ to list all key/value pairs in the environment.
	//This returns a slice of strings in the form KEY=value.
	//You can strings.SplitN them to get the key and value. Here we print all the keys
	for _, e := range os.Environ() {
		pair := strings.SplitN(e, "=", 2)
		fmt.Println(pair[0])
	}
	fmt.Printf("%q\n", strings.SplitN("a,b,c,d", ",", 3))
}

//If we set BAR in the environment first, the running program picks that value up.
// BAR=2 go run environment-variable.go
// FOO: 1
// BAR: 2

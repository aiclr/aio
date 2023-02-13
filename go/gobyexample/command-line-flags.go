package main

import (
	"flag"
	"fmt"
)

func main() {
	//Basic flag declarations are available for string, integer, and boolean options.
	//Here we declare a string flag word with a default value "foo" and a short description.
	//This flag.String function returns a string pointer (not a string value);
	//we’ll see how to use this pointer below.
	wordPtr := flag.String("word", "foo", "a string")

	numbPtr := flag.Int("numb", 42, "an int")
	forkPtr := flag.Bool("fork", false, "a bool")

	//It’s also possible to declare an option(选项) that uses an existing var declared elsewhere in the program.
	//Note that we need to pass in a pointer to the flag declaration function.
	var svar string
	flag.StringVar(&svar, "svar", "bar", "a string var")

	//Once all flags are declared, call flag.Parse() to execute the command-line parsing
	flag.Parse()

	//Here we’ll just dump out the parsed(解析) options and any trailing(尾随) positional arguments.
	//Note that we need to dereference the pointers with e.g. *wordPtr to get the actual option values.
	fmt.Println("word:", *wordPtr)
	fmt.Println("numb:", *numbPtr)
	fmt.Println("fork:", *forkPtr)
	fmt.Println("svar:", svar)
	fmt.Println("tail:", flag.Args())
}

//go run command-line-flags.go -word=word -numb=12 -fork=true -svar="啊啊啊啊啊啊" a b c d

//Note that the flag package requires all flags to appear before positional(位置) arguments
//(otherwise the flags will be interpreted(诠释) as positional arguments).
//go run command-line-flags.go -word=word a1 a2 a3 -numb=12 -fork=true -svar="啊啊啊啊啊啊"

//Use -h or --help flags to get automatically generated help text for the command-line program.
//go run command-line-flags.go -h

//If you provide a flag that wasn’t specified to the flag package, the program will print an error message and show the help text again
//go run command-line-flags.go -xxx

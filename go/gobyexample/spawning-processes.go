package main

import (
	"fmt"
	"io"
	"os/exec"
)

//Sometimes our Go programs need to spawn(产生) other, non-Go processes.

func main() {
	//We’ll start with a simple command that takes no arguments or input and just prints something to stdout.
	//The exec.Command helper creates an object to represent(表示) this external(外部的) process.
	dateCmd := exec.Command("date")
	//The Output method runs the command, waits for it to finish and collects its standard output.
	//If there were no errors, dateOut will hold bytes with the date info.
	dateOut, err := dateCmd.Output()
	if err != nil {
		panic(err)
	}
	fmt.Println("> date")
	fmt.Println(string(dateOut))
	//Output and other methods of Command will return *exec.Error if there was a problem executing the command (e.g. wrong path),
	//and *exec.ExitError if the command ran(run 过去式) but exited with a non-zero return code.
	//date doesn’t have a -x flag so it will exit with an error message and non-zero return code
	_, err = exec.Command("date", "-x").Output()
	if err != nil {
		switch e := err.(type) {
		case *exec.Error:
			fmt.Println("failed executing:", err)
		case *exec.ExitError:
			fmt.Println("command exit rc =", e.ExitCode())
		default:
			panic(err)
		}
	}
	//Next we’ll look at a slightly(稍微) more involved(复杂) case where we pipe(用管道输送) data to the external process on its stdin and collect the results from its stdout.
	grepCmd := exec.Command("grep", "hello")
	//Here we explicitly grab input/output pipes, start the process, write some input to it, read the resulting output, and finally wait for the process to exit
	grepIn, _ := grepCmd.StdinPipe()
	grepOut, _ := grepCmd.StdoutPipe()
	grepCmd.Start()
	grepIn.Write([]byte("hello grep\ngoodbye grep"))
	grepIn.Close()
	grepBytes, _ := io.ReadAll(grepOut)
	grepCmd.Wait()
	//We omitted(忽略) error checks in the above example, but you could use the usual if err != nil pattern for all of them.
	//We also only collect the StdoutPipe results, but you could collect the StderrPipe in exactly the same way.
	fmt.Println("> grep hello")
	fmt.Println(string(grepBytes))

	//Note that when spawning commands we need to provide an explicitly delineated command and argument array, vs. being able to just pass in one command-line string.
	//If you want to spawn a full command with a string, you can use bash’s -c option:
	lsCmd := exec.Command("bash", "-c", "ls -a -l -h")
	lsOut, err := lsCmd.Output()
	if err != nil {
		panic(err)
	}
	fmt.Println("> ls -a -l -h")
	fmt.Println(string(lsOut))
}

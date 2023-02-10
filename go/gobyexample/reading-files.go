package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {

	dat, err := os.ReadFile("/tmp/dat")
	check(err)
	fmt.Print(string(dat))

	f, err := os.Open("/tmp/dat")
	check(err)
	//Read some bytes from the beginning of the file.
	//Allow up to 5 to be read but also note how many actually were read
	b1 := make([]byte, 5)
	n1, err := f.Read(b1)
	check(err)
	fmt.Printf("%d bytes: %s\n", n1, string(b1[:n1]))
	//You can also Seek to a known location in the file and Read from there
	o2, err := f.Seek(6, 0)
	check(err)
	b2 := make([]byte, 2)
	n2, err := f.Read(b2)
	check(err)
	fmt.Printf("%d bytes @ %d: ", n2, o2)
	fmt.Printf("%v\n", string(b2[:n2]))

	//The io package provides some functions that may be helpful for file reading.
	//For example, reads like the ones above can be more robustly(鲁棒性) implemented with ReadAtLeast
	o3, err := f.Seek(6, 0)
	check(err)
	b3 := make([]byte, 2)
	n3, err := io.ReadAtLeast(f, b3, 2)
	check(err)
	fmt.Printf("%d bytes @ %d: %s\n", n3, o3, string(b3))

	//There is no built-in rewind(倒带), but Seek(0, 0) accomplishes(实现) this.
	_, err = f.Seek(0, 0)
	check(err)

	//The bufio package implements a buffered reader
	//that may be useful both for its efficiency(效率) with many small reads and because of the additional(附加的) reading methods it provides.
	r4 := bufio.NewReader(f)
	b4, err := r4.Peek(5)
	check(err)
	fmt.Printf("5 bytes: %s\n", string(b4))
	//Close the file when you’re done (usually this would be scheduled immediately after Opening with defer)
	//defer f.Close()
	f.Close()
}

//echo 'hello' > /tmp/dat
//echo 'go' >> /tmp/dat
// go run reading-files.go

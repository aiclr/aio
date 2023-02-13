package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {
	//The easiest way to create a temporary file is by calling os.CreateTemp.
	//It creates a file and opens it for reading and writing.
	//We provide "" as the first argument, so os.CreateTemp will create the file in the default location for our OS.
	f, err := os.CreateTemp("", "sample")
	check(err)

	fmt.Println("Temp file name:", f.Name())

	defer os.Remove(f.Name())

	_, err = f.Write([]byte{1, 2, 3, 4})
	check(err)

	//If we intend to write many temporary files, we may prefer to create a temporary directory.
	//os.MkdirTemp’s arguments are the same as CreateTemp’s, but it returns a directory name rather than an open file.
	dname, err := os.MkdirTemp("", "sampledir")
	check(err)
	fmt.Println("Temp dir name:", dname)

	defer os.RemoveAll(dname)

	fname := filepath.Join(dname, "file1")
	err = os.WriteFile(fname, []byte{1, 2}, 0666)
	check(err)
}

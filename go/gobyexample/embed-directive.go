package main

import (
	//Import the embed package;
	//if you don’t use any exported identifiers(标识) from this package, you can do a blank import with _ "embed".
	"embed"
	"os"
)

// //go:embed is a compiler(编译器) directive(指令) that allows programs to include arbitrary(任意的) files and folders in the Go binary(二进制) at build time.
//Read more about the embed directive here.

// embed directives accept paths relative to the directory containing the Go source file.
// This directive embeds the contents of the file into the string variable immediately following it.
//
//go:embed folder/single_file.txt
var fileString string

// Or embed the contents of the file into a []byte.
//
//go:embed folder/single_file.txt
var fileByte []byte

// We can also embed multiple files or even folders with wildcards(通配符).
// This uses a variable of the embed.FS type, which implements a simple virtual file system
//
//go:embed folder/single_file.txt
//go:embed folder/*.hash
var folder embed.FS

func main() {

	print(fileString)
	print(string(fileByte))

	content1, _ := folder.ReadFile("folder/file1.hash")
	print(string(content1))

	content2, _ := folder.ReadFile("folder/file2.hash")
	print(string(content2))

	content3, _ := folder.ReadFile("folder/single_file.txt")
	print(string(content3))

	defer os.RemoveAll("folder")
}

// mkdir -p folder
// echo "hello go" > folder/single_file.txt
// echo "123" > folder/file1.hash
// echo "456" > folder/file2.hash
// go run embed-directive.go

package main

import (
	"fmt"
	"os"
)

type point struct {
	x, y int
}

func main() {

	p := point{1, 2}
	fmt.Printf("struct1: %v\n", p)
	//If the value is a struct, the %+v variant will include the struct’s field names.
	fmt.Printf("struct2: %+v\n", p)
	//The %#v variant prints a Go syntax(语法) representation(结构) of the value, i.e. the source code snippet that would produce that value.
	fmt.Printf("struct3: %#v\n", p)
	//To print the type of a value, use %T.
	fmt.Printf("type: %T\n", p)
	//Formatting booleans is straight-forward(直接的).
	fmt.Printf("bool: %t\n", true)
	//There are many options for formatting integers. Use %d for standard, base-10 formatting
	fmt.Printf("int: %d\n", 123)
	//This prints a binary representation(表现形式).
	fmt.Printf("bin: %b\n", 14)
	//This prints the character corresponding(对应的) to the given integer.
	fmt.Printf("char: %c\n", 33)
	//%x provides hex encoding
	fmt.Printf("hex: %x\n", 456)
	//There are also several formatting options for floats. For basic decimal formatting use %f
	fmt.Printf("float1: %f\n", 78.9)
	//%e and %E format the float in (slightly different versions of) scientific notation.
	fmt.Printf("float2: %e\n", 123400000.0)
	fmt.Printf("float3: %E\n", 123400000.0)
	//For basic string printing use %s.
	fmt.Printf("str1: %s\n", "\"string\"")
	//To double-quote(双引号) strings as in Go source, use %q
	fmt.Printf("str2: %q\n", "\"string\"")
	//As with integers seen earlier, %x renders the string in base-16, with two output characters per byte of input.
	fmt.Printf("str3: %x\n", "hex this")
	//To print a representation(表现形式) of a pointer, use %p.
	fmt.Printf("pointer: %p\n", &p)

	fmt.Printf("width1: |%6d|%6d|\n", 12, 345)
	//width2: |  1.20|  3.45|
	fmt.Printf("width2: |%6.2f|%6.2f|\n", 1.2, 3.45)
	//width3: |1.20  |3.45  |
	fmt.Printf("width3: |%-6.2f|%-6.2f|\n", 1.2, 3.45)
	//width4: |   foo|     b|
	fmt.Printf("width4: |%6s|%6s|\n", "foo", "b")
	//width4: |   foo|     b|
	fmt.Printf("width5: |%-6s|%-6s|\n", "foo", "b")
	//So far we’ve seen Printf, which prints the formatted string to os.Stdout.
	//Sprintf formats and returns a string without printing it anywhere
	s := fmt.Sprintf("sprintf: a %s", "string")
	fmt.Println(s)
	//You can format+print to io.Writers other than os.Stdout using Fprintf.
	fmt.Fprintf(os.Stderr, "io: an %s\n", "error")
}

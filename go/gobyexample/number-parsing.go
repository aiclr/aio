package main

import (
	"fmt"
	"strconv"
)

func main() {
	//With ParseFloat, this 64 tells how many bits of precision(精度) to parse
	f, _ := strconv.ParseFloat("1.234", 64)
	fmt.Println(f)
	//For ParseInt,
	//the 0 means infer the base from the string.
	//64 requires that the result fit in 64 bits
	//// If the base argument is 0, the true base is implied by the string's
	//// prefix following the sign (if present): 2 for "0b", 8 for "0" or "0o",
	//// 16 for "0x", and 10 otherwise. Also, for argument base 0 only,
	//// underscore characters are permitted as defined by the Go syntax for
	//// [integer literals].
	i, _ := strconv.ParseInt("123", 0, 64)
	fmt.Println(i)

	d, _ := strconv.ParseInt("0x1c8", 0, 64)
	fmt.Println(d)

	u, _ := strconv.ParseUint("789", 0, 64)
	fmt.Println(u)
	
	//Atoi is a convenience function for basic base-10 int parsing
	k, _ := strconv.Atoi("135")
	fmt.Println(k)

	//Parse functions return an error on bad input.
	_, e := strconv.Atoi("wat")
	fmt.Println(e)
}

package main

import (
	"crypto/sha256"
	"crypto/sha512"
	"fmt"
)

func main() {
	s := "sha256 this string"

	h := sha256.New()
	h512 := sha512.New()

	h.Write([]byte(s))
	h512.Write([]byte(s))

	bs := h.Sum(nil)
	bs512 := h512.Sum(nil)

	fmt.Println(s)
	fmt.Printf("%x\n", bs)
	fmt.Printf("%x\n", bs512)
}

package main

import (
	"fmt"
	"sort"
)

// In order to sort by a custom function in Go, we need a corresponding(相应的) type.
// Here we’ve created a byLength type that is just an alias for the builtin(内置) []string type.
type byLength []string

// Len Swap Less
// We implement sort.Interface - Len, Less, and Swap - on our type so we can use the sort package’s generic(通用) Sort function.
// Len and Swap will usually be similar(相似的) across(在...各处) types and Less will hold the actual custom(自定义) sorting logic.
// In our case we want to sort in order of increasing(增加) string length, so we use len(s[i]) and len(s[j]) here
func (s byLength) Len() int {
	return len(s)
}
func (s byLength) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}
func (s byLength) Less(i, j int) bool {
	return len(s[i]) < len(s[j])
}

type student struct {
	name string
	age  int
}

type byAge []student

func (s byAge) Len() int {
	return len(s)
}

func (s byAge) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}
func (s byAge) Less(i, j int) bool {
	return s[i].age < s[j].age
}

func main() {
	fruits := []string{"peach", "banana", "kiwi"}
	sort.Sort(byLength(fruits))
	fmt.Println(fruits)

	students := []student{{name: "leo", age: 13}, {name: "jack", age: 12}, {name: "caddy", age: 11}}
	sort.Sort(byAge(students))
	fmt.Println(students)
}

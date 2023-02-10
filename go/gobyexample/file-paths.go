package main

import (
	"fmt"
	"path/filepath"
	"strings"
)

func main() {

	p := filepath.Join("dir1", "dir2", "filename")
	//p: dir1/dir2/filename
	fmt.Println("p:", p)
	//dir1/filename
	fmt.Println(filepath.Join("dir1//", "filename"))

	//dir1/filename
	fmt.Println(filepath.Join("dir1/../dir1", "filename"))

	//Dir(p): dir1/dir2
	fmt.Println("Dir(p):", filepath.Dir(p))
	//Base(p): filename
	fmt.Println("Base(p):", filepath.Base(p))

	//false
	fmt.Println(filepath.IsAbs("dir/file"))
	//true
	fmt.Println(filepath.IsAbs("/dir/file"))

	filename := "config.json"

	ext := filepath.Ext(filename)
	//.json
	fmt.Println(ext)
	// config
	fmt.Println(strings.TrimSuffix(filename, ext))

	//Rel finds a relative path between a base and a target.
	//It returns an error if the target cannot be made relative to base
	rel, err := filepath.Rel("a/b", "a/b/t/file")
	if err != nil {
		panic(err)
	}
	// t/file
	fmt.Println(rel)

	rel, err = filepath.Rel("a/b", "a/c/t/file")
	if err != nil {
		panic(err)
	}
	// ../c/t/file
	fmt.Println(rel)
}

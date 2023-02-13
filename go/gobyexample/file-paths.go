package main

import (
	"fmt"
	"path/filepath"
	"strings"
)

// The filepath package provides functions to parse and construct file paths in a way that is portable(可移植的) between operating systems;
// dir/file on Linux vs. dir\file on Windows, for example
func main() {

	//Join should be used to construct paths in a portable(可移植的) way.
	//It takes any number of arguments and constructs a hierarchical(层级) path from them.
	p := filepath.Join("dir1", "dir2", "filename")
	//p: dir1/dir2/filename
	fmt.Println("p:", p)

	//You should always use Join instead of concatenating(使连接) /s or \s manually(手动地).
	//In addition to(除...之外) providing portability(可移植性),
	//Join will also normalize(使正常化) paths by removing superfluous(多余的) separators(分割符) and directory changes.
	// dir1/filename
	fmt.Println(filepath.Join("dir1//", "filename"))
	// dir1/filename
	fmt.Println(filepath.Join("dir1/../dir1", "filename"))

	//Dir and Base can be used to split(使分开) a path to the directory and the file.
	//Alternatively(或者), Split will return both in the same call
	//Dir(p): dir1/dir2
	fmt.Println("Dir(p):", filepath.Dir(p))
	//Base(p): filename
	fmt.Println("Base(p):", filepath.Base(p))

	//We can check whether a path is absolute.
	//false
	fmt.Println(filepath.IsAbs("dir/file"))
	//true
	fmt.Println(filepath.IsAbs("/dir/file"))

	filename := "config.json"
	//Some file names have extensions following a dot.
	//We can split the extension out of such names with Ext.
	ext := filepath.Ext(filename)
	//.json
	fmt.Println(ext)
	// config
	fmt.Println(strings.TrimSuffix(filename, ext))

	//Rel finds a relative path(相对路径) between a base and a target.
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

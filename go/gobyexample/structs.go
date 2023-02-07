package main

import "fmt"

type person struct {
	name string
	age  int
}

func newPerson(name string) *person {
	p := person{name: name}
	p.age = 24
	return &p
}

// 值传递 创建一个临时变量p 保存传入的person
func changePerson(p person) {
	//修改的是临时变量p
	p.age = 100
}

// 值传递 创建一个临时变量p 保存传入的person
func changePersonPtr3(p person) {
	//p 指向一个新的变量地址
	pp := &p
	pp.age = 100
}

// 地址传递 创建一个临时指针变量p 保存参数指针
func changePersonPtr(p *person) {
	//修改指针指向的内存
	p.age = 100
}

// 传递地址 创建一个临时指针变量p 保存参数指针
func changePersonPtr2(p *person) {
	//新创建的局部指针变量p 指向一个新的地址
	p = newPerson("Caddy")
	// 修改的是 newPerson("Caddy") 的内存值
	p.age = 200
}

func main() {
	p1 := person{name: "p1", age: 10}
	changePerson(p1)
	fmt.Println("值传递 不影响传入前的值：", p1)
	changePersonPtr3(p1)
	fmt.Println("值传递 修改的是新创建的临时变量p指向的内存,不影响传入前的值：", p1)

	p2 := person{name: "p2", age: 10}
	changePersonPtr(&p2)
	fmt.Println("值传递 值是传入的指针 创建一个临时指针变量p 保存参数指针，修改指针指向内容会影响传入前的值：", p2)
	p3 := person{name: "p3", age: 10}
	changePersonPtr2(&p3)
	fmt.Println("值传递 值是传入的指针 创建一个临时指针变量p 保存参数指针，修改临时指针变量指向内容不会影响传入前的值：", p3)

	fmt.Println(person{"Bob", 20})
	fmt.Println(person{name: "Alice", age: 30})
	fmt.Println(person{name: "Fred"})
	fmt.Println(&person{name: "Ann", age: 40})
	fmt.Println(newPerson("Jon"))
	s := person{name: "Sean", age: 50}
	fmt.Println(s.name)
	sp := &s
	fmt.Println(sp.age)
	sp.age = 51
	fmt.Println(sp.age)
	fmt.Println(s.age)

	s.age = 70
	fmt.Println(s.age)
	fmt.Println(sp.age)
}

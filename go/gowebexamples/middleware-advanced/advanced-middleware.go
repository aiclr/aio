package main

import (
	"fmt"
	"log"
	"net/http"
	"time"
)

// 定义 Middleware 类型为 func(handlerFunc http.HandlerFunc) 返回值 http.HandlerFunc
type Middleware func(handlerFunc http.HandlerFunc) http.HandlerFunc

// 返回值 Middleware
func logging() Middleware {
	return func(f http.HandlerFunc) http.HandlerFunc {
		return func(w http.ResponseWriter, r *http.Request) {
			start := time.Now()
			defer func() { log.Println(r.URL.Path, time.Since(start)) }()
			f(w, r)
		}
	}
}

// 返回值 Middleware
func Method(m string) Middleware {
	return func(f http.HandlerFunc) http.HandlerFunc {
		return func(w http.ResponseWriter, r *http.Request) {
			if r.Method != m {
				http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
				return
			}
			f(w, r)
		}
	}
}

func logging2() func(f http.HandlerFunc) http.HandlerFunc {
	return func(f http.HandlerFunc) http.HandlerFunc {
		return func(w http.ResponseWriter, r *http.Request) {
			if r.Method == http.MethodPost {
				log.Println("POST REQUEST")
			}
			f(w, r)
		}
	}
}

func Chain(f http.HandlerFunc, middlewares ...Middleware) http.HandlerFunc {
	for _, m := range middlewares {
		f = m(f)
	}
	return f
}

func Hello(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, "hello world")
}

func main() {
	http.HandleFunc("/", Chain(Hello, Method("GET"), logging(), logging2()))
	http.ListenAndServe(":30000", nil)
}

//curl -s http://localhost:30000
//curl -s -XPOST http://localhost:30000

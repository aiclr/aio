package main

import (
	"fmt"
	"net/http"
	"time"
)

//In the http-servers.go example we looked at setting up a simple HTTP server.
//HTTP servers are useful for demonstrating(演示) the usage of context.Context for controlling cancellation(撤销).
//A Context carries deadlines, cancellation signals, and other request-scoped values across(跨过) API boundaries(边界) and goroutines.

func hello(w http.ResponseWriter, req *http.Request) {
	//A context.Context is created for each request by the net/http machinery, and is available with the Context() method
	ctx := req.Context()
	fmt.Println("server: hello handler started")
	defer fmt.Println("server: hello handler ended")

	//Wait for a few seconds before sending a reply to the client.
	//This could simulate some work the server is doing.
	//While working, keep an eye on the context’s Done() channel for a signal that we should cancel the work and return as soon as possible
	select {
	case <-time.After(10 * time.Second):
		fmt.Fprintf(w, "hello\n")
	case <-ctx.Done():
		//The context’s Err() method returns an error that explains why the Done() channel was closed.
		err := ctx.Err()
		fmt.Println("server:", err)
		internalError := http.StatusInternalServerError
		http.Error(w, err.Error(), internalError)
	}
}

func main() {
	//As before, we register our handler on the “/hello” route, and start serving.
	http.HandleFunc("/hello", hello)
	http.ListenAndServe(":30000", nil)
}

//Run the server in the background
// go run context-in-http-servers.go &
// lsof -i:30000
// COMMAND     PID USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
// context-i 21307  leo    3u  IPv6 310668      0t0  TCP *:ndmps (LISTEN)

// kill -9 PID(http-servers.go)
// kill -9 21307

//Simulate a client request to /hello, hitting Ctrl+C shortly after starting to signal cancellation
// curl localhost:30000/hello

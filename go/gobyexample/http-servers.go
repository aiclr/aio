package main

import (
	"fmt"
	"net/http"
)

// A fundamental concept in net/http servers is handlers.
// A handler is an object implementing the http.Handler interface.
// A common way to write a handler is by using the http.HandlerFunc adapter on functions with the appropriate signature
func hello(w http.ResponseWriter, req *http.Request) {
	//Functions serving as handlers take a http.ResponseWriter and a http.Request as arguments.
	//The response writer is used to fill in the HTTP response. Here our simple response is just “hello\n”.
	fmt.Fprintf(w, "hello\n")
}

func headers(w http.ResponseWriter, req *http.Request) {
	//This handler does something a little more sophisticated(复杂的) by reading all the HTTP request headers and echoing(回显) them into the response body
	for name, headers := range req.Header {
		for _, h := range headers {
			fmt.Fprintf(w, "%v: %v\n", name, h)
		}
	}
}

func main() {
	//We register our handlers on server routes using the http.HandleFunc convenience function.
	//It sets up the default router in the net/http package and takes a function as an argument.
	http.HandleFunc("/hello", hello)
	http.HandleFunc("/headers", headers)
	//Finally, we call the ListenAndServe with the port and a handler.
	//nil tells it to use the default router we’ve just set up.
	http.ListenAndServe(":30000", nil)
}

//Run the server in the background
// go run http-servers.go &
// lsof -i:30000
// kill -9 PID(http-servers.go)
// kill -9 PID2(http-server)

//Access the /hello route.
// curl localhost:30000/hello
// curl localhost:30000/headers

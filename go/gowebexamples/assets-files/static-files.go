package main

import "net/http"

func main() {
	fs := http.FileServer(http.Dir("assets/"))
	http.Handle("/static/", http.StripPrefix("/static/", fs))
	http.ListenAndServe(":30000", nil)
}

//curl -s http://127.0.0.1:30000/static/css/styles.css

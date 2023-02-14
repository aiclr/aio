package main

import (
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"net/http"
)

func main() {
	//create a new router
	r := mux.NewRouter()
	//POST GET PUT DELETE http://localhost:30000/books/cprimerplus/page/10
	r.HandleFunc("/books/{title}/page/{page}", func(w http.ResponseWriter, r *http.Request) {
		//get the book
		vars := mux.Vars(r)
		title := vars["title"]
		page := vars["page"]

		// navigate to the page
		fmt.Fprintf(w, "You've requested the book: %s on page %s\n", title, page)
	})
	//Request Methods
	//Restrict the request handler to specific HTTP methods.
	//POST GET PUT DELETE http://localhost:30000/books/cprimerplus
	r.HandleFunc("/books/{title}", CreateBook).Methods("POST")
	r.HandleFunc("/books/{title}", ReadBook).Methods("GET")
	r.HandleFunc("/books/{title}", UpdateBook).Methods("PUT")
	r.HandleFunc("/books/{title}", DeleteBook).Methods("DELETE")

	//Hostnames & Subdomains
	//Restrict the request handler to specific hostnames or subdomains.
	// GET http://localhost:30000/books/cprimerplus
	r.HandleFunc("/books/{title}", BookHandler).Host("www.mybookstore.com")

	//schemes
	//Restrict the request handler to http/https.
	r.HandleFunc("/secure", SecureHandler).Schemes("https")    //GET https://localhost:30000/secure
	r.HandleFunc("/insecure", InsecureHandler).Schemes("http") //GET http://localhost:30000/secure
	//Path Prefixes & Subrouters
	//Restrict the request handler to specific path prefixes.

	bookrouter := r.PathPrefix("/go/books").Subrouter()
	bookrouter.HandleFunc("/", AllBooks)       //GET http://localhost:30000/go/books/
	bookrouter.HandleFunc("/{title}", GetBook) //GET http://localhost:30000/go/books/cprimerplus
	http.ListenAndServe(":30000", r)
}

type book struct {
	Title string
}

func CreateBook(w http.ResponseWriter, r *http.Request) {
	//get the book
	vars := mux.Vars(r)
	title := vars["title"]

	response := &book{
		Title: title}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func ReadBook(w http.ResponseWriter, r *http.Request) {
	//get the book
	vars := mux.Vars(r)
	title := vars["title"]

	response := &book{
		Title: title}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func UpdateBook(w http.ResponseWriter, r *http.Request) {
	//get the book
	vars := mux.Vars(r)
	title := vars["title"]

	response := &book{
		Title: title}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func DeleteBook(w http.ResponseWriter, r *http.Request) {
	//get the book
	vars := mux.Vars(r)
	title := vars["title"]

	response := &book{
		Title: title}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}

func BookHandler(w http.ResponseWriter, r *http.Request) {
	//get the book
	vars := mux.Vars(r)
	title := vars["title"]

	response := &book{
		Title: title}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func SecureHandler(w http.ResponseWriter, r *http.Request) {
	//get the book
	response := &book{
		Title: "secure"}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func InsecureHandler(w http.ResponseWriter, r *http.Request) {
	//get the book
	response := &book{
		Title: "insecure"}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func AllBooks(w http.ResponseWriter, r *http.Request) {
	response := &book{
		Title: "allBook"}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}
func GetBook(w http.ResponseWriter, r *http.Request) {
	//get the book
	vars := mux.Vars(r)
	title := vars["title"]

	response := &book{
		Title: title}
	resJson, _ := json.Marshal(response)
	// navigate to the page
	fmt.Fprint(w, string(resJson))
}

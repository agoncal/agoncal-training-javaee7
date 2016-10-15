# Books

### Get an XML representation of a book
`curl -X GET -H "Accept: application/xml" http://localhost:8080/cdbookstore/rest/items/book/1`

### Get a JSon representation of a book
`curl -X GET -H "Accept: application/json" http://localhost:8080/cdbookstore/rest/items/book/1`

### Get an XML representation of all the books
`curl -X GET -H "Accept: application/xml" http://localhost:8080/cdbookstore/rest/items/books`

### Get a JSon representation of all the books
`curl -X GET -H "Accept: application/json" http://localhost:8080/cdbookstore/rest/items/books`

### Create a book
`curl -X POST --data-binary "{ \"title\":\"Dummy Title\", \"price\":\"29.99\", \"description\":\"Dummy Description\", \"isbn\":\"13-84356-731917428\", \"nbOfPage\":\"240\" }" -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/cdbookstore/rest/items/book`

{"title":"Dummy Title","price":29.99,"description":"Dummy Description","isbn":"13-84356-731917428","nbOfPage":240,"contentLanguage":"ITALIAN"}

### Deletes a book
`curl -X DELETE http://localhost:8080/cdbookstore/rest/items/book/2902`

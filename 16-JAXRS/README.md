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
`curl -X POST --data-binary "{ \"title\":\"H3G3\", \"price\":\"24.0\", \"description\":\"3rd Scifi IT book\", \"illustrations\":\"false\", \"isbn\":\"134-234\", \"nbOfPage\":\"241\" }" -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/cdbookstore/rest/items/book`

### Deletes a book
`curl -X DELETE http://localhost:8080/cdbookstore/rest/items/book/2902`

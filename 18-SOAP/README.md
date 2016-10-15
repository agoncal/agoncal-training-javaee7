# SOAP

### Generate WSDL from Java code
`mvn clean compile jaxws:wsgen`

### Generated artifacts (under target/generated-sources)

    ├── wsdl
    │   ├── ItemSoapService.wsdl
    │   └── ItemSoapService_schema1.xsd
    └── wsgen
        └── org
            └── agoncal
                └── training
                    └── javaee
                        └── soap
                            └── jaxws
                                ├── CreateBook.java
                                ├── CreateBookResponse.java
                                ├── FindAllBooks.java
                                ├── FindAllBooksResponse.java
                                ├── FindBook.java
                                ├── FindBookResponse.java
                                ├── GenerateNumber.java
                                ├── GenerateNumberResponse.java
                                └── RemoveBook.java

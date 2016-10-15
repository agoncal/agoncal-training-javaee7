# SOAP

### Generate WSDL from Java code
`mvn clean compile jaxws:wsimport`

### Generated artifacts (under target/generated-sources/wsimport)

.
└── org
    └── agoncal
        └── training
            └── javaee
                └── soap
                    ├── Book.java
                    ├── Chapter.java
                    ├── CreateBook.java
                    ├── CreateBookResponse.java
                    ├── FindAllBooks.java
                    ├── FindAllBooksResponse.java
                    ├── FindBook.java
                    ├── FindBookResponse.java
                    ├── GenerateNumber.java
                    ├── GenerateNumberResponse.java
                    ├── Item.java
                    ├── ItemSoap.java
                    ├── ItemSoapService.java
                    ├── Language.java
                    ├── ObjectFactory.java
                    ├── RemoveBook.java
                    └── package-info.java

### Problem encoutered

`schema_reference: Failed to read schema document ‘MyWebservice.xsd_1.xsd’, because ‘file’ access is not allowed due to restriction set by the accessExternalSchema property` 

To fix this error, in the Maven plugin, just pass :

    -Djavax.xml.accessExternalSchema=all

Or, create a file named `jaxp.properties` and paste the below content to it :

    javax.xml.accessExternalSchema = all

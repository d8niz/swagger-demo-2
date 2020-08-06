# Swagger-Demo-2
API docs with Swagger second demo application (configuration & customization)

## Prerequisites
- `Java` dev. environment
- `Spring Boot` (IDE plugin or using browser)
-  `Swagger-Demo-1` code-base
- _Keyboard and fingers (attached to a pair of hands)_

## Step by Step

- Copy or use the previous code-base to  continue.
- Add a `Docket` bean to your main application class as so:

```java
@Bean
public Docket swaggerConfig(){
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.ant("/api/*"))
        .apis(RequestHandlerSelectors.basePackage("com.eteration"))
        .build();
}
```
- Builder pattern meanings:
    - Constructor takes `DocumenationType`
    - Select API to listen `select()`
        - You can use `regex` or `ant` pattern built-in.
    - You can select `paths()` and/or packages by using `apis()`
    - Finalize building your `Docket` by `build()`

- Run your application and observe:
    - Documentation displays only our API (i.e. `error` bla bla stuff should be out of the window)

- Add application meta-data by modifying builder pattern for your `Docket` bean.

```java
@Bean
public Docket swaggerConfig(){
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.ant("/api/*"))
        .apis(RequestHandlerSelectors.basePackage("com.eteration"))
        .build()
        .apiInfo(metaData()); // meta-data stuff goes here
}
```

- `ApiInfo metadata()` method will look something like this ( notice the builder pattern again):

```java
private ApiInfo metaData(){
    return new ApiInfoBuilder()
        .title("Eteration Bootcamp Rest Api Documentation Swagger & Spring Boot")
        .description("\"Spring Boot Rest Api for Address Book Service\"")
        .version("1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
            .contact(new Contact("Deniz Memis", "https://www.eteration.com/", "deniz.memis@eteration.com"))
            .build();
}
```


- Add annotations (`@ApiOperation`, `@ApiResponses`, `@ApiResponse` etc.) to the endpoints of your contoller (resource), some samples:

```java
@ApiOperation(value = "Retrieve a contact with an ID", response = Contact.class)
@GetMapping("/{id}")
public Contact getContact(@PathVariable String id){
    return contacts.get(id);


@ApiOperation(value = "View all contacts in the address book", response = Contact.class, responseContainer = "List")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully retrieved the list!"),
    @ApiResponse(code = 401, message = "Not authorized to view the list!"),
    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden!"),
    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found!"),

})
@GetMapping("/")
public List<Contact> getAllContacts(){
    return new ArrayList<Contact>(contacts.values());

}
}

```

- Add `@ApiModelProperty` annotation to the model `Contact.java`:

```java
@ApiModelProperty(notes = "Id of the contact")
private String id;
@ApiModelProperty(notes = "Name of the contact")
private String name;
@ApiModelProperty(notes = "Phone number of the contact")
private String phone;
```

- Run and check the final version of your documentation.
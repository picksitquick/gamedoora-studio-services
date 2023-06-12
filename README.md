# gamedoora-studio-services

The Spring boot microservice application for gamedoora

## Requirements
- JDK 17
- PostgreSQL 14.8
- Maven 3.3 or higher 

## Command to build and launch
```
mvnw clean install
```
```
mvnw spring-boot:run 
```


## Service dependencies
1. Gamedoora Config Service  
   a. Run the config server  
   b. Set CONFIG_SERVER to the URL of the config server
      ```
      export CONFIG_SERVER=<URL of the config server>
      ``` 


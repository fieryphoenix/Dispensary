# Dispensary

## Installation

- Tomcat - embedded
- Java 8
    - Download http://www.oracle.com/technetwork/java/javase/downloads/index.html
    - Set JAVA_HOME to extracted folder
- Any IDE for Java and WEB
    - Intellij IDEA (Community edition - https://www.jetbrains.com/idea/download/#section=windows)
- Maven 3.5+ 
    - Download and extract http://maven.apache.org/download.cgi
    - Set MAVEN_HOME to extracted directory
- MongoDB
    - Instruction https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
- Install GIT


## Initialization
````sh
> mvn spring-boot:run -P InitDB 
````

## Running
To prepare project and download all external resource
````sh
> mvn clean package 
````
> **Note:** Node.js and Bower will be installed locally

To run mongo 
````sh
> mongod.exe -f {path}\mongod.conf.yml
````

To start application
````sh
> mvn spring-boot:run
````
Open in browser [http://localhost:8080](http://localhost:8080)
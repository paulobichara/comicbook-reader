# comicbook-reader

This project aims to be a HTML5 comic book reader with Java EE backend. It was entirely structured with Apache Maven. The project is still in an initial development stage (including the documentation). Right bellow, I'm gonna list the requirements, some of the main technologies involved and give some instructions on how to build and run the project.

***
### 1 - Requirements
    - Oracle's JDK 7.X.(installed and configured)
    - Wildfly9 (just download and uncompress it in some directory)
    - Apache Maven (3.X.X)
    - Bower (it must be installed and its location must be added to your PATH environment variable)
    
***
### 2 - Main Technologies
    
#### 2.1 - Frontend Web Application
    - Angular.JS
    - Require.JS
    - Bootstrap 3
    - Bower (integrated with Maven through configuration)

#### 2.2 - Backend Web Application
    - Java EE 7
    - apiDoc.JS (for auto generated REST API documentation)

***
### 3 - Building and running
    
First, start wildfly9 in standalone mode. E.g., in a linux environment:

**`cd wildfly-9.0.1.Final/bin/`**
**`./standalone.sh`**

Now, just clone the repo and enter in root folder

**`git clone https://github.com/paulobichara/comicbook-reader/`**  
**`cd comicbook-reader/`**

folder and run:

**`mvn clean install`**

And.. that's it!

***
### 4 - Testing

Just open a browser and access [http://localhost:8080/comicbook-reader/](http://localhost:8080/comicbook-reader/)
    

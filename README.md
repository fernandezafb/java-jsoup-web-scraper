# Web scraper using Java - Sainsbury

This is a console-based application that crawls a list of products 
and returns some of their information in JSON format. 

Technologies used:
- Java 8
- Spring Boot
- Jackson
- Jsoup
- Maven

Although spring boot is mostly used to build web applications, web and
embedded tomcat dependencies are not included as they are optional. I decided
to take of advantage of the fast development and better structure of DI and
spring.

Steps to run the application:

1. Clone the repository in the desired local folder: `git clone https://github.com/AlejandroFB/java-jsoup-web-scraper.git`

2. Check that you have valid versions of Java 8 and Maven correctly installed with updated system PATH variables. 

3. Go to the root application folder. Run `maven clean install` within console or from your IDE Maven plugin if you decide 
to import the project on it. This will also run the unit tests in test phase and integration tests in verify phase.

4. Now go to the generated /target directory in the root application folder and run: `java -jar sainsbury-0.0.1-SNAPSHOT.jar`
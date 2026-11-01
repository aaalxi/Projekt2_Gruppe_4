# Projekt2_Gruppe_4

This project (Ønskeskyen) is a web-application build with Spring Boot.  
Users can create, share and reserve wishes on a Wishlist. 

1. Technology used and has to be installed before run: 
* Java JDK 25
* MySQL Server
* MySQL Workbench
* (IntelliJ)

2. Open in IntelliJ
  * Clone project from this repository
  * Open IntelliJ
  * Wait for Maven to get dependencies
  * The project SDK has to be JDK 25

3. Dependencies has to be added:
* Spring Boot Dev Tools
* Spring Web
* Thymeleaf
* JDBC API
* MySQL Driver

4. To create the Database and tables
 Path: main > resources > sqlQueries:
 Run: only run the queries and not the application in IntelliJ at this point.
 * Open createDB.sql and Run (as root)
 * Open createUser.sql and Run
 * Open testData.sql and Run 
  
Tables and queries are made for test environment, and will drop all tables when Run. Changes won't be saved anywhere if tables are dropped. 
The query for testData.sql will insert some data for demonstration purposes. 

5. Run the Application
Path: main > java > com > example > projekt_2_gruppe_4 > Projekt2Gruppe4Application
* Find the main application class and Run.
* Open http://localhost:8080

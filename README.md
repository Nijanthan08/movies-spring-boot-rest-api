# movies-spring-boot-rest-api
Spring Boot REST API for the movies-web-app

REST API for the WEB APP(Movie Review Portal). API host the following services mentioned below:

1. View the list of Movies available from the HTTP service GET : http://localhost:8080/api/movies

![](/attachments/screenshots/Movies.jpg)

2. Check complete Movie information from the HTTP service GET : http://localhost:8080/api/movies/"movieId"

![](/attachments/screenshots/MovieInfo.jpg)

3. Add Reviews to the movie from the HTTP service POST :  http://localhost:8080/api/movies/review

![](/attachments/screenshots/CheckReviews.jpg)

4. Add a Movie to the portal using the HTTP service POST : http://localhost:8080/api/movies

![](/attachments/screenshots/AddMovie.jpg)

Please follow the steps mentioned below to run the Batch Application in your local:

1. Make sure to install the following softwares mentioned below 

    Java SE Development Kit 8: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html <br/>
    Apache Maven: https://maven.apache.org/download.cgi <br/>
    Eclipse IDE: https://www.eclipse.org/downloads/packages/release/2019-09/r/eclipse-ide-enterprise-java-developers
   
2. Make use of the following link mentioned below to configure Maven

    Maven Configuration -> https://www.tutorialspoint.com/maven/maven_environment_setup.htm
    
3. SQL Server is essential to perform the database operations. 

4. Clone the repository to your local workspace

5. Import the project to Eclipse using the Existing Maven Project Option

6. Update the application.properties file with the SQL Database information and the privateKey value based on your preference. Private key is used for generating the JSON Web Token for authentication

   Execute the scripts in the above mentioned Database in the order as below. Please find the scripts in the attachments folder.

	create scripts.sql <br/>
	insert scripts.sql
  

7. Open commannd prompt in the project folder and run the following command mentioned below to start the REST API

      mvn spring-boot:run
      
8. Integrate the REST API to the WEB APP to launch the Movie Review Portal Application. Refer the following repository to setup the WEB APP in your local

    https://github.com/Nijanthan08/movies-web-app

9. A Spring Boot Batch Application is needed for processing User Reviews and update the Database with Top Rated Movies in Real-Time

    https://github.com/Nijanthan08/movies-spring-boot-batch


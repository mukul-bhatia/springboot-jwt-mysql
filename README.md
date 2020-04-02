                                                    JWT with MySQL Authentication

Note: 
1) This procedure assumes that you have already installed JDK1.8 and MySQL database on your machine.
2) Here IDE used is "Spring Tool Suite - 4". Database name is "database_users" and table name is "role". You can change as per your requirement.

Objective: This jar file authenticates the "username" and "password" provided by the user with the one present in the MySQL database. It returns a JWT token on successful authentication and denies access on unsuccessful authentication.
This token is a necessity to every secured request made to server. The server expects this token in "Header" and decodes it to identify and validate the user.

Steps:

Step 1: Create a MySQL database with the name "users_database" and create a following table":

create table role(
   id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   role VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id )
);

Step 2: Now insert the data into the table.
Note: Here "Hashing" is done to encode the password. So we are using the encoded value in the password field. The password is "password".

INSERT INTO `role` (`username`,`password`,`role`) VALUES ('strong','$2a$10$5e3dB36HeRcozRgp8xQfw.tfD3Qsut8xu/NT9g/DSpVKg9Kzuitrq','ROLE_USER');

Step 3: Now download the project and import it on your IDE.
To Import :
a) Extract the project
b) Open your IDE. Go to File ->  Import -> Existing Maven Project -> Next .
c) In "Root Directory", browse to the extract folder location.
d) Click Finish.

Step 4: Configure your database details in "src/main/resources/application.properties" file i.e. database username, password and name.

Step 5: Now to run the project, select the project name. Right Click on it and select Run As -> Spring Boot App.
Once it starts running by showing "Completed initialization", open "Postman" to test the API's.

Step 6:
This jar contains 2 API's. One is to generate the JWT token and the other is to verify secured access through token.
a) To generate the token :
URL : http://localhost:8080/authenticate
Method: POST
Body -> raw -> JSON
{
	"username":"strong",
	"password":"password"
}
Click Send and it returns a jwt token.

b) Copy the token and make a new request :
URL : http://localhost:8080/hello
Method: GET
Headers -> Authorization : Bearer COPIED_TOKEN
Click Send and you are able to access the "/hello" API.

c) Now in the "Headers", uncheck the "Authorization" (this means that you are trying to access the "/hello" API without the token). 
Click Send and you can see that you are forbidden to access the "/hello" API.

d) You can also test the "/authenticate" API by passing the incorrect credentials in the body and you wil see that the access is denied and the token is not generated.


Thats All Folks...!!!

# Parking Lots

Simple Java(17) application which contains two REST endpoints, one is for getting back closest parking lot 
based on given latitude and longitude and second one is to calculate number of parking lots
for a given latitude, longitude and radius. Radius is not required and default value is `1.0` 
(which means 1 km).

Application use Postgres database to store data (recommend Postgres 12). The data is in the CSV 
file located in main resource package. There is separate endpoint that will trigger saving data 
from CSV file to database and endpoint which will delete all records.
 

## Running locally
### Setting local database

 1. Download the PostgreSQL installer from the website: 
 https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
 2. Start the installation process.
 3. Follow the on-screen instructions to install PostgreSQL. You can choose the default settings 
 or customize them according to your preferences.
 4. During the installation, you will be prompted to select a password for the default PostgreSQL 
 user `postgres`. Set password to `postgres` as well. 
 5. After the installation is complete, open the `pgAdmin` application. This is a graphical 
 user interface for managing PostgreSQL databases.
 6. In the `pgAdmin` window, on the left side you should see `Servers`. Right click on `Servers`
 and select `Register > Server...`. In `General` tab set server name (e.g. `LOCAL`). In `Connection`
 tab set `localhost` for host name and `postgres` for password, rest of the stuff should be 
 autofilled *(check screenshot)*.
 ![general-connection](https://user-images.githubusercontent.com/36564983/229992584-69bd9902-84cd-4e08-9d32-bd55095a77b6.png)
 7. When you extend `LOCAL` server you should see created `postgres` database with `public` schema.
 Right click on `Database` and select `Create > Database...`. Set database name `parking_lots` and 
 `postgres` owner and then save *(check screenshot)*. 
![create-database](https://user-images.githubusercontent.com/36564983/230041690-0f0e67c3-10b7-4928-947e-f93dfc47db2f.png)
 8. You should now see `parking_lots` database with `public` schema. If you see that, you are 
 officially set the database. If you don't see `public` schema, right click on `parking_lots`
 database and select `Query Tool`. Execute `CREATE SCHEMA IF NOT EXISTS public;` from `Query Tool`.
 
### Run project from IDE (recommend IntelliJ IDEA)

 1. Clone github repository, choose any directory where you want to save. <br/> 
 `git clone https://github.com/markobozic88/parking-lots`
 2. Install Gradle build tool. Find instruction on https://gradle.org/install/ 
 3. Open IntelliJ IDEA IDE and open cloned project. Wait couple moments for Gradle to download 
 all dependencies.
 4. Setup project in your IDE and build it. Some of the stuff I needed to setup: 
    * Set Gradle JVM to Java 17 *(check screenshot)* - `File > Settings... > Build, Execution... > Build Tools > Gradle`,
    * Set Run/Debug Configuration *(check screenshot)* - `Run > Edit Configuration...`.
![idea-setup](https://user-images.githubusercontent.com/36564983/230045685-50761ea8-0aa3-48b2-8482-f8afe3ef5efa.png)
 5. After build pass, you should be able to run the project locally. By default, application will
 run on port 8080, but you can choose any port you like by changing `server.port` property in 
 application.properties file.
 6. When project is up and running you should see two new tables in database, one for storing CSV file 
 and second for Flyway migration. Make sure you have that.
 
### Swagger

When application is up and running locally, you should be able to open this two Swagger documentation links:
* http://localhost:{PORT}/v2/api-docs - Swagger documentation in JSON format,
* http://localhost:{PORT}/swagger-ui.html - Swagger UI.
 
# Enjoy!







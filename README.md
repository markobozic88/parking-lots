# Parking Lots

Simple Java(17) application which contains two REST endpoints, one is for getting back closest parking lot 
based on given latitude and longitude and second one is to calculate number of parking lots
for a given latitude, longitude and radius. Radius is not required and efault value is `1.0` 
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
 user `postgres`. Set password to `postgres` as well. You will need this later to access 
 the database.
 5. After the installation is complete, open the `pgAdmin` application. This is a graphical 
 user interface for managing PostgreSQL databases.
 6. In the `pgAdmin` window, on the left side you should see `Servers`. Right click on `Servers`
 and select `Register > Server...`. In `General` tab set server name (e.g. `LOCAL`). In `Connection`
 tab set `localhost` for host name and `postgres` for password, rest of the stuff should be 
 autofilled.
 ![general-connection](https://user-images.githubusercontent.com/36564983/229992584-69bd9902-84cd-4e08-9d32-bd55095a77b6.png)
 7. When you extend `LOCAL` server you should see created `postgres` database with `public` schema.
 Right click on `Database` and select `Create > Database...`. Set database name `parking_lots` and 
 `postgres` owner and then save. 
 ![create-database](https://user-images.githubusercontent.com/36564983/229993845-0fea362e-6417-46b6-aeca-3d7939945134.png)
 8. You should see now `parking_lots` database with `public` schema. If you see that you are 
 officially set the database. If you don't see `public` schema, right click on `parking_lots`
 database and select `Query Tool`. Execute `CREATE SCHEMA IF NOT EXISTS public;` from `Query Tool`.
 
### Run project from IDE (recommend IntelliJ IDEA)

 1. Clone github repository, choose any directory where you want to save. <br/> 
 `git clone https://github.com/markobozic88/parking-lots`
 2. Install Gradle build tool. Find instruction on https://gradle.org/install/ 
 3. Open IntelliJ IDEA IDE and open cloned project. Wait couple moments for Gradle to download 
 all dependencies.
 4. 






# :notes:Record Shop API:notes:
## Project Overview

Welcome to the Record Shop API! No longer is our Record Shop a place of the past. With our new Record Shop API, we now have a computerised inventory system which can store all albums we have and how many are in stock. It is a Spring Boot application which uses a RESTful API to perform CRUD operations on a PostgreSQL database.

### Project Requirements

As the Record Shop, I want the backend API endpoints to be able to return and interact with album data in the following way:

* List all albums
* Get album by id
* List all albums by a given artist
* List all albums by a given release year
* List all albums by a given genre
* Get album information by album name
* Add new albums into the database
* Update album details
* Delete albums from the database

## Getting Started

### Installing

Clone this repo using `git clone https://github.com/AbiPetheram/record-shop`

Create an application.properties file in the resources folder within the java folder. 

Create a PostgreSQL database, using pgAdmin for this recommended. The database can be hosted locally or remotely e.g using an AWS RDS PostgreSQL database.

Add the following details to the applications.properties file to set up the application:
```
# Application set up
spring.application.name=record-shop
spring.cache.cache-names=getAllAlbumCache, getAlbumCache
springdoc.show-actuator=true
springdoc.swagger-ui.path=/api/v1/album/swagger-ui.html

# Database set up
spring.datasource.url=jdbc:postgresql://{host_address}/{your_database_name}
spring.datasource.username={your_username}
spring.datasource.password={your_password}
spring.jpa.database=POSTGRESQL
spring.jpa.hibernate.ddl-auto=update
```


### Running Program

Type the command `mvn package`

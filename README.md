# Employees
A mini server which stores details about employees.<br> Using MySQL database and Spring-Boot.

## Endpoints:
* **/get/{id}** - returns the employee (JSON format) with the given ID.
* **/get** - returns a list of all of the employees.
* **/delete** - deletes the employee with the given id (passed as a QueryParam).
* **/save** - saves the given employee (passed as a JSON in the request body).
* **/update** - updates the given employee (passed as a JSON in the request body).

## Environmental variable
* **MYSQL_HOST** - default is *localhost*
* **MYSQL_PORT** - default is *3306*
* **MYSQL_DB** - default is *db*
* **MYSQL_USERNAME** - default is *root*
* **MYSQL_PASSWORD** - default is *password123*

## OneToOne relationship
Each employee has a unique address, so we are implementing the one to one relationship using the JPA annotations.<br>
The Employee has a FK field - the primary key of the address (in a different table).

## Rest Consumer - Third party
In this project we are also consumers of the Http-Rest-Api using RestTemplate.
We are using a micro-service that converts USD to ILS - we are getting the salaries from the user in USD (when the user passes an employee to save through the /save endpoint) and we save them to the database in ILS.<br>
[https://free.currconv.com](https://free.currconv.com)/<br>
In order for it to work we need to generate a Free API Key and then put the key in app.properties.

## Profiles
**"dev":**
* Saving the users in a **local HashMap** (not persistence).
* Using a **mock** currency converter service - not consuming.

**"prod":**
* Saving the users in **MySQL database** (persistence).
* Consuming a **real** Http-Rest-Api using RestTemplate.

**"default"**
* Saving the users in **MySQL database** (persistence).
* Using a **mock** currency converter service - not consuming.

## Actuator
We are exposing 2 extra endpoints - ***"/actuator/beans"*** and ***"/actuator/health"*** using the actuator tool - a tool that helps you to provide information about your service using endpoints.

## Cofiguring properties
We are configuring the following properties using the app.properties file:
* **Service Port** - We are configuring the port of our service using the ***server.port*** property.
* **Database URL** - We are configuring the database URL using the ***spring.datasource.url*** property.
* **Database Username** - We are configuring the database username using the ***spring.datasource.username*** property.
* **Database Password** - We are configuring the database password using the ***spring.datasource.password*** property.
* **Max Number Of Employees** - We are configuring the max number of employees that the server can save using the ***max.employees*** property.
* **Exposing endpoints of the Actuator tool** - We are exposing the endpoints by configuring the ***management.endpoints.web.exposure.include*** property.
* **URL of the third party currency converter service** - We are configuring the url of the third party currency converter service URL using the ***thirdpary.currencyconverter.url*** property.
* **Free API key of the third party currency converter service** - We are configuring the api key of the third party currency converter service URL using the ***thirdpary.currencyconverter.apikey*** property.

## Aspect
We are using aspects in this project,<br>before every handling of a request from the user (before every function in the controller - a function that represent the endpoint)<br>we are logging using *SpringLogger* and *SpringAOP* - Spring aspect oriented programming.

## Filter
We are using filter which helps us to perform a logic before every processing of a request and after getting the response.<br>
We are logging information about the headers of the response. Also, we are adding to our responses a funny header.

## Interceptor
We are using interceptor in order to log before forwarding the request to the controller, after getting the response and before forwarding it to the user, and after finishing all.

## ExceptionHandler
We are using ExceptionHandlers in order to let the user know in case of errors.

## Docker

The employee service is very easy to install and deploy in a Docker container.
The docker creates a mysql container and a container for the app itself.

First you need to generate an .jar file of the application.<be>
You can do it easily using Maven, run the following command at the repository root folder:

 ```sh
mvn package
```
You can also use the Maven wrapper.


Then, run the following command at the repository root folder:

```sh
docker-compose up
```

You can configure the profile by editing the docker-compose.yml file.<br>
Go to the *environment* section and add 
```sh
SPRING_PROFILES_ACTIVE: <profile>
```



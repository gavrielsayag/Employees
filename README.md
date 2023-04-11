# Employees
A mini server which stores details about employees.<br> Using MySQL database and Spring-Boot.

## Endpoints:
* **/get/{id}** - returns the employee (JSON format) with the given ID.
* **/get** - returns a list of all of the employees.
* **/delete** - deletes the employee with the given id (passed as a QueryParam).
* **/save** - saves the given employee (passed as a JSON in the request body).
* **/update** - updates the given employee (passed as a JSON in the request body).

## Rest Consumer
In this project we are also consumers of the Http-Rest-Api using RestTemplate.
We are using a micro-service that converts USD to ILS - we are getting the salaries from the user in USD (when the user passes an employee to save through the /save endpoint) and we save them to the database in ILS.

## Profiles
**"dev":**
* Saving the users in a **local HashMap** (not presistence).
* Using a **mock** curenncy convertor service - not consuming.

**"prod":**
* Saving the users in **MySQL database** (presistence).
* Consuming a **real** Http-Rest-Api using RestTemplate.

## Aspect
We are using aspects in this project - beore every handling of a request from the user (before every function in the controller - a function that represent the endpoint) we are logging using SpringLogger and SpringAOP - Spring aspect oriented programming.

## Cofiguring roperties
We are configuring the following properties using the app.properties file:
* **Service Port** - We are configuring the port of our service using the ***server.port*** property.
* **Database URL** - We are configuring the database URL using the ***spring.datasource.url*** property.
* **Database Username** - We are configuring the database username using the ***spring.datasource.username*** property.
* **Database Password** - We are configuring the database password using the ***spring.datasource.password*** property.
* **Max Number Of Employees** - We are configuring the max number of employees that the server can save using the ***max.employees*** property.

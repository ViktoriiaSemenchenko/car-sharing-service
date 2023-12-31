﻿# 🚙 Car Sharing Service 🚙
Car-Sharing-Service is an innovative RESTful web application tailored to address car rental needs. Developed using Spring Boot and Java, the application strictly follows the principles of the REST architectural style, ensuring stateless communication between clients and the server. With easy registration and login processes, users can access the platform using their credentials. Notably, the application implements role-based authorization for administrators and regular users, enhancing security.

One of the unique aspects of our service is its seamless integration with the Stripe payment system, guaranteeing secure and reliable transactions for all users. This integration allows customers to pay for car rentals with maximum comfort and peace of mind. Additionally, we have integrated a Telegram bot to provide convenient notifications to users regarding rental status, payments, and any changes in car conditions. This feature ensures quick access to crucial information, enhancing the overall rental experience.

Car-Sharing-Service stands out as a contemporary and efficient tool for car rentals, delivering speed, security, and convenience to all its valued users.

## 🌐 Functionality 🌐
* User registration, login, and role-based authorization: Allows different user levels to have appropriate access and capabilities within the service.
* Multiple endpoints with user and admin access: Enables different functionalities and operations for both users and administrators.
* Telegram bot notifications: Provides users with timely updates about the rental status, payments, and changes in the condition of the cars, ensuring that users are always informed.
* Integration with Stripe payment service: Offers secure and reliable transactions for car rentals, improving the user experience and trust in the service.
* Car booking and management: Enables users to conveniently book, use, and return rental cars, with all the necessary details tracked within the service.

## ↗️ Project structure ↗️
The project follows a 3-layer architecture:

* Presentation layer (controllers)
* Application layer (services)
* Data access layer (repositories)

The project has the following package structure:

* Config: It is responsible for configuring the security rules and access restrictions for different endpoints of the application using the Spring Security framework.
* Controllers: Contains controllers for handling endpoints.
* Dto: Data transfer objects for transferring data between layers.
* Exception: Contains exception classes for handling errors within the project.
* Model: Stores information about entities and their properties.

## 💫 Technologies 💫
* Java 17
* Spring Boot 3.1.2
* Springdoc OpenAPI (Swagger) 2.2.15
* Liquibase 4.3.1
* MySQL
* Stripe Java 20.35.0
* jjwt 0.11.5
* Telegram Bots 5.5.0
* Docker 24.0.2
* Apache Maven 3.1.1

## 🏃 How to run app 🏃
* Clone this project from GitHub
* Install Postman for sending requests
* Create an empty database using a local installation of MySQL
* Open the project in your preferred Integrated Development Environment. Locate the [application.properties](src/main/resources/application.properties) and [liquibase.properties](src/main/resources/liquibase.properties) in the project. It should contain the database connection settings. Enter the appropriate values in the appropriate fields.
* Configure the Stripe API (read below how)
* Configure Docker (read below how)
* Run project by running the command "docker-compose up"!

## How to configure Stripe API
* Fork the Stripe API Postman collection from the Stripe Dev workspace to your own workspace.
* Fork the Stripe Environment template from the Stripe Dev workspace to your own workspace. Update the secret key variable with your test API secret key.
* Set up authentication by replacing the placeholder token in the collection's authorization settings with your test API secret key.
* To create a checkout session, fill out the required parameters, such as the product details and pricing, in the request body.
* Send the request and check the response status (e.g., 200 OK). You can even view the generated form in Postman.
* For further testing, use the Customers API to associate transactions with specific customers to track transaction history.

## How to configure Docker
* Install DOCKER DESKTOP from https://www.docker.com/products/docker-desktop/ and follow the setup steps
* Create an .env file at the project level and insert the necessary information using the template:

  * MYSQLDB_USER=YOUR_USER_NAME
    MYSQLDB_ROOT_PASS=YOUR_PASSWORD
    MYSQLDB_DATABASE=YOUR_DATABASE
    MYSQLDB_LOCAL_PORT=3307
    MYSQLDB_DOCKER_PORT=3306
    SPRING_LOCAL_PORT=8080
    SPRING_DOCKER_PORT=8080
    TOKEN_SIGNING_KEY=YOUR_TOKEN
    BOT_NAME=YOUR_TELEGRAM_BOT_NAME
    BOT_TOKEN= YOUR_TELEGRAM_BOT_TOKEN
* Run the following 2 commands in the terminal:
  * “mvn clean package”
  * “docker build -t car-sharing:latest  .”

Authors:
1. Artur Dviniskykh
2. Mykyta Katkov
3. Volodymyr Maliarchuk
4. Pavlo Polezhay
5. Volodymyr Rusyn
6. Nazar Balko
7. Victoriia Semenchenko (team lead)
8. Vladyslav Solianyk

<img src="https://cdn.vectorstock.com/i/preview-1x/29/89/carsharing-or-car-sharing-concept-linear-icon-vector-46372989.jpg" width="52" height="52"> CAR-SHARING-SERVICE <img src="https://cdn.vectorstock.com/i/preview-1x/29/89/carsharing-or-car-sharing-concept-linear-icon-vector-46372989.jpg" width="52" height="52">


Car-Sharing-Service is an innovative RESTful web application tailored to address car rental needs. Developed using Spring Boot and Java, the application strictly follows the principles of the REST architectural style, ensuring stateless communication between clients and the server. With easy registration and login processes, users can access the platform using their credentials. Notably, the application implements role-based authorization for administrators and regular users, enhancing security.

One of the unique aspects of our service is its seamless integration with the Stripe payment system, guaranteeing secure and reliable transactions for all users. This integration allows customers to pay for car rentals with maximum comfort and peace of mind. Additionally, we have integrated a Telegram bot to provide convenient notifications to users regarding rental status, payments, and any changes in car conditions. This feature ensures quick access to crucial information, enhancing the overall rental experience.

Car-Sharing-Service stands out as a contemporary and efficient tool for car rentals, delivering speed, security, and convenience to all its valued users.

**FEATURES**:
- User registration, login, and role-based authorization: Allows different user levels to have appropriate access and capabilities within the service.
- Multiple endpoints with user and admin access: Enables different functionalities and operations for both users and administrators.
- Telegram bot notifications: Provides users with timely updates about the rental status, payments, and changes in the condition of the cars, ensuring that users are always informed.
- Integration with Stripe payment service: Offers secure and reliable transactions for car rentals, improving the user experience and trust in the service.
- Car booking and management: Enables users to conveniently book, use, and return rental cars, with all the necessary details tracked within the service.

**PROJECT STRUCTURE**

The project follows a 3-layer architecture:

- Presentation layer (controllers)
- Application layer (services)
- Data access layer (repositories)

The project has the following package structure:
- Config: It is responsible for configuring the security rules and access restrictions for different endpoints of the application using the Spring Security framework.
- Controllers: Contains controllers for handling endpoints.
- Dto: Data transfer objects for transferring data between layers.
- Exception: Contains exception classes for handling errors within the project.
- Model: Stores information about entities and their properties.

**Technologies**
Technology	Version
- Java	17
- Spring Boot	3.1.2
- Springdoc OpenAPI (Swagger)	2.2.15
- Liquibase 4.3.1
- MySQL	
- Stripe Java	20.35.0
- jjwt	0.11.5
- Telegram Bots	5.5.0
- Docker	23.0.5
- Apache Maven	3.1.1

**HOW TO RUN THE PROJECT**
1. Download and install Docker on your machine
2. Download and install Postman on your machine.
3. Download and install MySQL Workbench on you machine.
4. You need to use Postman to send requests to the application's endpoints for testing.
5. Clone the project repository to your local machine.
6. In src/main/resources fill in the necessary information in resources/application.properties.
8. Сonfigure the connection with the database.
9. Start the services using Docker Compose: docker-compose up.

**OUR TEAM**
- Team Leader - Victoriia Semenchenko
- Developer - Nazar Balko
- Developer - Artur Dviniskykh
- Developer - Vladyslav Solianyk
- Developer - Pavlo Polezhay
- Developer - Mykyta Katkov
- Developer - Volodymyr Rusyn
- Developer - Volodymyr Maliarchuk

# Spring Boot Application with LocalStack and Docker Compose

This README provides a brief overview of the Spring Boot application, its architecture, and instructions on how to run it locally using LocalStack and Docker Compose.

## Overview

The application is a Spring Boot project that integrates with AWS services, specifically Amazon SQS, using the Spring Cloud AWS library. It is designed to run in a local development environment using LocalStack, which emulates AWS services locally, and Docker Compose for container orchestration.

## Architecture

- **Spring Boot Application**: The core of the application, built with Spring Boot, leverages the Spring Cloud AWS library for AWS service integration.
- **LocalStack**: Emulates AWS services locally, allowing for development and testing without incurring costs or requiring an AWS account.
- **Docker Compose**: Used to define and run multi-container Docker applications, including LocalStack and the Spring Boot application.

### Components

- `SqsConfig`: A configuration class that sets up a `QueueMessagingTemplate` bean for interacting with Amazon SQS.

## Running Locally

### Prerequisites

- Docker and Docker Compose installed on your machine.
- Java 8 or later installed.

### Steps

1. **Clone the Repository**: Clone this repository to your local machine.

2. **Start LocalStack with Docker Compose**: Navigate to the project directory and run the following command to start LocalStack and other required services using Docker Compose:
   
   ```bash
   docker-compose up -d
   ```
  This command starts LocalStack and any other services defined in the docker-compose.yml file.

  Run the Spring Boot Application: With LocalStack running, start the Spring Boot application from your IDE or using the command line:

  ```bash
  ./mvnw spring-boot:run
  ```
  Alternatively, you can build the application and run the JAR file:

```bash
  Copy code
    ./mvnw clean package
    java -jar target/your-application-name.jar
  ```

Interact with the Application: Once the application is running, you can interact with it through its REST endpoints or any other configured interfaces.

### Testing
Integration tests can be written to test the application's interaction with LocalStack services. Use the @Testcontainers annotation along with LocalStackContainer to start LocalStack for testing purposes. Refer to the example provided for setting up integration tests with LocalStack.

### Conclusion
This setup allows for a seamless development experience, enabling developers to work with AWS services locally without the need for an AWS account or incurring costs. Docker Compose and LocalStack provide a powerful combination for local development and testing of Spring Boot applications that interact with AWS services.

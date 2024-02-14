# Todo List Application with Spring MVC

## Overview
This project is a simple web-based todo list application implemented using Spring MVC without Spring Boot. It supports CRUD operations for managing tasks: viewing the task list, adding new tasks, editing, and deleting existing tasks. Each task includes a description and a completion status.

## Technologies
- **Backend**: Spring MVC, Hibernate for ORM.
- **Frontend**: Thymeleaf for HTML templates.
- **Database**: MySQL.
- **Additional**: The application is containerized using Docker and orchestrated with Docker Compose for simplified deployment and execution alongside its database.

## Running the Application
The application and database are configured to run in Docker containers. Docker and Docker Compose must be installed to proceed.

1. Clone the repository to your local machine.
2. Open a terminal and navigate to the project directory.
3. Launch the application and database using Docker Compose:
   
```bash
docker-compose up --build
```

This command builds the application image, launches containers for the application and the database.

## Implementation Highlights
- The application is developed without Spring Boot to deepen the understanding of configuring and setting up Spring MVC, Hibernate, and other components.
- Configuration classes are used for setting up Spring MVC, Hibernate, and database connections.
- Thymeleaf is utilized for server-side dynamic HTML page rendering.
- Paging support is integrated for handling a large number of tasks efficiently.
- Includes prepared Dockerfile and docker-compose.yml for containerizing the application and running it with a MySQL database in separate Docker containers, demonstrating ease of deployment in modern containerized environments.

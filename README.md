# Dream-Shop

A fully functional and scalable e-commerce backend application developed with Spring Boot, Spring Data JPA, Spring Security and MySQL. This project provides a RESTful API for managing e-commerce operations such as user management, product management, order processing, and payment integration.

# Features

User Management: Secure registration, login, and role-based authorization using JWT.
Product Management: CRUD operations for products, categories, and inventory management.
Order Management: Add to cart, checkout, and track order statuses.
Payment Integration: Placeholder integration with payment gateways (e.g., Stripe/PayPal).
Admin Panel: APIs for admin functionalities like managing users, products, and orders.
Database Integration: Persistent storage using Spring Data JPA and MySQL/PostgreSQL.

# Technologies Used

Spring Boot (Framework)
Spring Data JPA (Database Layer)
Spring Security with JWT (Authentication & Authorization)
Hibernate (ORM)
MySQL (Database)
RESTful APIs

# Requirements

To run the project locally, ensure you have the following installed:

Java Development Kit (JDK) 17 or higher
Maven (for dependency management)
MySQL/PostgreSQL (for database)

# Clone the Repository

git clone https://github.com/your-username/ecommerce-backend.git

# Configure Database

spring.datasource.url=jdbc:mysql://localhost:3306/your_database  
spring.datasource.username=your_username  
spring.datasource.password=your_password  
spring.jpa.hibernate.ddl-auto=update  

# Build and Run the Project

mvn clean install  

mvn spring-boot:run  



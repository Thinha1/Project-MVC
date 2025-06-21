# 📚 Student Management System with Full CRUD - Spring Boot MVC Web Application

This is a web application built with **Spring Boot MVC**, **Thymeleaf**, **MySQL**, and **Lombok**.  
The system supports full CRUD operations (Create, Read, Update, Delete) for managing students, staff, subjects, courses, and scores.  
It is intended as a learning project to understand how to build a structured Java web application using the Spring ecosystem.

---

## ✨ Main Features

- 🔹 **Staff Management**: Add, update, delete, and list staff members
- 🔹 **Student Management**: Full CRUD for student information
- 🔹 **Course Management**: Manage courses offered in the system
- 🔹 **Subject Management**: Create and update subjects per course
- 🔹 **Score Management**: Add/edit/delete student scores by subject
- 🔹 **Thymeleaf-based UI** with form validation and table display

---

## 🧰 Technologies Used

| Technology         | Purpose                                        |
|--------------------|------------------------------------------------|
| **Java 17+**        | Programming language                           |
| **Spring Boot MVC** | Simplifies Spring-based web development       |
| **Thymeleaf**       | Template engine for server-side HTML rendering|
| **MySQL**           | Relational database                           |
| **Spring Data JPA** | Simplifies data access using repositories     |
| **Hibernate**       | ORM provider used underneath Spring JPA       |
| **Lombok**          | Reduces boilerplate Java code                 |
| **Maven**           | Project build and dependency management       |

---

## 🗂️ Project Structure

src/
├── main/
│ ├── java/
│ │ └── vn.thinh.studentms/
│ │ ├── controller/ # Handles HTTP requests
│ │ ├── service/ # Business logic
│ │ ├── repository/ # Database operations
│ │ └── model/ #have 
│ │ └── entity/ # Entity classes (Student, Course, Score...)
│ └── resources/
│ ├── templates/ # HTML files with Thymeleaf
│ ├── static/ # CSS, JS (if any)
│ └── application.properties

## ⚙️ How to Run the Project

### 1️⃣ Set up the database

- Open MySQL and create the database:

```sql
CREATE DATABASE studentms;
```
```java
- Then edit application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/studentms //Make sure change your port to be fit with your machine
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

## 🔧 Future Improvements

Here are some ideas that may be added later:

- Separate login pages for Students and Staff
- Role-based access control (Spring Security)
- REST API for frontend-backend separation
- Improved UI with better design (Bootstrap, Tailwind, etc.)
- Pagination and search/filter in list pages

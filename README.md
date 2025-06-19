# 📚 Student Management System - Spring MVC Web Application

This is a simple web application for managing students and their scores.  
It is built using **Spring MVC**, **Thymeleaf**, **MySQL**, and **Lombok**.  
The project helps practice core concepts of Java web development using the MVC pattern.

---

## 🧰 Technologies Used

| Technology         | Purpose                                    |
|--------------------|--------------------------------------------|
| **Java 17+**        | Programming language                        |
| **Spring Boot MVC** | Web application framework (simplified Spring MVC) |
| **Thymeleaf**       | Template engine for rendering HTML          |
| **MySQL**           | Relational database                         |
| **Spring Data JPA** | Simplifies database access using repositories |
| **Hibernate**       | ORM provider used by Spring JPA underneath |
| **Lombok**          | Reduces boilerplate code                    |
| **Maven**           | Project management and build tool          |

---

## ✨ Main Features

- Add new students with basic info (name, gender, birth date, class...)
- Edit and update student information
- Delete students
- View a list of all students
- Add or update scores for students
- Display courses and scores per student
- Simple UI using Thymeleaf templates

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
- Then edit application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/studentms //Make sure change your port to be fit with your machine
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## 🔧 Future Improvements

Here are some ideas that may be added later:

- Separate login pages for Students and Staff
- Role-based access control (Spring Security)
- REST API for frontend-backend separation
- Improved UI with better design (Bootstrap, Tailwind, etc.)
- Pagination and search/filter in list pages

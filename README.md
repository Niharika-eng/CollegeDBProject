# Student Management System (Java + MySQL)

This is a fully functional command-line-based **Student Management System** built using **Core Java** and **MySQL**, designed to showcase strong command over object-oriented programming principles, JDBC integration, and key Java concepts such as threading, synchronization, exception handling, and file I/O.

The project was developed with simplicity in mind, while ensuring each concept is meaningfully demonstrated through clean and modular code.

---

## Features

* **Persistent Database Integration**: Uses MySQL with JDBC to manage real, persistent data across multiple tables (`students`, `professors`, `courses`, and `enrollments`).
* **Object-Oriented Design**: Demonstrates all four pillars of OOP — inheritance, encapsulation, abstraction, and polymorphism — through a well-structured class hierarchy.
* **Multithreading and Synchronization**: Simulates concurrent student enrollments using Java threads and `ExecutorService`, with `synchronized` blocks to prevent race conditions.
* **Robust Exception Handling**: Incorporates custom exceptions (e.g., `InvalidGradeException`) and gracefully handles SQL and I/O errors.
* **File Output**: Generates individual `.txt` grade reports for students, showcasing file handling in Java.
* **Extensible Structure**: Easy to enhance with features like course registration via CLI, GUI using Swing/JavaFX, or web integration.

---

## Project Structure

```
.
├── src/
│   ├── Main.java
│   ├── DatabaseManager.java
│   ├── Person.java
│   ├── Student.java
│   ├── Professor.java
│   ├── Course.java
│   ├── StudentEnrollmentTask.java
│   ├── EnrollmentJob.java
│   └── InvalidGradeException.java
├── lib/
│   └── mysql-connector-j-<version>.jar
├── GradeReport_<StudentName>.txt
└── README.md
```

---

## Concepts Demonstrated

| Concept                | Description                                                                                                                                             |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **OOP in Java**        | Inheritance (`Person → Student/Professor`), encapsulation (private/protected fields), abstraction (`DatabaseManager`), polymorphism (method overriding) |
| **JDBC**               | SQL integration using `PreparedStatement`, data persistence, proper error handling                                                                      |
| **Threading**          | Custom thread and `Runnable` classes, thread pool with `ExecutorService`                                                                                |
| **Synchronization**    | Prevented race conditions with `synchronized` blocks                                                                                                    |
| **Exception Handling** | Use of both standard and custom exceptions                                                                                                              |
| **File I/O**           | Writes per-student grade reports to `.txt` files                                                                                                        |

---

## Setup & Run Instructions

1. **Install MySQL** and set up a database named `college`.

2. **Create Tables** using the provided SQL scripts (`students`, `courses`, `professors`, `enrollments`).

3. **Insert Sample Data** manually or through SQL insert statements.

4. **Compile the Java Code**:

   ```bash
   javac -cp "lib/mysql-connector-j-<version>.jar;src" src/*.java
   ```

5. **Run the Application**:

   ```bash
   java -cp "lib/mysql-connector-j-<version>.jar;src" Main
   ```

Replace `<version>` with your actual MySQL connector jar version.

---

## Note

* This project is designed to emphasize clarity, maintainability, and educational value over complex UI or frameworks.
* All data is persistent — changes to the database through Java will reflect permanently in MySQL.

---

## About the me

Hi! I'm Niharika — a Computer Engineering student passionate about writing clean code and solving real-world problems.
This project reflects my hands-on understanding of Java, databases, and core CS principles.


---


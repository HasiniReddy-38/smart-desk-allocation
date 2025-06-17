# 🪑 Smart Desk Allocation System for Hybrid Workplaces

This is a basic Java console project that simulates a **desk booking system** for hybrid workplaces, where employees must reserve desks in advance to avoid conflicts and maximize space utilization.

---

## 🚀 Features

- Add desks to the system
- Register employees
- Book desks on specific dates
- Prevent double-booking
- View available desks for a given date
- View an employee’s booking history

---

## 🔧 Tech Stack

- **Java**: Core Java concepts (OOP, Collections, Exception Handling)
- **Java 8**: Stream API for filtering and processing
- **JDBC**: MySQL connectivity
- **MySQL**: Data storage for desks, employees, and bookings

---

## 🧠 Java Concepts Used

- **OOP**: Abstraction, Encapsulation, Inheritance, Polymorphism
- **Collections**: `List`, `Map` for storing and managing data
- **Streams**: Filtering and searching desk availability
- **Exception Handling**: Custom exceptions like `BookingException`
- **JDBC**: Connects Java with MySQL using SQL queries

---

## 🗂️ Project Structure

project/
│
├── model/ # POJO classes (Desk, Employee, Booking)
├── service/ # Business logic (BookingService.java)
├── exception/ # Custom exceptions
├── util/ # DBUtil for database connection
├── Main.java # Main menu for the console app
├── README.md
└── lib/ # MySQL JDBC connector .jar


---

## 🛠️ How to Run

1. Install MySQL and create the database and tables using provided SQL.
2. Download and add MySQL JDBC connector to the `lib/` folder.
3. Compile the project:
   ```bash
   javac -cp ".;lib/mysql-connector-j-8.3.0.jar" model/*.java service/*.java exception/*.java util/*.java Main.java

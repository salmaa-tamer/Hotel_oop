
1.INTRODUCTION

  This project focuses on building a Hotel Reservation System using Java, with an emphasis on applying Object-Oriented Programming (OOP) concepts. The goal is to design a system that manages hotel operations such as guest registration, room booking, and payment handling in a structured and organized way.
In this milestone, the main focus is on developing the backend logic only, without a graphical user interface. The system is built using well-designed classes that represent real-world entities like guests, rooms, reservations, and staff members.

2.PROBLEM DESCRIPTION

  Managing hotel reservations manually or with unstructured systems can lead to issues such as booking conflicts and difficulty tracking guests and rooms. This system was designed to organize data efficiently and allow both guests and staff to perform their tasks easily and correctly.

3.SYSTEM & DESIGN IMPLEMENTATION

  The system is built using multiple classes representing key entities. The Guest class allows users to register, log in, view available rooms, and manage their reservations (create, cancel, and checkout).
A base Staff class is used, with Admin and Receptionist inheriting from it. The Admin handles managing rooms, room types, and amenities (CRUD operations), while the Receptionist is responsible for reservation-related tasks such as check-in and check-out.
Rooms are represented by a Room class, which is associated with a RoomType and a list of Amenity objects. The Reservation class stores booking details including guest, room, dates, and status. The Invoice class handles payment information during checkout.
All data is stored using an in-memory database implemented with ArrayLists for guests, rooms, reservations, and invoices.

4.OOP CONCEPTS 
Encapsulation: Attributes are private and accessed through getters and setters.
Inheritance: Admin and Receptionist extend the Staff class 
Enums: Used for gender, roles, reservation status, and payment methods.
Validation & Exceptions: Ensures correct input and handles errors like unavailable rooms. 

5.DESIGN DECISIONS
Several design decisions were made to keep the system organized, clear, and easy to extend.
5.1Separation of responsibilities
Each class was designed to represent a single real-world entity (Guest, Room, Reservation, etc.), and each class handles its own logic. This makes the system easier to understand, test, and modify without affecting other parts. 

5.2 Use of inheritance for staff roles
A base Staff class was created to store common attributes such as username and password, while Admin and Receptionist extend it. This avoids code duplication and allows shared behavior to be reused while still supporting role-specific functionality. 

5.3 Use of enums instead of strings
Enums were used for values like gender, roles, reservation status, and payment methods. This prevents invalid inputs and makes the code more readable and safer compared to using plain strings. 

5.4 In-memory database for simplicity
Instead of connecting to an external database, ArrayLists were used to store system data. This simplifies development and allows easy testing of functionality without dealing with database setup. 

5.5 Encapsulation and data protection:
All attributes are private and accessed through getters and setters. This ensures that data is not modified directly and allows validation to be applied when values are updated. 

5.6 Clear relationships between classes:
Objects are connected logically (e.g., a Reservation links a Guest and a Room). This reflects real-world relationships and makes the system more intuitive. 

5.7 Basic validation and exception handling:
Input validation and custom exceptions were added to handle cases like invalid data or unavailable rooms. This improves system reliability and prevents unexpected behavior.

6.CONCLUSION
This milestone resulted in a well-structured backend system that simulates hotel operations effectively. It provides a strong foundation for adding a graphical interface and more advanced features in the next stage

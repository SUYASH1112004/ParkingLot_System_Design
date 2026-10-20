# Parking Lot Automation System

A console-based **Parking Lot Automation System** built using Java, Object-Oriented Programming (OOP), Low-Level Design (LLD), and popular design patterns. The project simulates vehicle entry, parking spot allocation, ticket generation, parking fee calculation, payment processing, and vehicle exit.

## Overview

The system manages a multi-floor parking facility named **Mall ParkEngine**. It supports different vehicle types, tracks parking availability, generates unique parking tickets, and calculates parking charges based on the vehicle type and parking duration.

The project focuses on applying software design principles to build a modular, maintainable, and extensible system.

## Features

- **Vehicle Management:** Supports cars, bikes, and trucks.
- **Multi-Floor Parking:** Manages multiple floors and their parking spots.
- **Automatic Spot Allocation:** Allocates the first available spot suitable for the vehicle.
- **Parking Ticket Management:** Generates tickets with unique ticket numbers and entry timestamps.
- **Vehicle Search:** Searches for currently parked vehicles using their vehicle numbers.
- **Parking Availability:** Displays available spots by vehicle category.
- **Dynamic Pricing Strategies:** Supports normal and weekend pricing implementations.
- **Multiple Payment Methods:** Supports cash, UPI, and card payment strategies.
- **Entry and Exit Gates:** Handles vehicle entry, ticket generation, billing, and parking spot release.
- **Duplicate Vehicle Prevention:** Prevents the same vehicle from being parked multiple times.
- **Real-Time Display Updates:** Updates parking display boards when spot availability changes.

## Design Patterns Implemented

### 1. Singleton Pattern
**Class:** `ParkingLot`

Ensures that the application uses a single shared parking lot instance through the `getInstance()` method.

### 2. Factory Pattern
**Class:** `VehicleFactory`

Centralizes vehicle object creation and returns the appropriate `Bike`, `Car`, or `Truck` object based on the selected vehicle type.

### 3. Observer Pattern
**Classes:** `ParkingObserver`, `ParkingDisplayBoard`, `ParkingFloor`

Automatically notifies registered observers when parking spot availability changes, allowing display boards to show updated availability.

### 4. Strategy Pattern
**Interfaces:** `ParkingStrategy`, `PricingStrategy`, `PaymentStartegy`

- **Parking Strategy:** Determines how available parking spots are selected.
- **Pricing Strategy:** Calculates parking charges using normal or weekend pricing.
- **Payment Strategy:** Supports different payment methods through a common interface.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Low-Level Design (LLD)
- Java Collections Framework
- Java Date and Time API
- Design Patterns

## Project Structure

The current implementation contains multiple classes in a single Java source file.

```text
ParkingLotAutomationSystem/
├── ParkingLotProject.java
└── README.md
```

The source file contains the vehicle hierarchy, parking spot hierarchy, parking floors, display boards, strategies, tickets, entry and exit gates, the parking lot controller, and the main application.

## How to Run

### Prerequisites
- Java Development Kit (JDK) 21 or a compatible JDK.
- A terminal or Java IDE.

### Steps

1. Clone the repository:

   ```bash
   git clone <your-repository-url>
   ```

2. Navigate to the project directory:

   ```bash
   cd ParkingLotAutomationSystem
   ```

3. Compile the Java source file:

   ```bash
   javac ParkingLotProject.java
   ```

4. Run the application:

   ```bash
   java ParkingLotProject
   ```

## Application Menu

The application provides the following console operations:

1. Park Vehicle
2. Exit Vehicle
3. Search Vehicle
4. Display Parking Lot
5. Exit Application

## Learning Objectives

- Understand and implement Low-Level Design concepts.
- Apply SOLID-oriented design principles and separation of responsibilities.
- Implement commonly used design patterns in Java.
- Use abstraction, inheritance, interfaces, and polymorphism.
- Design a modular parking management system.
- Practice object-oriented problem solving through a real-world use case.

## Future Enhancements

- Introduce separate Java files and packages for better modularity.
- Add unit tests using JUnit.
- Implement persistent storage using a database.
- Add configurable parking rates and time-based pricing.
- Introduce concurrent parking operations and stronger thread safety.
- Develop a graphical user interface or REST API.

## Author

Developed as a hands-on project to learn and practice **System Design, Low-Level Design (LLD), Object-Oriented Programming, and Design Patterns in Java**.

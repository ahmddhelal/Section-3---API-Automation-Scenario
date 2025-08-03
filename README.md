# REST Assured API Automation - Reqres.in

This project demonstrates API automation using REST Assured with Lombok and Jackson to test endpoints from [https://reqres.in](https://reqres.in).

## Features
- POST - Create user
- PUT - Update user
- GET - Get user by ID
- DELETE - Delete user

## Tech Stack
- Java 11+
- REST Assured
- TestNG
- Lombok
- Jackson

## Project Structure
- `builders/` – Builds reusable request objects
- `models/request/` – POJOs for request bodies
- `models/response/` – POJOs for response bodies
- `services/` – Request execution logic
- `tests/` – TestNG test classes

##  How to Run

1. Clone the repo
2. Import it as a Maven project
3. Run tests using:
   ```bash
   mvn test

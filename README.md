# Hackaton Project

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)
![Docker](https://img.shields.io/badge/Container-Docker-blue?logo=docker)

## Overview
The Hackaton project is a Spring Boot-based microservice application designed for mortgage account management. It includes features like account creation, transaction management, and mortgage handling, leveraging lightweight Dockerized deployment for efficient performance.

## Features
- 🏦 Create and manage savings accounts.
- 🏠 Manage mortgage accounts.
- 💸 Transfer funds between accounts.
- 🐳 Lightweight Dockerized deployment using OpenJDK Alpine.

---

## Requirements
- **Java 17**
- **Maven 3.8+**
- **Docker** (optional for containerization)
- **MySQL** (or other database, based on configuration)

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/hackaton.git
cd hackaton


spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hackaton
    username: your-username
    password: your-password
  jpa:
    hibernate:
      ddl-auto: update


mvn clean package
java -jar target/hackaton-1.0.0.jar

Setup Instructions
1. Clone the Repository
bash
Copy code
git clone https://github.com/yourusername/hackaton.git
cd hackaton
2. Configure the Database
Create a database named hackaton in MySQL.
Update the database connection details in src/main/resources/application.yml:
yaml
Copy code
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hackaton
    username: your-username
    password: your-password
  jpa:
    hibernate:
      ddl-auto: update
3. Build the Application
bash
Copy code
mvn clean package
4. Run the Application
bash
Copy code
java -jar target/hackaton-1.0.0.jar
The application will be available at http://localhost:8080.

Docker Deployment
Build Docker Image
bash
Copy code
docker build -t hackaton-app .
Run Docker Container
bash
Copy code
docker run -p 8080:8080 hackaton-app
The application will be available at http://localhost:8080.

API Endpoints
Account Endpoints
POST /api/accounts - Create a new savings account.
GET /api/accounts/{id} - Retrieve account details.
Mortgage Endpoints
POST /api/mortgages - Create a new mortgage.
GET /api/mortgages/{id} - Retrieve mortgage details.
Transaction Endpoints
POST /api/transactions/transfer - Transfer funds between accounts.
Running Tests
To execute unit tests:

bash
Copy code
mvn test
Project Structure
bash
Copy code
src
├── main
│   ├── java
│   │   └── com.hcl.hackaton
│   │       ├── controller    # Controllers for handling API requests
│   │       ├── entity        # Entity classes for database interaction
│   │       ├── repository    # JPA repositories
│   │       ├── service       # Service interfaces and implementations
│   │       └── dto           # Data Transfer Objects
│   └── resources
│       ├── application.yml   # Application configuration
│       └── schema.sql        # Initial database schema (if any)
└── test                      # Unit and integration tests
Tech Stack
Java 17: Application runtime.
Spring Boot: Framework for building the microservice.
Hibernate/JPA: ORM for database interaction.
MySQL: Relational database for storing data.
Docker: Containerization for lightweight deployment.
JUnit: Testing framework.
Contributing
Fork the repository.
Create a feature branch.
Commit your changes.
Push to the branch.
Open a pull request.

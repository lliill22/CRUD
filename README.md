# CRUD Application with PostgreSQL

This is a CRUD (Create, Read, Update, Delete) application built with Spring Boot and PostgreSQL. It allows you to manage customer records on a local network.

## Getting Started

### Prerequisites

- Java 11 or higher installed on your system.
- PostgreSQL database installed and running locally.

### Installation

1. Clone the repository to your local machine:

```bash
git clone git@github.com:lliill22/CRUD.git
```

2. Configure PostgreSQL:

   - Create a new database in your local PostgreSQL instance.
   - Update the database configurations in `src/main/resources/application.properties` with your database details.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Running the Application

1. Navigate to the project root directory:

```bash
cd CRUDapp
```

2. Build and run the application:

```bash
./mvnw spring-boot:run
```

The application will start, and you can access it via `http://localhost:8080`.

## Usage

- Create a new customer: Send a POST request to `http://localhost:8080` with a JSON body containing customer details.
- Get a customer by ID: Send a GET request to `http://localhost:8080?id={id}`.
- Edit a customer: Send a PUT request to `http://localhost:8080` with a JSON body containing the updated customer details.
- Delete a customer: Send a DELETE request to `http://localhost:8080?id={id}`.

## Contributing

Feel free to contribute to this project by opening issues or pull requests. Any feedback or improvements are welcome!

## License

This project is licensed under the [MIT License](LICENSE).

---

**Note:** Make sure to replace placeholders like `your-username`, `your_database`, `your_username`, and `your_password` with your actual values.

Enjoy using the CRUD application! If you have any questions or need further assistance, feel free to reach out.

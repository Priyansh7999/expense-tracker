# Expense Tracker

A RESTful backend application built with Spring Boot, PostgreSQL.

## Tech Stack

- Spring Boot
- Spring Data JPA with Hibernate
- PostgreSQL (Production)
- Gradle

## Features

### API Endpoints

#### 1. Create Expense
```
POST /expenses
```

Creates a new expense record in the system. On successful creation 

**Request Body:**
```json
{
  "title": "Lunch",
  "description": "Office lunch",
  "categoryId": 1,
  "paymentMethod": "UPI",
  "amount": 150,
  "transactionDate": "2026-02-15"
}
```

**Response:**
```json
{
  "amount": 150.0,
  "category": {
    "id": 1,
    "name": "FOOD"
  },
  "createdAt": "2026-02-15T21:48:19.190224",
  "description": "Office lunch",
  "id": 7,
  "paymentMethod": "UPI",
  "title": "Lunch",
  "transactionDate": "2026-02-15"
}
```

#### 2. Get All Expenses
```
GET /expenses
```

Retrieves all expense records from the database.

**Response:**
```json
[
  {
    "amount": 150.0,
    "category": {
      "id": 1,
      "name": "FOOD"
    },
    "createdAt": "2026-02-15T21:48:19.190224",
    "description": "Office lunch",
    "id": 7,
    "paymentMethod": "UPI",
    "title": "Lunch",
    "transactionDate": "2026-02-15"
  }
]
```

#### 3. Get Expense by ID
```
GET /expenses/{id}
```

Fetches a specific expense record using its unique identifier. 

**Response:**
```json
{
  "amount": 150.0,
  "category": {
    "hibernateLazyInitializer": {},
    "id": 1,
    "name": "FOOD"
  },
  "createdAt": "2026-02-15T21:48:19.190224",
  "description": "Office lunch",
  "id": 7,
  "paymentMethod": "UPI",
  "title": "Lunch",
  "transactionDate": "2026-02-15"
}
```

#### 4. Update Expense (Partial Update)
```
PATCH /expenses/{id}
```

Allows modification of one or more fields of an existing expense.

**Request Body (Example - updating only amount and category):**
```json
{
  "amount": 11000,
  "category": "RENT"
}
```

**Response:**
```json
{
  "amount": 11000.0,
  "category": {
    "hibernateLazyInitializer": {},
    "id": 1,
    "name": "FOOD"
  },
  "createdAt": "2026-02-15T21:48:19.190224",
  "description": "Office lunch",
  "id": 7,
  "paymentMethod": "UPI",
  "title": "Lunch",
  "transactionDate": "2026-02-15"
}
```

#### 5. Delete Expense
```
DELETE /expenses/{id}
```
Permanently removes an expense record from the database. 

**Response (Success):**
```
HTTP 204 No Content
(empty body)
```

## Validation

Uses Jakarta Bean Validation annotations and service-level checks.

**Rules:**
- Title: required, max 100 characters
- Description: required, max 1000 characters
- Amount: must be positive
- Category: valid enum value
- Payment method: valid enum value
- Transaction date: cannot be future date



## Exception Handling

Global exception handler using `@RestControllerAdvice`.

**Handles:**
- `MethodArgumentNotValidException` : Catches @Valid failure
- `ResourceNotFoundException` : Catches ResourceNotFoundException
- `IllegalArgumentException` :  Catches IllegalArgumentException
- `MethodArgumentTypeMismatchException`Catches invalid query params
- `HttpMessageNotReadableException` :  Catches malformed JSON

**Error Response Format:**
```json
{
  "message": "..."
}
```
## How to Run the Project
### Prerequisites
Make sure you have these installed before starting:
- Java
- PostgreSQL
- pgAdmin

### 1. Clone the Repository

```bash
git clone https://github.com/Priyansh7999/expense-tracker.git
cd expense-tracker
```

### 2. Database Setup

#### Step 1: Install PostgreSQL
Download and install PostgreSQL from (https://www.postgresql.org/download/)

#### Step 2: Create Database using pgAdmin
1. Open pgAdmin
2. Connect to your PostgreSQL server
2. Create database name: expense_db

#### Step 3: Configure application.properties
```env
spring.application.name=expense-tracker
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_db
spring.datasource.username=postgres
spring.datasource.password=YOUR-PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.defer-datasource-initialization=true

```
### 3. Start the Server
```bash
./gradlew run
```

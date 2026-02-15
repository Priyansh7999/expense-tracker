# Expense Tracker Lite

A RESTful backend application built with Spring Boot, PostgreSQL, and H2 for testing.

## Tech Stack

- Spring Boot
- Spring Data JPA with Hibernate
- PostgreSQL (Production)
- H2 In-Memory Database (Testing)
- Gradle

## Features

### API Endpoints

#### 1. Create Expense
```
POST /expenses
```

Creates a new expense record in the system. The request body must include all required fields with valid data. On successful creation, returns the created expense with a generated ID and a `201 Created` status.

**Request Body:**
```json
{
  "title": "Grocery Shopping",
  "description": "Weekly groceries from supermarket",
  "amount": 150.50,
  "category": "FOOD",
  "paymentMethod": "CREDIT_CARD",
  "transactionDate": "2024-02-10"
}
```

**Response:**
```json
{
  "id": 1,
  "title": "Grocery Shopping",
  "description": "Weekly groceries from supermarket",
  "amount": 150.50,
  "category": "FOOD",
  "paymentMethod": "CREDIT_CARD",
  "transactionDate": "2024-02-10",
  "createdAt": "2024-02-10T14:30:00"
}
```

**Validations:**
- All fields are mandatory
- Title cannot exceed 100 characters
- Amount must be greater than 0
- Category must be a valid enum value
- Payment method must be a valid enum value
- Transaction date cannot be in the future

#### 2. Get All Expenses
```
GET /expenses
```

Retrieves all expense records from the database. Returns an array of expense objects. If no expenses exist, returns an empty array with `200 OK` status.

**Response:**
```json
[
  {
    "id": 1,
    "title": "Grocery Shopping",
    "description": "Weekly groceries",
    "amount": 150.50,
    "category": "FOOD",
    "paymentMethod": "CREDIT_CARD",
    "transactionDate": "2024-02-10",
    "createdAt": "2024-02-10T14:30:00"
  },
  {
    "id": 2,
    "title": "Gas Station",
    "description": "Fuel for car",
    "amount": 45.00,
    "category": "TRANSPORTATION",
    "paymentMethod": "DEBIT_CARD",
    "transactionDate": "2024-02-11",
    "createdAt": "2024-02-11T09:15:00"
  }
]
```

#### 3. Get Expense by ID
```
GET /expenses/{id}
```

Fetches a specific expense record using its unique identifier. Returns the expense details if found, otherwise throws a `404 Not Found` error.

**Response (Success):**
```json
{
  "id": 1,
  "title": "Grocery Shopping",
  "description": "Weekly groceries",
  "amount": 150.50,
  "category": "FOOD",
  "paymentMethod": "CREDIT_CARD",
  "transactionDate": "2024-02-10",
  "createdAt": "2024-02-10T14:30:00"
}
```

**Response (Not Found):**
```json
{
  "timestamp": "2024-02-10T15:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Expense not found with id: 999",
  "path": "/expenses/999"
}
```

#### 4. Update Expense (Partial Update)
```
PATCH /expenses/{id}
```

Allows modification of one or more fields of an existing expense. Only provided fields are updated; others remain unchanged. This endpoint supports partial updates, meaning you don't need to send all fields—only the ones you want to change.

**Updatable Fields:**
- `title`
- `description`
- `amount`
- `category`
- `paymentMethod`
- `transactionDate`

**Request Body (Example - updating only amount and category):**
```json
{
  "amount": 175.75,
  "category": "GROCERIES"
}
```

**Response:**
```json
{
  "id": 1,
  "title": "Grocery Shopping",
  "description": "Weekly groceries",
  "amount": 175.75,
  "category": "GROCERIES",
  "paymentMethod": "CREDIT_CARD",
  "transactionDate": "2024-02-10",
  "createdAt": "2024-02-10T14:30:00",
  "updatedAt": "2024-02-10T16:45:00"
}
```

**Validations:**
- If provided, title cannot be blank or exceed 100 characters
- If provided, amount must be positive
- If provided, category must be valid
- If provided, payment method must be valid
- If provided, transaction date cannot be future date
- At least one field must be provided for update

#### 5. Delete Expense
```
DELETE /expenses/{id}
```

Permanently removes an expense record from the database. Returns `204 No Content` on successful deletion. If the expense doesn't exist, returns `404 Not Found`.

**Response (Success):**
```
HTTP 204 No Content
(empty body)
```

**Response (Not Found):**
```json
{
  "timestamp": "2024-02-10T15:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Expense not found with id: 999",
  "path": "/expenses/999"
}
```

## Architecture
```
Controller → Service → Repository → Database
```

**Controller Layer**
- Handles HTTP requests
- Validates DTOs
- Returns HTTP responses

**Service Layer**
- Business logic
- Additional validations
- Domain-specific exceptions

**Repository Layer**
- Spring Data JPA
- Database persistence

**DTO Layer**
- API models separate from entities
- Validation constraints

## Validation

Uses Jakarta Bean Validation annotations and service-level checks.

**Rules:**
- Title: required, max 100 characters
- Description: required
- Amount: must be positive
- Category: valid enum value
- Payment method: valid enum value
- Transaction date: cannot be future date

Invalid requests return `400 Bad Request` with error details.

## HTTP Status Codes

| Operation | Status Code |
|-----------|-------------|
| Create Success | 201 Created |
| Fetch Success | 200 OK |
| Update Success | 200 OK |
| Delete Success | 204 No Content |
| Validation Failure | 400 Bad Request |
| Not Found | 404 Not Found |
| Server Error | 500 Internal Server Error |

## Exception Handling

Global exception handler using `@RestControllerAdvice`.

**Handles:**
- `MethodArgumentNotValidException`
- `ResourceNotFoundException`
- `IllegalArgumentException`
- Generic exceptions

**Error Response Format:**
```json
{
  "timestamp": "...",
  "status": 400,
  "error": "Validation Failed",
  "message": "...",
  "path": "/expenses"
}
```

## Testing Strategy

| Layer | Approach |
|-------|----------|
| Service | Unit tests with Mockito |
| Repository | @DataJpaTest with H2 |
| Controller | MockMvc tests |
| Integration | H2 in-memory database |

H2 database used for fast, isolated tests. PostgreSQL for production.

## Git Workflow

**Main Branches:**
- `main` - Production-ready code
- `develop` - Integration branch

**Feature Branches:**
- `feature/create-expense`
- `feature/get-expenses`
- `feature/update-expense`
- `feature/delete-expense`
- `feature/validation-handling`

**Process:**
1. Create feature branch from `develop`
2. Implement with TDD
3. Commit changes
4. Merge to `develop`
5. Merge to `main` when stable

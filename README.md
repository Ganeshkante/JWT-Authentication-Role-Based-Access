# 🛡️ JWT Authentication & Role-Based Access (Spring Boot)

## 📘 Overview
This is a simple **Spring Boot backend** implementing **JWT authentication** with **role-based access control** for `USER` and `ADMIN` roles.  
It is built as part of the **OTRAS (One Time Registration Application System)** evaluation task.

---

## ⚙️ Tech Stack
- **Java 21**  
- **Spring Boot 3.5.7**  
- **Spring Security**  
- **Spring Data JPA**  
- **MySQL Database**  
- **JWT (io.jsonwebtoken 0.11.5)**  

---

## 🚀 Features
- User **Registration** with password encryption (`BCrypt`)
- User **Login** with JWT token generation  
- **Role-based Authorization**
  - `/user/**` → USER or ADMIN  
  - `/admin/**` → ADMIN only  
- **Stateless Authentication** (no session stored)
- Token **expiry** for enhanced security  

---

## 🔒 JWT Token Details
- Token generated on login contains:
  - **Username** (as subject)
  - **Role** (`USER` or `ADMIN`)
  - **Issued At** timestamp
  - **Expiration Time:** **1 hour**
- After expiry, you must log in again to get a new token.
- Token must be sent in request header:
Authorization: Bearer <token>


---

## 🧩 API Endpoints

| Method | Endpoint           | Description              | Access        |
|--------|--------------------|--------------------------|----------------|
| POST   | `/auth/register`   | Register new user        | Public         |
| POST   | `/auth/login`      | Login and get JWT token  | Public         |
| GET    | `/user/welcome`    | Welcome message for user | USER / ADMIN   |
| GET    | `/admin/welcome`   | Welcome message for admin| ADMIN only     |

---

## 📬 Example API Calls (Postman)

### 1️⃣ Register User
POST /auth/register
{
"username": "Ganesh",
"password": "dummy",
"role": "ADMIN"
}


✅ Response:  
`User registered successfully!`

---

### 2️⃣ Login
POST /auth/login
{
"username": "Ganesh",
"password": "dummy"
}

✅ Response:
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}


---

### 3️⃣ Access Protected API
GET /admin/welcome

Header → Authorization: Bearer your_token

✅ Response:

Welcome, ADMIN!


---


📁 Project Structure

```
com.example.jwt_mini
│
├── controller
│ ├── AuthController.java # Handles registration and login
│ ├── UserController.java # Accessible by USER role
│ └── AdminController.java # Accessible by ADMIN role
│
├── service
│ └── AuthService.java # Handles authentication logic
│
├── security
│ ├── JwtFilter.java # Filters and validates JWT tokens
│ ├── JwtUtil.java # Generates and verifies tokens
│ └── SecurityConfig.java # Configures Spring Security
│
├── model
│ └── User.java # Entity class for User details
│
├── repository
│ └── UserRepository.java # Interface for database operations
│
└── JwtMiniApplication.java # Main Spring Boot application class

```

🏁 Conclusion

This project demonstrates JWT Authentication, Role-based Authorization, and Secure API design using Spring Boot.
It ensures password safety, token-based validation, and clean REST API structure — ready for deployment or integration with frontend systems.

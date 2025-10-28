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
"username": "ganesh",
"password": "dummy12",
"role": "ADMIN"
}


✅ Response:  
`User registered successfully!`

🖼️ Screenshot:

<img width="1920" height="1080" alt="Screenshot 2025-10-27 232845" src="https://github.com/user-attachments/assets/5acbd6b0-05fc-40f9-9692-09dbc6236802" />


---

### 2️⃣ Login
POST /auth/login
{
"username": "ganesh",
"password": "dummy12"
}

✅ Response:
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}

🖼️ Screenshot:

<img width="1920" height="1080" alt="Screenshot 2025-10-27 232924" src="https://github.com/user-attachments/assets/62e6ab7d-d59a-4eb0-83fc-f6699d1f08e7" />


---

### 3️⃣ Access Protected API
GET /admin/welcome

Header → Authorization: Bearer your_token

✅ Response:

Welcome, ADMIN!

🖼️ Screenshot:

<img width="1920" height="1080" alt="Screenshot 2025-10-27 233055" src="https://github.com/user-attachments/assets/b7a95373-15c0-4323-af2d-6b636d3efa8f" />


---

📸 Screenshots Folder Structure

```
src
└── main
    └── screenshots
        ├── user-role-screenshots
        │   ├── user-register.png      # POST /auth/register
        │   ├── user-login.png         # POST /auth/login
        │   ├── user-access-denied.png        # GET /user/welcome
        │   └── admin-access.png       # Access denied test for USER role
        │
        └── admin-role-screenshots
            ├── admin-register.png     # POST /auth/register (admin)
            ├── login.png              # POST /auth/login (admin)
            ├── user-access.png        # Access denied test for ADMIN role
            └── admin-access.png       # GET /admin/welcome

```

📁 Project Structure

```
com.example.jwt_mini
│
├── controller
│ ├── AuthController.java       # Handles registration and login
│ ├── UserController.java       # Accessible by USER role
│ └── AdminController.java      # Accessible by ADMIN role
│
├── service
│ └── AuthService.java          # Handles authentication logic
│
├── security
│ ├── JwtFilter.java            # Filters and validates JWT tokens
│ ├── JwtUtil.java              # Generates and verifies tokens
│ └── SecurityConfig.java       # Configures Spring Security
│
├── model
│ └── User.java                 # Entity class for User details
│
├── repository
│ └── UserRepository.java       # Interface for database operations
│
└── JwtMiniApplication.java     # Main Spring Boot application class

```

⚡ How to Run

Clone the repository

https://github.com/Ganeshkante/JWT-Authentication-Role-Based-Access.git

Test with Postman

Register → /auth/register

Login → /auth/login

Copy token → use in Authorization header

Access → /user/welcome or /admin/welcome


🏁 Conclusion

This project demonstrates JWT Authentication, Role-based Authorization, and Secure API design using Spring Boot.
It ensures password safety, token-based validation, and clean REST API structure — ready for deployment or integration with frontend systems.

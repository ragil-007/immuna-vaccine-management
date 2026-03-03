# 💉 Immuna – Digital Immunization Management System

Immuna is a full-stack vaccine management platform that tracks vaccination history and intelligently calculates upcoming and overdue doses.

---

## 🚀 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security (JWT)
- JPA / Hibernate
- MySQL

### Frontend
- Vue.js 3
- Vue Router
- Axios
- Vite

### Database
- MySQL

---

## 📂 Project Structure

```
immuna-vaccine-management
│
├── backend/      → Spring Boot application
├── frontend/     → Vue.js application
├── database/     → SQL dump file
└── README.md
```

---

## 🔐 Demo Credentials

### 👨‍💼 Admin
Email: admin@immuna.com  
Password: admin123  

### 👤 User
Email: ragil@gmail.com  
Password: ragil123  

### 👤 User
Email: john@gmail.com  
Password: 123456

---

## 🛠️ Setup Guide

### 1️⃣ Clone Repository

```bash
git clone https://github.com/ragil-007/immuna-vaccine-management.git
cd immuna-vaccine-management
```

---

### 2️⃣ Setup Database

Create database:

```sql
CREATE DATABASE immuna;
```

Import:

```
database/immuna.sql
```

---

### 3️⃣ Run Backend

Update database credentials in:

```
backend/src/main/resources/application.properties
```

Then:

```bash
cd backend
mvn spring-boot:run
```

Runs at:

```
http://localhost:8080
```

---

### 4️⃣ Run Frontend

```bash
cd frontend
npm install
npm run dev
```

Runs at:

```
http://localhost:5173
```

---

## ✨ Features

### Admin
- Manage diseases
- Manage vaccines
- Configure dose schedules
- Manage users
- Manage immunization records

### User
- View vaccination history
- Track upcoming doses
- Overdue alerts
- Explore vaccine library

---

## 👨‍💻 Author
Ragil Raghunath
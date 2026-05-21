# 💉 Immuna – Digital Immunization Management System

Immuna is a full-stack digital vaccine management platform that helps users track immunization history, monitor upcoming doses, and manage vaccine schedules intelligently.

The system supports secure authentication, role-based access control, vaccine scheduling, and immunization record management through a modern responsive UI.

---

# 🌐 Live Demo

## Frontend
https://immuna-vaccine-management.vercel.app

## Backend API
https://immuna-vaccine-management.onrender.com

---

# 🚀 Tech Stack

## Backend
- Java 17
- Spring Boot
- Spring Security (JWT Authentication)
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Docker

## Frontend
- Vue.js 3
- Vue Router
- Axios
- Vite

## Database
- MySQL (Aiven Cloud)

## Deployment
- Render (Backend)
- Vercel (Frontend)
- Aiven MySQL (Database)

---

# 📂 Project Structure

```text
immuna-vaccine-management
│
├── backend/              → Spring Boot backend
├── frontend/             → Vue.js frontend
├── database/             → SQL dump / schema
└── README.md
```

---

# ✨ Features

## 👨‍💼 Admin Features
- Manage diseases
- Manage vaccines
- Configure vaccine dose schedules
- Manage users
- Manage immunization records
- Track vaccination schedules

## 👤 User Features
- User registration & login
- Secure JWT authentication
- View vaccination history
- Track upcoming doses
- Overdue vaccination alerts
- Explore vaccine library

---

# 🔐 Demo Credentials

## 👨‍💼 Admin
Email: admin@immuna.com  
Password: admin123

---

## 👤 User
Email: ragil@gmail.com  
Password: ragil123

---

## 👤 User
Email: john@gmail.com  
Password: 123456

---

# 🛠️ Local Setup Guide

## 1️⃣ Clone Repository

```bash
git clone https://github.com/ragil-007/immuna-vaccine-management.git
cd immuna-vaccine-management
```

---

# 🗄️ Backend Setup

## 2️⃣ Configure Database

Create MySQL database:

```sql
CREATE DATABASE immuna;
```

Import SQL file from:

```text
database/immuna.sql
```

Update database configuration inside:

```text
backend/immuna/src/main/resources/application.properties
```

---

## 3️⃣ Run Backend

```bash
cd backend/immuna
mvn spring-boot:run
```

Backend runs at:

```text
http://localhost:8080
```

---

# 🎨 Frontend Setup

## 4️⃣ Install Dependencies

```bash
cd frontend/immuna-frontend
npm install
```

---

## 5️⃣ Configure API URL

Create `.env` file inside:

```text
frontend/immuna-frontend
```

Add:

```env
VITE_API_URL=http://localhost:8080/api
```

---

## 6️⃣ Run Frontend

```bash
npm run dev
```

Frontend runs at:

```text
http://localhost:5173
```

---

# 🔒 Security Features

- JWT-based authentication
- Role-based authorization
- Password encryption using BCrypt
- Protected API routes
- Secure REST APIs
- CORS configuration for production deployment

---

# ☁️ Deployment Architecture

| Service | Platform |
|---|---|
| Frontend | Vercel |
| Backend | Render |
| Database | Aiven MySQL |
| Containerization | Docker |

---

# 📸 Screenshots

## Admin Dashboard
- Manage vaccines
- Manage schedules
- Manage immunization records

## User Dashboard
- Track vaccinations
- Upcoming dose reminders
- Vaccine history

---

# 🧠 Future Improvements

- Email reminders for upcoming doses
- Analytics dashboard
- PDF vaccination reports
- Mobile responsive enhancements
- Search and filtering
- Docker Compose setup
- CI/CD GitHub Actions

---

# 👨‍💻 Author

## Ragil K P

- GitHub: https://github.com/ragil-007
- LinkedIn: https://www.linkedin.com/in/ragil-raghunath/

---
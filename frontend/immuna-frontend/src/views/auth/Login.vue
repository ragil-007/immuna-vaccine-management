<template>
  <div class="login-container">
    <div class="login-card">

      <h2 class="login-title">Login</h2>

      <form @submit.prevent="handleLogin">

        <div class="form-group">
          <input
            type="email"
            placeholder="Email"
            v-model="email"
            required
          />
        </div>

        <div class="form-group">
          <input
            type="password"
            placeholder="Password"
            v-model="password"
            required
          />
        </div>

        <button class="login-btn" type="submit">
          Login
        </button>

        <p class="switch-link">
          Don’t have an account?
          <router-link to="/register">Register</router-link>
        </p>

        <p v-if="errorMessage" class="error-msg">
          {{ errorMessage }}
        </p>

      </form>

    </div>
  </div>
</template>

<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"
import axios from "axios"
import { jwtDecode } from "jwt-decode"

const email = ref("")
const password = ref("")
const errorMessage = ref("")
const router = useRouter()

async function handleLogin() {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/auth/login",
      {
        email: email.value,
        password: password.value
      }
    )

    const token = response.data.token
    const decoded = jwtDecode(token)
    const role = decoded.role

    localStorage.setItem("token", token)
    localStorage.setItem("role", role)

    console.log("DECODED:", decoded)

    if (role === "ADMIN") {
      router.push("/admin")
    } else if (role === "USER") {
      router.push("/user")
    }

  } catch (error) {
    errorMessage.value = "Invalid email or password"
  }
}
</script>

<style scoped>

/* ============================= */
/* Page Layout */
/* ============================= */
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #0B5ED7, #1E3A8A);
  font-family: 'Inter', sans-serif;
  padding: 20px;
}

/* ============================= */
/* Card - Slightly Bigger */
/* ============================= */
.login-card {
  background: #ffffff;
  width: 360px;              /* Slightly bigger */
  padding: 35px 35px;
  border-radius: 14px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
}

/* ============================= */
/* Title */
/* ============================= */
.login-title {
  text-align: center;
  margin-bottom: 28px;
  font-size: 22px;
  font-weight: 600;
  color: #1F2937;
}

/* ============================= */
/* Center Form Properly */
/* ============================= */
form {
  width: 100%;
}

/* ============================= */
/* Inputs */
/* ============================= */
.form-group {
  margin-bottom: 18px;
  display: flex;
  justify-content: center;  /* Ensures centering */
}

.form-group input {
  width: 100%;
  padding: 11px 14px;
  border-radius: 8px;
  border: 1px solid #DDE2F1;
  font-size: 14px;
  background-color: #F9FBFF;
  transition: 0.2s ease;
}

.form-group input:focus {
  outline: none;
  border-color: #0B5ED7;
  box-shadow: 0 0 0 2px rgba(11, 94, 215, 0.15);
  background-color: #FFFFFF;
}

/* ============================= */
/* Button */
/* ============================= */
.login-btn {
  width: 100%;
  padding: 11px;
  background: #0B5ED7;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
  transition: 0.2s ease;
}

.login-btn:hover {
  background: #084298;
}

/* ============================= */
/* Error */
/* ============================= */
.error-msg {
  margin-top: 15px;
  padding: 8px;
  background: #F8D7DA;
  color: #842029;
  text-align: center;
  font-size: 13px;
  border-radius: 6px;
}

/* ============================= */
/* Register Link */
/* ============================= */
.switch-link {
  margin-top: 20px;
  text-align: center;
  font-size: 14px;
  color: #4B5563;
}

.switch-link a {
  color: #0B5ED7;
  font-weight: 600;
  text-decoration: none;
}

.switch-link a:hover {
  text-decoration: underline;
}

/* ============================= */
/* Mobile */
/* ============================= */
@media (max-width: 480px) {
  .login-card {
    width: 95%;
    padding: 30px 22px;
  }
}

</style>
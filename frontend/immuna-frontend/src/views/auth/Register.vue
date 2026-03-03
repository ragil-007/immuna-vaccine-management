<template>
  <div class="auth-container">
    <div class="auth-card">

      <h2>Create Account</h2>

      <form @submit.prevent="handleRegister">

        <div class="form-group">
          <input type="text"
                 placeholder="Full Name"
                 v-model="fullName"
                 required />
        </div>

        <div class="form-group">
          <input type="date"
                 v-model="dob"
                 required />
        </div>

        <div class="form-group">
          <input type="email"
                 placeholder="Email"
                 v-model="email"
                 required />
        </div>

        <div class="form-group">
          <input type="password"
                 placeholder="Password"
                 v-model="password"
                 required />
        </div>

        <div class="form-group">
          <input type="password"
                 placeholder="Confirm Password"
                 v-model="confirmPassword"
                 required />
        </div>

        <button type="submit" class="btn-primary">
          Register
        </button>

        <p v-if="errorMessage" class="error-msg">
          {{ errorMessage }}
        </p>

        <p class="switch-link">
          Already have an account?
          <router-link to="/login">Login</router-link>
        </p>

      </form>

    </div>
  </div>
</template>

<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"
import axios from "axios"

const router = useRouter()

const fullName = ref("")
const dob = ref("")
const email = ref("")
const password = ref("")
const confirmPassword = ref("")
const errorMessage = ref("")

async function handleRegister() {

  if (password.value !== confirmPassword.value) {
    errorMessage.value = "Passwords do not match"
    return
  }

  try {
    await axios.post("http://localhost:8080/api/auth/register", {
      fullName: fullName.value,
      dob: dob.value,
      email: email.value,
      password: password.value
    })

    alert("Registration successful! Please login.")
    router.push("/login")

  } catch (error) {
    errorMessage.value =
      error.response?.data?.message || "Registration failed"
  }
}
</script>

<style scoped>
/* ============================= */
/* Page Layout - Same as Login */
/* ============================= */
.auth-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #0B5ED7, #1E3A8A);
  font-family: 'Inter', sans-serif;
  padding: 20px;
}

/* ============================= */
/* Card - Same Size as Login */
/* ============================= */
.auth-card {
  background: #ffffff;
  width: 360px;
  padding: 35px 35px;
  border-radius: 14px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
}

/* ============================= */
/* Title */
/* ============================= */
h2 {
  text-align: center;
  margin-bottom: 28px;
  font-size: 22px;
  font-weight: 600;
  color: #1F2937;
}

/* ============================= */
/* Form */
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
  justify-content: center;
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
/* Register Button */
/* ============================= */
.btn-primary {
  width: 100%;
  padding: 11px;
  background: #198754;   /* Medical green */
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
  transition: 0.2s ease;
}

.btn-primary:hover {
  background: #157347;
}

/* ============================= */
/* Error Message */
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
/* Switch Link */
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
  .auth-card {
    width: 95%;
    padding: 30px 22px;
  }
}
</style>
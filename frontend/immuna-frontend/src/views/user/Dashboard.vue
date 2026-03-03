<template>
  <div class="dashboard-container">
    <h2>My Vaccination Dashboard</h2>

    <div v-if="loading" class="loading">
      Loading dashboard...
    </div>

    <div v-else-if="dashboard">

      <!-- NEXT DOSE CARD -->
      <div class="card next-card">

        <div class="countdown">
          <div
            class="days"
            :class="{ overdue: dashboard.daysRemaining < 0 }"
          >
            {{ Math.abs(dashboard.daysRemaining) }}
          </div>

          <div class="label">
            {{ dashboard.daysRemaining >= 0 ? "Days Remaining" : "Days Overdue" }}
          </div>
        </div>

        <div class="details">
          <h3>{{ dashboard.vaccineName }}</h3>

          <p>
            Dose {{ dashboard.doseNo }} • {{ dashboard.doseType }}
          </p>

          <p>
            Next Date:
            <strong>{{ dashboard.nextDoseDate }}</strong>
          </p>

          <span
            class="status"
            :class="dashboard.status.toLowerCase()"
          >
            {{ dashboard.status }}
          </span>
        </div>

      </div>

      <!-- RECENT DOSE CARD -->
      <div class="card recent-card">
        <h3>Most Recent Vaccination</h3>

        <p><strong>{{ dashboard.recentVaccineName }}</strong></p>
        <p>Dose {{ dashboard.recentDoseNo }}</p>
        <p>Taken on {{ dashboard.recentDateTaken }}</p>
      </div>

    </div>

    <div v-else class="error">
      Unable to load dashboard.
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"

const dashboard = ref(null)
const loading = ref(true)

const token = localStorage.getItem("token")

async function loadDashboard() {
  try {
    const res = await axios.get(
      "http://localhost:8080/api/user/dashboard",
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    )

    dashboard.value = res.data
  } catch (err) {
    console.error("Dashboard error:", err.response?.data || err)
  } finally {
    loading.value = false
  }
}

onMounted(loadDashboard)
</script>

<style scoped>

/* ========================================= */
/* PAGE */
/* ========================================= */
.dashboard-container {
  max-width: 900px;
}

.dashboard-container h2 {
  font-size: 24px;
  font-weight: 700;
  color: #0F172A;
  margin-bottom: 30px;
}

/* ========================================= */
/* CARD BASE */
/* ========================================= */
.card {
  background: white;
  padding: 28px;
  border-radius: 18px;
  border: 1px solid #E5E7EB;
  box-shadow: 0 10px 24px rgba(0,0,0,0.04);
  margin-bottom: 28px;
  transition: 0.25s ease;
}

.card:hover {
  transform: translateY(-4px);
  box-shadow: 0 16px 32px rgba(0,0,0,0.06);
}

/* ========================================= */
/* NEXT DOSE CARD */
/* ========================================= */
.next-card {
  display: flex;
  align-items: center;
  gap: 40px;
}

/* Countdown block */
.countdown {
  min-width: 120px;
  text-align: center;
}

.days {
  font-size: 56px;
  font-weight: 800;
  color: #1E40AF; /* brand color */
  line-height: 1;
}

.days.overdue {
  color: #DC2626;
}

.label {
  margin-top: 6px;
  font-size: 13px;
  color: #64748B;
  font-weight: 500;
  letter-spacing: 0.3px;
}

/* Details */
.details h3 {
  margin: 0 0 6px 0;
  font-size: 20px;
  font-weight: 600;
  color: #0F172A;
}

.details p {
  margin: 6px 0;
  font-size: 14px;
  color: #475569;
}

/* ========================================= */
/* STATUS BADGE */
/* ========================================= */
.status {
  display: inline-block;
  padding: 6px 16px;
  border-radius: 999px;
  font-size: 12px;
  font-weight: 600;
  margin-top: 10px;
  letter-spacing: 0.4px;
}

/* Upcoming */
.status.upcoming {
  background: #DBEAFE;
  color: #1E40AF;
}

/* Due today */
.status.due {
  background: #FEF3C7;
  color: #B45309;
}

/* Overdue */
.status.overdue {
  background: #FEE2E2;
  color: #DC2626;
}

/* ========================================= */
/* RECENT CARD */
/* ========================================= */
.recent-card h3 {
  font-size: 18px;
  margin-bottom: 14px;
  color: #0F172A;
}

.recent-card p {
  font-size: 14px;
  color: #475569;
  margin: 6px 0;
}

/* ========================================= */
/* STATES */
/* ========================================= */
.loading,
.error {
  padding: 30px;
  border-radius: 16px;
  background: white;
  border: 1px solid #E5E7EB;
  color: #64748B;
  box-shadow: 0 8px 20px rgba(0,0,0,0.03);
  text-align: center;
}

/* ========================================= */
/* RESPONSIVE */
/* ========================================= */
@media (max-width: 768px) {

  .next-card {
    flex-direction: column;
    text-align: center;
  }

  .countdown {
    margin-bottom: 20px;
  }
}

</style>
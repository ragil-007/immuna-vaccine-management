<template>
  <div>
    <h2>Admin Dashboard</h2>

    <div class="cards">

      <div class="card">
        <h3>Total Users</h3>
        <p>{{ stats.totalUsers }}</p>
      </div>

      <div class="card">
        <h3>Total Vaccines</h3>
        <p>{{ stats.totalVaccines }}</p>
      </div>

      <div class="card">
        <h3>Total Diseases</h3>
        <p>{{ stats.totalDiseases }}</p>
      </div>

      <div class="card">
        <h3>Total Immunization Records</h3>
        <p>{{ stats.totalImmunizationRecords }}</p>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { getDashboardSummary } from "../../api/adminService"

const stats = ref({
  totalUsers: 0,
  totalVaccines: 0,
  totalDiseases: 0,
  totalImmunizationRecords: 0
})

onMounted(async () => {
  try {
    const data = await getDashboardSummary()
    stats.value = data
  } catch (err) {
    console.error("Error loading dashboard:", err)
  }
})
</script>

<style scoped>
/* ===================================================== */
/* PAGE TITLE */
/* ===================================================== */
h2 {
  font-size: 26px;
  font-weight: 600;
  color: #111827;
  margin-bottom: 28px;
}

/* ===================================================== */
/* DASHBOARD GRID */
/* ===================================================== */
.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 24px;
}

/* ===================================================== */
/* STAT CARD */
/* ===================================================== */
.card {
  background: #FFFFFF;
  padding: 28px;
  border-radius: 16px;
  border: 1px solid #E5E7EB;
  box-shadow: 0 6px 20px rgba(0,0,0,0.04);
  position: relative;
  transition: all 0.25s ease;
}

/* Subtle hover lift */
.card:hover {
  transform: translateY(-4px);
  box-shadow: 0 12px 30px rgba(0,0,0,0.06);
}

/* Top accent line */
.card::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 5px;
  width: 100%;
  background: #0B5ED7;
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;
}

/* Card Title */
.card h3 {
  font-size: 14px;
  font-weight: 500;
  color: #6B7280;
  margin-bottom: 12px;
}

/* Stat Number */
.card p {
  font-size: 32px;
  font-weight: 600;
  color: #111827;
  margin: 0;
}

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {
  .cards {
    gap: 16px;
  }

  .card {
    padding: 22px;
  }

  .card p {
    font-size: 26px;
  }
}
</style>
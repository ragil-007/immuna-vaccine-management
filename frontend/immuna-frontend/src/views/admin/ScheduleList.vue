<template>
  <div class="page-container">

    <div class="header-row">
      <h2>Vaccine Schedules</h2>
      <button class="btn-primary" @click="goToAdd">
        + Add Schedule
      </button>
    </div>

    <div class="card">
      <table class="styled-table">
        <thead>
          <tr>
            <th>Vaccine</th>
            <th>Dose</th>
            <th>Gap (Days)</th>
            <th>Type</th>
            <th>Recurring</th>
            <th>Max Occ</th>
            <th class="center">Actions</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="s in schedules" :key="s.scheduleId">
            <td>{{ s.vaccineName }}</td>
            <td>{{ s.doseNo }}</td>
            <td>{{ s.gapDays }}</td>

            <td>
              <span 
                class="badge"
                :class="s.doseType === 'PRIMARY' ? 'badge-primary' : 'badge-booster'"
              >
                {{ s.doseType }}
              </span>
            </td>

            <td>
              <span :class="s.isRecurring ? 'text-success' : 'text-muted'">
                {{ s.isRecurring ? "Yes" : "No" }}
              </span>
            </td>

            <td>{{ s.maxOccurrences }}</td>

            <td class="center">
              <button class="btn-edit" @click="edit(s.scheduleId)">Edit</button>
              <button class="btn-delete" @click="remove(s.scheduleId)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>

      <div v-if="schedules.length === 0" class="empty">
        No schedules found.
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"
import { useRouter } from "vue-router"

const router = useRouter()
const token = localStorage.getItem("token")
const schedules = ref([])

async function loadSchedules() {
  const res = await axios.get(
    "https://immuna-vaccine-management.onrender.com/api/admin/schedules",
    { headers: { Authorization: `Bearer ${token}` } }
  )
  schedules.value = res.data
}

function goToAdd() {
  router.push("/admin/schedules/add")
}

function edit(id) {
  router.push(`/admin/schedules/edit/${id}`)
}

async function remove(id) {
  if (!confirm("Are you sure you want to delete this schedule?")) return

  await axios.delete(
    `https://immuna-vaccine-management.onrender.com/api/admin/schedules/${id}`,
    { headers: { Authorization: `Bearer ${token}` } }
  )

  loadSchedules()
}

onMounted(loadSchedules)
</script>

<style scoped>
/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.page-container {
  width: 100%;
}

/* ===================================================== */
/* HEADER ROW */
/* ===================================================== */
.header-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 28px;
}

.header-row h2 {
  font-size: 24px;
  font-weight: 600;
  color: #111827;
  margin: 0;
}

/* PRIMARY BUTTON */
.btn-primary {
  background: #0B5ED7;
  color: #FFFFFF;
  padding: 10px 18px;
  border-radius: 8px;
  border: none;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-primary:hover {
  background: #0A53BE;
}

/* ===================================================== */
/* CARD */
/* ===================================================== */
.card {
  background: #FFFFFF;
  border: 1px solid #E5E7EB;
  border-radius: 16px;
  padding: 24px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.03);
  overflow-x: auto;
}

/* ===================================================== */
/* TABLE */
/* ===================================================== */
.styled-table {
  width: 100%;
  border-collapse: collapse;
}

.styled-table thead th {
  text-align: left;
  font-size: 14px;
  font-weight: 600;
  color: #374151;
  padding: 14px 10px;
  border-bottom: 1px solid #E5E7EB;
}

.styled-table tbody td {
  padding: 14px 10px;
  font-size: 14px;
  color: #374151;
  border-bottom: 1px solid #F3F4F6;
  vertical-align: middle;
}

/* Row Hover */
.styled-table tbody tr:hover {
  background: #F9FAFB;
}

.center {
  text-align: center;
}

/* ===================================================== */
/* BADGES */
/* ===================================================== */
.badge {
  display: inline-block;
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 500;
}

.badge-primary {
  background: #E6F0FF;
  color: #0B5ED7;
}

.badge-booster {
  background: #FEE2E2;
  color: #DC3545;
}

/* ===================================================== */
/* STATUS TEXT */
/* ===================================================== */
.text-success {
  color: #16A34A;
  font-weight: 500;
}

.text-muted {
  color: #9CA3AF;
}

/* ===================================================== */
/* ACTION BUTTONS */
/* ===================================================== */
.btn-edit {
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 6px 12px;
  border-radius: 6px;
  border: none;
  font-size: 13px;
  font-weight: 500;
  cursor: pointer;
  margin-right: 8px;
  transition: 0.2s ease;
}

.btn-edit:hover {
  background: #D0E2FF;
}

.btn-delete {
  background: #FEE2E2;
  color: #DC3545;
  padding: 6px 12px;
  border-radius: 6px;
  border: none;
  font-size: 13px;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-delete:hover {
  background: #FCA5A5;
}

/* ===================================================== */
/* EMPTY STATE */
/* ===================================================== */
.empty {
  padding: 30px 0;
  text-align: center;
  color: #9CA3AF;
  font-size: 14px;
}

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {

  .header-row {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }

  .card {
    padding: 18px;
  }

  .styled-table {
    font-size: 13px;
  }
}
</style>
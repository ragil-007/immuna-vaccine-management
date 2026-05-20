<template>
  <div class="page-container">

    <div class="header">
      <h2>Immunization Records</h2>

      <router-link
        to="/admin/immunizations/add"
        class="btn-add"
      >
        + Add Record
      </router-link>
    </div>

    <div class="card">
      <table>
        <thead>
          <tr>
            <th>User</th>
            <th>Vaccine</th>
            <th>Dose</th>
            <th>Date Taken</th>
            <th>Next Due</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="record in records" :key="record.recordId">
            <td>{{ record.userName }}</td>
            <td>{{ record.vaccineName }}</td>
            <td>{{ record.doseNo }}</td>
            <td>{{ record.dateTaken }}</td>
            <td>{{ record.nextDueDate }}</td>
            <td class="actions">
              <router-link
                :to="`/admin/immunizations/edit/${record.recordId}`"
                class="btn-edit"
              >
                Edit
              </router-link>

              <button
                class="btn-delete"
                @click="deleteRecord(record.recordId)"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"

const records = ref([])
const token = localStorage.getItem("token")

async function loadRecords() {
  const res = await axios.get(
    "https://immuna-vaccine-management.onrender.com/api/admin/immunizations",
    {
      headers: { Authorization: `Bearer ${token}` }
    }
  )

  records.value = res.data
}

async function deleteRecord(id) {
  if (!confirm("Are you sure?")) return

  await axios.delete(
    `https://immuna-vaccine-management.onrender.com/api/admin/immunizations/${id}`,
    {
      headers: { Authorization: `Bearer ${token}` }
    }
  )

  loadRecords()
}

onMounted(loadRecords)
</script>

<style scoped>

/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.page-container {
  width: 100%;
}

/* ===================================================== */
/* HEADER */
/* ===================================================== */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 28px;
}

.header h2 {
  font-size: 24px;
  font-weight: 600;
  color: #111827;
  margin: 0;
}

/* ADD BUTTON */
.btn-add {
  background: #0B5ED7;
  color: #FFFFFF;
  padding: 10px 18px;
  border-radius: 8px;
  text-decoration: none;
  font-weight: 500;
  transition: 0.2s ease;
}

.btn-add:hover {
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
table {
  width: 100%;
  border-collapse: collapse;
}

thead th {
  text-align: left;
  font-size: 14px;
  font-weight: 600;
  color: #374151;
  padding: 14px 10px;
  border-bottom: 1px solid #E5E7EB;
}

tbody td {
  padding: 14px 10px;
  font-size: 14px;
  color: #374151;
  border-bottom: 1px solid #F3F4F6;
  vertical-align: middle;
}

/* Row hover */
tbody tr:hover {
  background: #F9FAFB;
}

/* ===================================================== */
/* ACTIONS */
/* ===================================================== */
.actions {
  display: flex;
  gap: 8px;
}

/* EDIT BUTTON */
.btn-edit {
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 6px 12px;
  border-radius: 6px;
  text-decoration: none;
  font-size: 13px;
  font-weight: 500;
  transition: 0.2s ease;
}

.btn-edit:hover {
  background: #D0E2FF;
}

/* DELETE BUTTON */
.btn-delete {
  background: #FEE2E2;
  color: #DC3545;
  border: none;
  padding: 6px 12px;
  border-radius: 6px;
  font-size: 13px;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-delete:hover {
  background: #FCA5A5;
}

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {

  .header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }

  .card {
    padding: 18px;
  }

  table {
    font-size: 13px;
  }

  .actions {
    flex-direction: column;
  }
}

</style>
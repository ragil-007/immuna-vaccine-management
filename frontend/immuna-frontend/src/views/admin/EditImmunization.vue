<template>
  <div class="page-container">

    <h2>Edit Immunization Record</h2>

    <div class="card">
      <form @submit.prevent="update">

        <!-- USER -->
        <div class="form-group">
          <label>User</label>

          <input
            v-model="userSearch"
            placeholder="Search user..."
            class="search-input"
          />

          <div class="dropdown">
            <div
              v-for="user in filteredUsers"
              :key="user.userId"
              class="dropdown-item"
              @click="selectUser(user)"
            >
              {{ user.fullName }}
            </div>
          </div>

          <div v-if="selectedUser" class="selected-pill">
            {{ selectedUser.fullName }}
          </div>
        </div>

        <!-- SCHEDULE -->
        <div class="form-group">
          <label>Schedule</label>

          <input
            v-model="scheduleSearch"
            placeholder="Search vaccine or dose..."
            class="search-input"
          />

          <div class="dropdown">
            <div
              v-for="schedule in filteredSchedules"
              :key="schedule.scheduleId"
              class="dropdown-item"
              @click="selectSchedule(schedule)"
            >
              {{ schedule.vaccineName }} - Dose {{ schedule.doseNo }}
            </div>
          </div>

          <div v-if="selectedSchedule" class="selected-pill">
            {{ selectedSchedule.vaccineName }}
            - Dose {{ selectedSchedule.doseNo }}
          </div>
        </div>

        <!-- DATE -->
        <div class="form-group">
          <label>Date Taken</label>
          <input type="date" v-model="dateTaken" required />
        </div>

        <div class="form-actions">
          <button class="btn-primary" type="submit">
            Update Record
          </button>

          <router-link
            to="/admin/immunizations"
            class="cancel-link"
          >
            Cancel
          </router-link>
        </div>

      </form>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue"
import axios from "axios"
import { useRouter, useRoute } from "vue-router"

const router = useRouter()
const route = useRoute()
const token = localStorage.getItem("token")
const recordId = route.params.id

const userId = ref("")
const scheduleId = ref("")
const dateTaken = ref("")

const users = ref([])
const schedules = ref([])

const userSearch = ref("")
const scheduleSearch = ref("")

const selectedUser = ref(null)
const selectedSchedule = ref(null)

async function loadData() {
  const headers = { Authorization: `Bearer ${token}` }

  const [usersRes, scheduleRes, recordRes] = await Promise.all([
    axios.get("https://immuna-vaccine-management.onrender.com/api/admin/users/normal", { headers }),
    axios.get("https://immuna-vaccine-management.onrender.com/api/admin/schedules", { headers }),
    axios.get(
      `https://immuna-vaccine-management.onrender.com/api/admin/immunizations/${recordId}`,
      { headers }
    )
  ])

  users.value = usersRes.data

  schedules.value = scheduleRes.data.sort((a, b) => {
    if (a.vaccineName === b.vaccineName) {
      return a.doseNo - b.doseNo
    }
    return a.vaccineName.localeCompare(b.vaccineName)
  })

  const record = recordRes.data

  userId.value = Number(record.userId)
  scheduleId.value = Number(record.scheduleId)
  dateTaken.value = record.dateTaken

  // Preselect user
  selectedUser.value = users.value.find(
    u => u.userId === userId.value
  )

  if (selectedUser.value) {
    userSearch.value = selectedUser.value.fullName
  }

  // Preselect schedule
  selectedSchedule.value = schedules.value.find(
    s => s.scheduleId === scheduleId.value
  )

  if (selectedSchedule.value) {
    scheduleSearch.value =
      `${selectedSchedule.value.vaccineName} - Dose ${selectedSchedule.value.doseNo}`
  }
}

const filteredUsers = computed(() =>
  users.value.filter(u =>
    u.fullName.toLowerCase().includes(userSearch.value.toLowerCase())
  )
)

const filteredSchedules = computed(() =>
  schedules.value.filter(s =>
    `${s.vaccineName} ${s.doseNo}`
      .toLowerCase()
      .includes(scheduleSearch.value.toLowerCase())
  )
)

function selectUser(user) {
  selectedUser.value = user
  userId.value = user.userId
  userSearch.value = user.fullName
}

function selectSchedule(schedule) {
  selectedSchedule.value = schedule
  scheduleId.value = schedule.scheduleId
  scheduleSearch.value =
    `${schedule.vaccineName} - Dose ${schedule.doseNo}`
}

async function update() {
  const headers = { Authorization: `Bearer ${token}` }

  await axios.put(
    `https://immuna-vaccine-management.onrender.com/api/admin/immunizations/${recordId}`,
    {
      userId: Number(userId.value),
      scheduleId: Number(scheduleId.value),
      dateTaken: dateTaken.value
    },
    { headers }
  )

  router.push("/admin/immunizations")
}

onMounted(loadData)
</script>

<style scoped>
/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.page-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 0;
}

.page-container h2 {
  width: 100%;
  max-width: 620px;
  font-size: 24px;
  font-weight: 600;
  color: #111827;
  margin-bottom: 24px;
}

/* ===================================================== */
/* CARD */
/* ===================================================== */
.card {
  background: #FFFFFF;
  width: 100%;
  max-width: 620px;
  padding: 30px;
  border-radius: 16px;
  border: 1px solid #E5E7EB;
  box-shadow: 0 6px 20px rgba(0,0,0,0.04);
}

/* ===================================================== */
/* FORM GROUP */
/* ===================================================== */
.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.form-group label {
  font-size: 14px;
  font-weight: 600;
  color: #374151;
  margin-bottom: 6px;
}

/* ===================================================== */
/* INPUTS */
/* ===================================================== */
input {
  padding: 11px 14px;
  border-radius: 10px;
  border: 1px solid #D1D5DB;
  font-size: 14px;
  background: #F9FAFB;
  transition: all 0.2s ease;
}

input:hover {
  border-color: #9CA3AF;
}

input:focus {
  outline: none;
  border-color: #0B5ED7;
  background: #FFFFFF;
  box-shadow: 0 0 0 3px rgba(11, 94, 215, 0.08);
}

/* ===================================================== */
/* SEARCH INPUT */
/* ===================================================== */
.search-input {
  margin-bottom: 8px;
}

/* ===================================================== */
/* DROPDOWN */
/* ===================================================== */
.dropdown {
  border: 1px solid #E5E7EB;
  border-radius: 10px;
  max-height: 160px;
  overflow-y: auto;
  background: #FAFBFC;
  padding: 6px;
}

/* Scrollbar */
.dropdown::-webkit-scrollbar {
  width: 6px;
}

.dropdown::-webkit-scrollbar-thumb {
  background: #D1D5DB;
  border-radius: 8px;
}

.dropdown-item {
  padding: 8px 10px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 14px;
  transition: 0.15s ease;
}

.dropdown-item:hover {
  background: #EAF2FF;
}

/* ===================================================== */
/* SELECTED PILL */
/* ===================================================== */
.selected-pill {
  margin-top: 10px;
  display: inline-block;
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 6px 14px;
  border-radius: 20px;
  font-size: 13px;
  font-weight: 500;
}

/* ===================================================== */
/* FORM ACTIONS */
/* ===================================================== */
.form-actions {
  margin-top: 28px;
  display: flex;
  align-items: center;
  gap: 16px;
}

/* PRIMARY BUTTON */
.btn-primary {
  background: #0B5ED7;
  color: #FFFFFF;
  padding: 10px 20px;
  border-radius: 8px;
  border: none;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-primary:hover {
  background: #0A53BE;
  transform: translateY(-1px);
}

/* CANCEL LINK */
.cancel-link {
  text-decoration: none;
  color: #6B7280;
  font-weight: 500;
  font-size: 14px;
  transition: 0.2s ease;
}

.cancel-link:hover {
  color: #374151;
}

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {

  .page-container {
    padding: 25px 15px;
  }

  .card {
    padding: 22px;
  }

  .form-actions {
    flex-direction: column;
    align-items: stretch;
  }

  .btn-primary {
    width: 100%;
  }

  .cancel-link {
    text-align: center;
  }
}
</style>
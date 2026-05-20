<template>
  <div class="form-container">
    <h2>Add Vaccine Schedule</h2>

    <form class="form-card" @submit.prevent="save">

      <!-- 🔎 Searchable Vaccine -->
      <div class="form-group">
        <label>Vaccine</label>

        <input
          v-model="vaccineSearch"
          placeholder="Search vaccine..."
          class="search-input"
        />

        <div class="dropdown">
          <div
            v-for="v in filteredVaccines"
            :key="v.vaccineId"
            class="dropdown-item"
            :class="{ active: vaccineId === v.vaccineId }"
            @click="selectVaccine(v)"
          >
            {{ v.vaccineName }}
          </div>
        </div>
      </div>

      <!-- Dose -->
      <div class="form-group">
        <label>Dose Number</label>
        <input type="number" v-model="doseNo" required min="1" />
      </div>

      <!-- Gap -->
      <div class="form-group">
        <label>Gap Days</label>
        <input type="number" v-model="gapDays" required min="0" />
      </div>

      <!-- Type -->
      <div class="form-group">
        <label>Dose Type</label>
        <select v-model="doseType">
          <option value="PRIMARY">PRIMARY</option>
          <option value="BOOSTER">BOOSTER</option>
        </select>
      </div>

      <!-- Recurring -->
      <div class="form-group horizontal">
        <input type="checkbox" v-model="isRecurring" />
        <label>Recurring</label>
      </div>

      <!-- Max -->
      <div class="form-group">
        <label>Max Occurrences</label>
        <input
          type="number"
          v-model="maxOccurrences"
          :disabled="!isRecurring"
          min="1"
        />
      </div>

      <button class="btn-primary" type="submit">
        Save Schedule
      </button>

    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, watch } from "vue"
import axios from "axios"
import { useRouter } from "vue-router"

const router = useRouter()
const token = localStorage.getItem("token")

const vaccines = ref([])

const vaccineId = ref(null)
const vaccineSearch = ref("")
const doseNo = ref("")
const gapDays = ref("")
const doseType = ref("PRIMARY")
const isRecurring = ref(false)
const maxOccurrences = ref(1)

/* 🔥 Recurring rule */
watch(isRecurring, (val) => {
  if (!val) maxOccurrences.value = 1
})

/* 🔎 Filter vaccines */
const filteredVaccines = computed(() =>
  vaccines.value.filter(v =>
    v.vaccineName.toLowerCase()
      .includes(vaccineSearch.value.toLowerCase())
  )
)

function selectVaccine(v) {
  vaccineId.value = v.vaccineId
  vaccineSearch.value = v.vaccineName
}

async function loadVaccines() {
  const res = await axios.get(
    "https://immuna-vaccine-management.onrender.com/api/admin/vaccines",
    { headers: { Authorization: `Bearer ${token}` } }
  )
  vaccines.value = res.data
}

async function save() {

  if (!isRecurring.value) maxOccurrences.value = 1

  await axios.post(
    "https://immuna-vaccine-management.onrender.com/api/admin/schedules",
    {
      vaccineId: vaccineId.value,
      doseNo: doseNo.value,
      gapDays: gapDays.value,
      doseType: doseType.value,
      isRecurring: isRecurring.value,
      maxOccurrences: maxOccurrences.value
    },
    { headers: { Authorization: `Bearer ${token}` } }
  )

  router.push("/admin/schedules")
}

onMounted(loadVaccines)
</script>

<style scoped>
/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.form-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 0;
}

h2 {
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
.form-card {
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

/* Horizontal checkbox */
.form-group.horizontal {
  flex-direction: row;
  align-items: center;
  gap: 10px;
}

.form-group.horizontal label {
  margin: 0;
  font-weight: 500;
  color: #374151;
}

/* ===================================================== */
/* INPUTS & SELECT */
/* ===================================================== */
input,
select {
  padding: 11px 14px;
  border-radius: 10px;
  border: 1px solid #D1D5DB;
  font-size: 14px;
  background: #F9FAFB;
  transition: all 0.2s ease;
}

input:hover,
select:hover {
  border-color: #9CA3AF;
}

input:focus,
select:focus {
  outline: none;
  border-color: #0B5ED7;
  background: #FFFFFF;
  box-shadow: 0 0 0 3px rgba(11, 94, 215, 0.08);
}

/* Disabled */
input:disabled {
  background: #F3F4F6;
  cursor: not-allowed;
}

/* ===================================================== */
/* SEARCHABLE DROPDOWN */
/* ===================================================== */
.search-input {
  margin-bottom: 8px;
}

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

/* Active selection */
.dropdown-item.active {
  background: #E6F0FF;
  color: #0B5ED7;
  font-weight: 500;
}

/* ===================================================== */
/* BUTTON */
/* ===================================================== */
.btn-primary {
  margin-top: 10px;
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

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {

  .form-container {
    padding: 25px 15px;
  }

  .form-card {
    padding: 22px;
  }

  .btn-primary {
    width: 100%;
  }

  .form-group.horizontal {
    justify-content: space-between;
  }
}
</style>
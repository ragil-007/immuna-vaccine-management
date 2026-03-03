<template>
  <div class="container">
    <h2>Edit Vaccine</h2>

    <div class="card">
      <!-- Name -->
      <div class="form-group">
        <label>Name</label>
        <input v-model="form.vaccineName" />
      </div>

      <!-- Manufacturer -->
      <div class="form-group">
        <label>Manufacturer</label>
        <input v-model="form.manufacturer" />
      </div>

      <!-- Description -->
      <div class="form-group">
        <label>Description</label>
        <input v-model="form.description" />
      </div>

      <!-- Searchable Multi Select -->
      <div class="form-group">
        <label>Select Diseases</label>

        <!-- Search -->
        <input
          v-model="search"
          placeholder="Search disease..."
          class="search-input"
        />

        <!-- Scrollable list -->
        <div class="dropdown">
          <div
            v-for="d in filteredDiseases"
            :key="d.diseaseId"
            class="dropdown-item"
            @click="toggleDisease(d.diseaseId)"
          >
            <input
              type="checkbox"
              :checked="form.diseaseIds.includes(d.diseaseId)"
              readonly
            />
            {{ d.diseaseName }}
          </div>
        </div>

        <!-- Selected tags -->
        <div class="selected-tags">
          <span
            v-for="id in form.diseaseIds"
            :key="id"
            class="tag"
          >
            {{ diseases.find(d => d.diseaseId === id)?.diseaseName }}
          </span>
        </div>
      </div>

      <div class="form-actions">
        <button class="primary-btn" @click="update">
          Update
        </button>

        <router-link to="/admin/vaccines">
          Cancel
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue"
import { useRoute, useRouter } from "vue-router"
import { getVaccines, updateVaccine } from "../../api/vaccineService"
import { getDiseases } from "../../api/diseaseService"

const route = useRoute()
const router = useRouter()

const diseases = ref([])
const search = ref("")

const form = ref({
  vaccineName: "",
  manufacturer: "",
  description: "",
  diseaseIds: []
})

/* Load everything */
onMounted(async () => {
  // 1. Load diseases
  diseases.value = await getDiseases()

  // 2. Load vaccines
  const vaccines = await getVaccines()

  const vaccine = vaccines.find(
    v => v.vaccineId == route.params.id
  )

  if (vaccine) {
    form.value.vaccineName = vaccine.vaccineName
    form.value.manufacturer = vaccine.manufacturer
    form.value.description = vaccine.description

    // IMPORTANT PART
    // backend returns diseases as names
    form.value.diseaseIds = vaccine.diseases
      .map(dName =>
        diseases.value.find(d => d.diseaseName === dName)
      )
      .filter(Boolean)
      .map(d => d.diseaseId)
  }
})

/* Search filter */
const filteredDiseases = computed(() => {
  return diseases.value.filter(d =>
    d.diseaseName
      .toLowerCase()
      .includes(search.value.toLowerCase())
  )
})

/* Toggle select */
const toggleDisease = (id) => {
  if (form.value.diseaseIds.includes(id)) {
    form.value.diseaseIds =
      form.value.diseaseIds.filter(d => d !== id)
  } else {
    form.value.diseaseIds.push(id)
  }
}

/* Update */
const update = async () => {
  await updateVaccine(route.params.id, form.value)
  router.push("/admin/vaccines")
}
</script>

<style scoped>
/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.container {
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
  margin-top: 6px;
  margin-bottom: 10px;
}

/* ===================================================== */
/* DROPDOWN MULTI SELECT */
/* ===================================================== */
.dropdown {
  max-height: 160px;
  overflow-y: auto;
  border: 1px solid #E5E7EB;
  border-radius: 10px;
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
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  transition: 0.15s ease;
}

.dropdown-item:hover {
  background: #EAF2FF;
}

.dropdown-item input {
  cursor: pointer;
}

/* ===================================================== */
/* SELECTED TAGS */
/* ===================================================== */
.selected-tags {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
}

.tag {
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 500;
}

/* ===================================================== */
/* FORM ACTIONS */
/* ===================================================== */
.form-actions {
  margin-top: 25px;
  display: flex;
  align-items: center;
  gap: 16px;
}

.primary-btn {
  background: #0B5ED7;
  color: #FFFFFF;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
}

.primary-btn:hover {
  background: #0A53BE;
  transform: translateY(-1px);
}

.form-actions a {
  text-decoration: none;
  color: #6B7280;
  font-weight: 500;
  font-size: 14px;
  transition: 0.2s ease;
}

.form-actions a:hover {
  color: #374151;
}

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {

  .container {
    padding: 25px 15px;
  }

  .card {
    padding: 22px;
  }

  .form-actions {
    flex-direction: column;
    align-items: stretch;
  }

  .primary-btn {
    width: 100%;
  }

  .form-actions a {
    text-align: center;
  }
}
</style>
<template>
  <div class="library-page">
    <h2>Vaccination Library</h2>

    <!-- SEARCH BAR -->
    <div class="search-wrapper">
      <input
        type="text"
        v-model="search"
        placeholder="Search vaccine..."
        @input="handleSearch"
        @focus="showDropdown = true"
      />

      <!-- DROPDOWN -->
      <div
        v-if="showDropdown && suggestions.length"
        class="dropdown"
      >
        <div
          v-for="item in suggestions"
          :key="item.vaccineId"
          class="dropdown-item"
          @click="selectVaccine(item)"
        >
          {{ item.vaccineName }}
        </div>
      </div>
    </div>

    <!-- LOADING -->
    <div v-if="loading" class="state-card">
      Loading vaccines...
    </div>

    <!-- EMPTY -->
    <div v-else-if="!loading && vaccines.length === 0" class="state-card">
      No vaccines found.
    </div>

    <!-- RESULTS -->
    <div v-else class="grid">
      <div
        v-for="vaccine in vaccines"
        :key="vaccine.vaccineId"
        class="vaccine-card"
      >
        <h3>{{ vaccine.vaccineName }}</h3>

        <p class="description">
          {{ vaccine.description }}
        </p>

        <p class="manufacturer">
          Manufacturer: {{ vaccine.manufacturer }}
        </p>

        <div class="diseases">
          <h4>Protects Against:</h4>

          <div
            v-for="d in vaccine.diseases"
            :key="d.diseaseName"
            class="disease-item"
          >
            <strong>{{ d.diseaseName }}</strong>
            <p>{{ d.description }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import api from "../../api/api.js"

const search = ref("")
const vaccines = ref([])
const suggestions = ref([])
const loading = ref(false)
const showDropdown = ref(false)

let debounceTimer = null

// Fetch vaccines (all or search)
async function fetchVaccines(query = "") {
  loading.value = true

  try {
    const response = await api.get("/api/library/vaccines", {
      params: query ? { search: query } : {}
    })

    vaccines.value = response.data
  } catch (error) {
    console.error("Error fetching vaccines:", error)
  } finally {
    loading.value = false
  }
}

// Handle search typing
function handleSearch() {
  clearTimeout(debounceTimer)

  debounceTimer = setTimeout(async () => {
    if (!search.value.trim()) {
      suggestions.value = []
      await fetchVaccines()
      return
    }

    try {
      const response = await api.get("/api/library/vaccines", {
        params: { search: search.value }
      })

      vaccines.value = response.data
      suggestions.value = response.data
    } catch (error) {
      console.error("Search error:", error)
    }
  }, 300)
}

// Select dropdown suggestion
function selectVaccine(vaccine) {
  search.value = vaccine.vaccineName
  vaccines.value = [vaccine]
  suggestions.value = []
  showDropdown.value = false
}

// Load all vaccines initially
onMounted(() => {
  fetchVaccines()
})
</script>

<style scoped>

/* ========================================= */
/* PAGE */
/* ========================================= */
.library-page {
  max-width: 1150px;
}

.library-page h2 {
  font-size: 24px;
  font-weight: 700;
  color: #0F172A;
  margin-bottom: 25px;
}

/* ========================================= */
/* SEARCH */
/* ========================================= */
.search-wrapper {
  position: relative;
  max-width: 420px;
  margin-bottom: 35px;
}

.search-wrapper input {
  width: 100%;
  padding: 12px 16px;
  border-radius: 12px;
  border: 1px solid #CBD5E1;
  font-size: 14px;
  background: #FFFFFF;
  transition: all 0.2s ease;
}

.search-wrapper input:focus {
  outline: none;
  border-color: #1E40AF;
  box-shadow: 0 0 0 3px rgba(30, 64, 175, 0.12);
}

/* ========================================= */
/* DROPDOWN */
/* ========================================= */
.dropdown {
  position: absolute;
  width: 100%;
  background: white;
  border-radius: 12px;
  margin-top: 6px;
  border: 1px solid #E5E7EB;
  box-shadow: 0 12px 28px rgba(0,0,0,0.08);
  z-index: 20;
  overflow: hidden;
}

.dropdown-item {
  padding: 12px 14px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.2s ease;
}

.dropdown-item:hover {
  background: #EEF2FF;
  color: #1E40AF;
}

/* ========================================= */
/* GRID */
/* ========================================= */
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(340px, 1fr));
  gap: 28px;
}

/* ========================================= */
/* CARD */
/* ========================================= */
.vaccine-card {
  background: white;
  border-radius: 18px;
  padding: 26px;
  border: 1px solid #E5E7EB;
  box-shadow: 0 10px 24px rgba(0,0,0,0.04);
  transition: all 0.25s ease;
}

.vaccine-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 16px 32px rgba(0,0,0,0.06);
}

/* Vaccine Title */
.vaccine-card h3 {
  font-size: 18px;
  font-weight: 600;
  color: #0F172A;
  margin-bottom: 8px;
}

/* Description */
.description {
  margin: 8px 0 6px 0;
  color: #475569;
  font-size: 14px;
}

/* Manufacturer */
.manufacturer {
  font-size: 13px;
  margin-bottom: 18px;
  color: #64748B;
}

/* ========================================= */
/* DISEASE SECTION */
/* ========================================= */
.diseases h4 {
  margin-bottom: 10px;
  font-size: 14px;
  font-weight: 600;
  color: #1E293B;
}

/* Disease box */
.disease-item {
  background: #F8FAFC;
  padding: 12px;
  border-radius: 12px;
  margin-bottom: 10px;
  border: 1px solid #E2E8F0;
}

.disease-item strong {
  font-size: 14px;
  color: #0F172A;
}

.disease-item p {
  font-size: 13px;
  color: #64748B;
  margin-top: 4px;
}

/* ========================================= */
/* STATES */
/* ========================================= */
.state-card {
  background: white;
  padding: 40px;
  border-radius: 18px;
  text-align: center;
  border: 1px solid #E5E7EB;
  color: #64748B;
  font-size: 14px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.03);
}

/* ========================================= */
/* RESPONSIVE */
/* ========================================= */
@media (max-width: 768px) {

  .grid {
    grid-template-columns: 1fr;
  }

  .search-wrapper {
    max-width: 100%;
  }
}

</style>
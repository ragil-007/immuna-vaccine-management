<template>
  <div class="history-page">

    <h2>Vaccination History</h2>

    <!-- TABS -->
    <div class="tabs">
      <button
        :class="{ active: activeTab === 'history' }"
        @click="activeTab = 'history'"
      >
        History
      </button>

      <button
        :class="{ active: activeTab === 'upcoming' }"
        @click="activeTab = 'upcoming'"
      >
        Upcoming
      </button>

      <button
        :class="{ active: activeTab === 'overdue' }"
        @click="activeTab = 'overdue'"
      >
        Overdue
      </button>
    </div>

    <div v-if="loading" class="loading">
      Loading...
    </div>

    <div v-else>

      <div v-if="records.length === 0" class="empty">
        {{ emptyMessage }}
      </div>

      <div
        v-else
        class="card"
        v-for="r in records"
        :key="r.recordId"
      >

        <!-- LEFT -->
        <div>
          <h3>{{ r.vaccineName }}</h3>
          <p>Dose {{ r.doseNo }} • {{ r.doseType }}</p>

          <!-- HISTORY -->
          <p v-if="activeTab === 'history'">
            Taken: {{ r.dateTaken }}
          </p>

          <!-- UPCOMING -->
          <p v-if="activeTab === 'upcoming'">
            Next Due: {{ r.nextDueDate }}
          </p>

          <!-- OVERDUE -->
          <p v-if="activeTab === 'overdue'">
            Overdue Since: {{ r.nextDueDate }}
          </p>
        </div>

        <!-- STATUS BADGE (Only for upcoming & overdue) -->
        <div v-if="activeTab !== 'history'">
          <span
            :class="[
              'status',
              activeTab === 'overdue' ? 'overdue' : 'upcoming'
            ]"
          >
            {{ activeTab.toUpperCase() }}
          </span>
        </div>

      </div>

    </div>

  </div>
</template>

<script setup>
import { ref, watch, onMounted } from "vue"
import axios from "axios"

const activeTab = ref("history")
const records = ref([])
const loading = ref(false)

const token = localStorage.getItem("token")

const emptyMessages = {
  history: "No vaccination history available.",
  upcoming: "No upcoming vaccinations 🎉",
  overdue: "No overdue vaccinations 👏"
}

const emptyMessage = ref("")

async function loadData() {
  loading.value = true

  try {
    const res = await axios.get(
      `http://localhost:8080/api/user/immunizations/${activeTab.value}`,
      {
        headers: { Authorization: `Bearer ${token}` }
      }
    )

    records.value = res.data
    emptyMessage.value = emptyMessages[activeTab.value]

  } catch (err) {
    console.error(err)
  } finally {
    loading.value = false
  }
}

watch(activeTab, loadData)
onMounted(loadData)
</script>

<style scoped>

/* ========================================= */
/* PAGE */
/* ========================================= */
.history-page {
  max-width: 950px;
}

.history-page h2 {
  font-size: 24px;
  font-weight: 700;
  color: #0F172A;
  margin-bottom: 25px;
}

/* ========================================= */
/* TABS */
/* ========================================= */
.tabs {
  display: flex;
  gap: 12px;
  margin-bottom: 30px;
}

.tabs button {
  padding: 10px 18px;
  border-radius: 10px;
  border: none;
  background: #EEF2F7;
  cursor: pointer;
  font-weight: 500;
  font-size: 14px;
  color: #374151;
  transition: all 0.2s ease;
}

.tabs button:hover {
  background: #E2E8F0;
}

.tabs button.active {
  background: #1E40AF;      /* brand color */
  color: white;
  box-shadow: 0 4px 12px rgba(30, 64, 175, 0.2);
}

/* ========================================= */
/* CARD */
/* ========================================= */
.card {
  background: white;
  border-radius: 16px;
  padding: 24px;
  margin-bottom: 18px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #E5E7EB;
  box-shadow: 0 8px 18px rgba(0,0,0,0.04);
  transition: 0.2s ease;
}

.card:hover {
  transform: translateY(-3px);
  box-shadow: 0 12px 24px rgba(0,0,0,0.06);
}

.card h3 {
  font-size: 18px;
  font-weight: 600;
  color: #0F172A;
  margin-bottom: 6px;
}

.card p {
  margin: 4px 0;
  color: #475569;
  font-size: 14px;
}

/* ========================================= */
/* STATUS BADGE */
/* ========================================= */
.status {
  padding: 6px 16px;
  border-radius: 999px;
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 0.4px;
}

.status.upcoming {
  background: #DBEAFE;
  color: #1E40AF;
}

.status.overdue {
  background: #FEE2E2;
  color: #DC2626;
}

/* ========================================= */
/* EMPTY STATE */
/* ========================================= */
.empty {
  background: white;
  padding: 40px;
  border-radius: 16px;
  text-align: center;
  border: 1px solid #E5E7EB;
  color: #64748B;
  font-size: 14px;
}

/* ========================================= */
/* LOADING */
/* ========================================= */
.loading {
  padding: 20px;
  color: #64748B;
  font-weight: 500;
}

</style>
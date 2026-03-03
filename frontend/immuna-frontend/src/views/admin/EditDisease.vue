<template>
  <div class="container">
    <h2>Edit Disease</h2>

    <div class="card">
      <div class="form-group">
        <label>Disease Name</label>
        <input v-model="form.diseaseName" />
      </div>

      <div class="form-group">
        <label>Description</label>
        <input v-model="form.description" />
      </div>

      <div class="form-actions">
        <button class="primary-btn" @click="update">
          Update
        </button>

        <router-link class="cancel-link" to="/admin/diseases">
          Cancel
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { useRoute, useRouter } from "vue-router"
import { getDiseases, updateDisease } from "../../api/diseaseService"

const route = useRoute()
const router = useRouter()

const form = ref({
  diseaseName: "",
  description: ""
})

onMounted(async () => {
  const diseases = await getDiseases()
  const disease = diseases.find(
    d => d.diseaseId == route.params.id
  )
  if (disease) {
    form.value = { ...disease }
  }
})

const update = async () => {
  await updateDisease(route.params.id, form.value)
  router.push("/admin/diseases")
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

/* PAGE TITLE */
h2 {
  width: 100%;
  max-width: 520px;
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
  max-width: 520px;
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

/* INPUT FIELD */
.form-group input {
  padding: 11px 14px;
  border-radius: 10px;
  border: 1px solid #D1D5DB;
  font-size: 14px;
  background: #F9FAFB;
  transition: all 0.2s ease;
}

/* Hover */
.form-group input:hover {
  border-color: #9CA3AF;
}

/* Focus */
.form-group input:focus {
  outline: none;
  border-color: #0B5ED7;
  background: #FFFFFF;
  box-shadow: 0 0 0 3px rgba(11, 94, 215, 0.08);
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

/* PRIMARY BUTTON */
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

  .cancel-link {
    text-align: center;
  }
}
</style>
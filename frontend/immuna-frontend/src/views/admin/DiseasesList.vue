<template>
  <div class="container">
    <div class="header">
      <h2>Diseases Management</h2>
      <router-link class="add-btn" to="/admin/diseases/add">
        + Add Disease
      </router-link>
    </div>

    <div class="card">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th style="width: 160px;">Actions</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="d in diseases" :key="d.diseaseId">
            <td>{{ d.diseaseName }}</td>
            <td>{{ d.description }}</td>
            <td>
              <router-link
                class="edit-btn"
                :to="`/admin/diseases/edit/${d.diseaseId}`">
                Edit
              </router-link>

              <button
                class="delete-btn"
                @click="confirmDelete(d)">
                Delete
              </button>
            </td>
          </tr>

          <tr v-if="diseases.length === 0">
            <td colspan="3" class="empty">
              No diseases found.
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Confirmation Modal -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <p>
          Are you sure you want to delete
          <strong>{{ selectedDisease?.diseaseName }}</strong> ?
        </p>

        <div class="modal-actions">
          <button class="delete-btn" @click="deleteConfirmed">
            Yes, Delete
          </button>
          <button class="cancel-btn" @click="showModal = false">
            Cancel
          </button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { getDiseases, deleteDisease } from "../../api/diseaseService"

const diseases = ref([])
const showModal = ref(false)
const selectedDisease = ref(null)

const loadDiseases = async () => {
  diseases.value = await getDiseases()
}

onMounted(loadDiseases)

const confirmDelete = (disease) => {
  selectedDisease.value = disease
  showModal.value = true
}

const deleteConfirmed = async () => {
  await deleteDisease(selectedDisease.value.diseaseId)
  showModal.value = false
  loadDiseases()
}
</script>

<style scoped>
/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.container {
  width: 100%;
}

/* ===================================================== */
/* HEADER SECTION */
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
}

/* ADD BUTTON */
.add-btn {
  background: #0B5ED7;
  color: #FFFFFF;
  padding: 10px 18px;
  border-radius: 8px;
  text-decoration: none;
  font-weight: 500;
  transition: 0.2s ease;
}

.add-btn:hover {
  background: #0A53BE;
}

/* ===================================================== */
/* CARD TABLE CONTAINER */
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
}

/* Row hover */
tbody tr:hover {
  background: #F9FAFB;
}

/* ===================================================== */
/* ACTION BUTTONS */
/* ===================================================== */
.edit-btn {
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 6px 12px;
  border-radius: 6px;
  font-size: 13px;
  text-decoration: none;
  font-weight: 500;
  margin-right: 8px;
  transition: 0.2s ease;
}

.edit-btn:hover {
  background: #D0E2FF;
}

.delete-btn {
  background: #FEE2E2;
  color: #DC3545;
  padding: 6px 12px;
  border: none;
  border-radius: 6px;
  font-size: 13px;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;
}

.delete-btn:hover {
  background: #FCA5A5;
}

/* ===================================================== */
/* EMPTY STATE */
/* ===================================================== */
.empty {
  text-align: center;
  padding: 30px 0;
  font-size: 14px;
  color: #9CA3AF;
}

/* ===================================================== */
/* MODAL */
/* ===================================================== */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.35);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2000;
}

.modal {
  background: #FFFFFF;
  padding: 28px;
  width: 360px;
  border-radius: 16px;
  text-align: center;
  box-shadow: 0 20px 40px rgba(0,0,0,0.15);
}

.modal p {
  font-size: 14px;
  color: #374151;
  margin-bottom: 22px;
}

.modal strong {
  color: #111827;
}

.modal-actions {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}

/* Reuse delete style for confirm */
.modal .delete-btn {
  flex: 1;
  background: #DC3545;
  color: white;
}

.modal .delete-btn:hover {
  background: #BB2D3B;
}

.cancel-btn {
  flex: 1;
  background: #E5E7EB;
  color: #374151;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  padding: 8px 12px;
  transition: 0.2s ease;
}

.cancel-btn:hover {
  background: #D1D5DB;
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
}
</style>
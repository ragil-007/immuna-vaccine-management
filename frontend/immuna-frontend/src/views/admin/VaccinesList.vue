<template>
  <div class="container">
    <div class="header">
      <h2>Vaccines Management</h2>
      <router-link class="add-btn" to="/admin/vaccines/add">
        + Add Vaccine
      </router-link>
    </div>

    <div class="card">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Manufacturer</th>
            <th>Description</th>
            <th>Diseases</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="v in vaccines" :key="v.vaccineId">
            <td>{{ v.vaccineName }}</td>

            <td>{{ v.manufacturer }}</td>

            <td>{{ v.description }}</td>

            <td>
              <span
                v-for="d in v.diseases"
                :key="d"
                class="disease-tag"
              >
                {{ d }}
              </span>
            </td>

            <td>
              <router-link
                class="edit-btn"
                :to="`/admin/vaccines/edit/${v.vaccineId}`"
              >
                Edit
              </router-link>

              <button
                class="delete-btn"
                @click="confirmDelete(v)"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Confirmation Modal -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <p>
          Delete <strong>{{ selectedVaccine?.vaccineName }}</strong> ?
        </p>

        <div class="modal-actions">
          <button class="delete-btn" @click="deleteConfirmed">
            Yes, Delete
          </button>
          <button class="cancel-btn" @click="showModal=false">
            Cancel
          </button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { getVaccines, deleteVaccine } from "../../api/vaccineService"

const vaccines = ref([])
const showModal = ref(false)
const selectedVaccine = ref(null)

const loadVaccines = async () => {
  vaccines.value = await getVaccines()
}

onMounted(loadVaccines)

const confirmDelete = (v) => {
  selectedVaccine.value = v
  showModal.value = true
}

const deleteConfirmed = async () => {
  await deleteVaccine(selectedVaccine.value.vaccineId)
  showModal.value = false
  loadVaccines()
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
  margin: 0;
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
  vertical-align: top;
}

/* Row hover */
tbody tr:hover {
  background: #F9FAFB;
}

/* ===================================================== */
/* DISEASE TAGS */
/* ===================================================== */
.disease-tag {
  display: inline-block;
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 500;
  margin: 4px 6px 0 0;
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

/* Confirm delete inside modal */
.modal .delete-btn {
  flex: 1;
  background: #DC3545;
  color: #FFFFFF;
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
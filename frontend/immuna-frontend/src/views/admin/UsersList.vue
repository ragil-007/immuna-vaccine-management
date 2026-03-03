<template>
  <div class="container">
    <h2>Users Management</h2>

    <div class="card">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th style="width:220px;">Actions</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="u in users" :key="u.userId">
            <td>{{ u.fullName }}</td>
            <td>{{ u.email }}</td>
            <td>
              <span class="role-badge">{{ u.role }}</span>
            </td>
            <td>
              <span
                :class="u.isActive ? 'status-active' : 'status-disabled'"
              >
                {{ u.isActive ? 'Active' : 'Disabled' }}
              </span>
            </td>

            <td>
                <!-- Show actions only if role is USER -->
                <template v-if="u.role === 'USER'">

                    <button
                    v-if="u.isActive"
                    class="disable-btn"
                    @click="disable(u.userId)"
                    >
                    Disable
                    </button>

                    <button
                    v-else
                    class="enable-btn"
                    @click="enable(u.userId)"
                    >
                    Enable
                    </button>

                    <button
                    class="delete-btn"
                    @click="confirmDelete(u)"
                    >
                    Delete
                    </button>

                </template>

                <!-- If ADMIN -->
                <template v-else>
                    <span class="admin-protected">
                    Protected
                    </span>
                </template>
                </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Confirm Delete Modal -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <p>
          Delete <strong>{{ selectedUser?.fullName }}</strong> ?
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
import {
  getUsers,
  enableUser,
  disableUser,
  deleteUser
} from "../../api/userService"

const users = ref([])
const showModal = ref(false)
const selectedUser = ref(null)

const loadUsers = async () => {
  users.value = await getUsers()
}

onMounted(loadUsers)

const enable = async (id) => {
  await enableUser(id)
  loadUsers()
}

const disable = async (id) => {
  await disableUser(id)
  loadUsers()
}

const confirmDelete = (u) => {
  selectedUser.value = u
  showModal.value = true
}

const deleteConfirmed = async () => {
  await deleteUser(selectedUser.value.userId)
  showModal.value = false
  loadUsers()
}
</script>

<style scoped>
/* ===================================================== */
/* PAGE CONTAINER */
/* ===================================================== */
.container {
  width: 100%;
}

.container h2 {
  font-size: 24px;
  font-weight: 600;
  color: #111827;
  margin-bottom: 28px;
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
/* ROLE BADGE */
/* ===================================================== */
.role-badge {
  background: #E6F0FF;
  color: #0B5ED7;
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 500;
}

/* ===================================================== */
/* STATUS */
/* ===================================================== */
.status-active {
  color: #16A34A;
  font-weight: 500;
}

.status-disabled {
  color: #DC3545;
  font-weight: 500;
}

/* ===================================================== */
/* ACTION BUTTONS */
/* ===================================================== */
.enable-btn,
.disable-btn,
.delete-btn {
  padding: 6px 12px;
  border-radius: 6px;
  border: none;
  font-size: 13px;
  font-weight: 500;
  cursor: pointer;
  margin-right: 8px;
  transition: 0.2s ease;
}

/* Enable (soft green) */
.enable-btn {
  background: #ECFDF5;
  color: #16A34A;
}

.enable-btn:hover {
  background: #D1FAE5;
}

/* Disable (soft amber) */
.disable-btn {
  background: #FEF3C7;
  color: #D97706;
}

.disable-btn:hover {
  background: #FDE68A;
}

/* Delete (soft red) */
.delete-btn {
  background: #FEE2E2;
  color: #DC3545;
}

.delete-btn:hover {
  background: #FCA5A5;
}

/* ===================================================== */
/* ADMIN PROTECTED */
/* ===================================================== */
.admin-protected {
  color: #9CA3AF;
  font-size: 13px;
  font-style: italic;
}

/* ===================================================== */
/* MODAL */
/* ===================================================== */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.35);
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

.modal-actions {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}

/* Modal delete button stronger */
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

  .card {
    padding: 18px;
  }

  table {
    font-size: 13px;
  }

  .enable-btn,
  .disable-btn,
  .delete-btn {
    margin-bottom: 6px;
  }
}

</style>
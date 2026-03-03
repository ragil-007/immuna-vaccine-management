<template>
  <div class="admin-layout">

    <!-- HEADER -->
    <header class="admin-header">
      <h1 class="logo">Immuna Admin</h1>

      <button class="logout-btn" @click="showLogoutModal = true">
        Logout
      </button>
    </header>

    <div class="admin-body">

      <!-- SIDEBAR -->
      <aside class="sidebar">

        <!-- Dashboard (exact match only) -->
        <router-link
          to="/admin"
          class="nav-link"
          exact-active-class="active-link"
        >
          Dashboard
        </router-link>

        <router-link
          to="/admin/diseases"
          class="nav-link"
          active-class="active-link"
        >
          Diseases
        </router-link>

        <router-link
          to="/admin/vaccines"
          class="nav-link"
          active-class="active-link"
        >
          Vaccines
        </router-link>

        <router-link
          to="/admin/schedules"
          class="nav-link"
          active-class="active-link"
        >
          Vaccine Schedules
        </router-link>

        <router-link
          to="/admin/immunizations"
          class="nav-link"
          active-class="active-link"
        >
          Immunization Records
        </router-link>

        <router-link
          to="/admin/users"
          class="nav-link"
          active-class="active-link"
        >
          Users
        </router-link>

      </aside>

      <!-- MAIN CONTENT -->
      <main class="content">
        <router-view />
      </main>

    </div>

    <!-- FOOTER -->
    <footer class="admin-footer">
      © {{ year }} Immuna System
    </footer>

    <!-- LOGOUT MODAL -->
    <div v-if="showLogoutModal" class="modal-overlay">
      <div class="modal">
        <h3>Confirm Logout</h3>
        <p>Are you sure you want to logout?</p>

        <div class="modal-actions">
          <button class="cancel-btn" @click="showLogoutModal = false">
            Cancel
          </button>

          <button class="confirm-btn" @click="confirmLogout">
            Logout
          </button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"

const router = useRouter()
const showLogoutModal = ref(false)
const year = new Date().getFullYear()

function confirmLogout() {
  localStorage.removeItem("token")
  localStorage.removeItem("role")
  showLogoutModal.value = false
  router.push("/login")
}
</script>

<style scoped>

/* ============================= */
/* LAYOUT */
/* ============================= */
.admin-layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #F8FAFC;
}

/* ============================= */
/* HEADER */
/* ============================= */
.admin-header {
  background: #FFFFFF;
  padding: 18px 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #E5E7EB;
}

.logo {
  font-size: 20px;
  font-weight: 600;
  color: #0B5ED7;
}

.logout-btn {
  background: #DC3545;
  color: white;
  padding: 8px 18px;
  border: none;
  border-radius: 8px;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;
}

.logout-btn:hover {
  background: #BB2D3B;
}

/* ============================= */
/* BODY */
/* ============================= */
.admin-body {
  flex: 1;
  display: flex;
}

/* ============================= */
/* SIDEBAR */
/* ============================= */
.sidebar {
  width: 240px;
  background: #FFFFFF;
  padding: 30px 18px;
  border-right: 1px solid #E5E7EB;
  overflow-y: auto;
}

.nav-link {
  display: block;
  padding: 12px 16px;
  margin-bottom: 8px;
  border-radius: 8px;
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  color: #374151;
  transition: 0.2s ease;
}

.nav-link:hover {
  background: #F1F5F9;
  color: #0B5ED7;
}

/* Active Link */
.active-link {
  background: #E9F2FF;
  color: #0B5ED7 !important;
  font-weight: 600;
}

/* ============================= */
/* CONTENT */
/* ============================= */
.content {
  flex: 1;
  padding: 40px;
}

/* ============================= */
/* FOOTER */
/* ============================= */
.admin-footer {
  background: #FFFFFF;
  border-top: 1px solid #E5E7EB;
  padding: 14px;
  text-align: center;
  font-size: 13px;
  color: #6B7280;
}

/* ============================= */
/* MODAL */
/* ============================= */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.45);
  backdrop-filter: blur(4px);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 3000;
  animation: fadeIn 0.2s ease;
}

.modal {
  background: #FFFFFF;
  padding: 32px;
  border-radius: 18px;
  width: 380px;
  text-align: center;
  box-shadow: 0 25px 60px rgba(0,0,0,0.2);
  animation: scaleIn 0.2s ease;
}

.modal h3 {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 12px;
}

.modal p {
  font-size: 14px;
  color: #6B7280;
  margin-bottom: 24px;
}

.modal-actions {
  display: flex;
  gap: 12px;
}

.cancel-btn {
  flex: 1;
  background: #F3F4F6;
  border: none;
  padding: 10px;
  border-radius: 10px;
  cursor: pointer;
  font-weight: 500;
}

.cancel-btn:hover {
  background: #E5E7EB;
}

.confirm-btn {
  flex: 1;
  background: #DC3545;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 10px;
  cursor: pointer;
  font-weight: 500;
}

.confirm-btn:hover {
  background: #BB2D3B;
}

/* Animations */
@keyframes fadeIn {
  from { opacity: 0 }
  to { opacity: 1 }
}

@keyframes scaleIn {
  from { transform: scale(0.95); opacity: 0 }
  to { transform: scale(1); opacity: 1 }
}

/* ============================= */
/* RESPONSIVE */
/* ============================= */
@media (max-width: 768px) {
  .admin-body {
    flex-direction: column;
  }

  .sidebar {
    width: 100%;
    border-right: none;
    border-bottom: 1px solid #E5E7EB;
  }

  .content {
    padding: 25px;
  }
}

</style>
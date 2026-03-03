<template>
  <div class="layout">

    <!-- TOPBAR -->
    <header class="topbar">
      <div class="top-left">
        <button class="menu-toggle" @click="toggleSidebar">
          ☰
        </button>

        <!-- Changed brand color -->
        <h1 class="logo">Immuna</h1>
      </div>

      <button class="logout-btn" @click="showLogoutModal = true">
        Logout
      </button>
    </header>

    <div class="main">

      <!-- SIDEBAR -->
      <aside :class="['sidebar', { open: sidebarOpen }]">

        <!-- Dashboard (exact match only) -->
        <router-link
          to="/user"
          class="menu-item"
          exact-active-class="active-link"
        >
          Dashboard
        </router-link>

        <router-link
          to="/user/history"
          class="menu-item"
          active-class="active-link"
        >
          Vaccination History
        </router-link>

        <router-link
          to="/user/library"
          class="menu-item"
          active-class="active-link"
        >
          Vaccination Library
        </router-link>

      </aside>

      <!-- MOBILE OVERLAY -->
      <div
        v-if="sidebarOpen"
        class="overlay"
        @click="sidebarOpen = false"
      ></div>

      <!-- CONTENT -->
      <section class="content">
        <router-view />
      </section>

    </div>

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

    <!-- FOOTER -->
    <footer class="footer">
      <div>© {{ year }} Immuna</div>
      <div class="footer-sub">
        Your digital vaccination companion
      </div>
    </footer>

  </div>
</template>

<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"

const router = useRouter()

const sidebarOpen = ref(false)
const showLogoutModal = ref(false)
const year = new Date().getFullYear()

function toggleSidebar() {
  sidebarOpen.value = !sidebarOpen.value
}

function confirmLogout() {
  localStorage.removeItem("token")
  localStorage.removeItem("role")
  showLogoutModal.value = false
  router.push("/login")
}
</script>

<style scoped>

/* ===================================================== */
/* GLOBAL LAYOUT */
/* ===================================================== */
.layout {
  min-height: 100vh;
  background: #F7FAFC;
  display: flex;
  flex-direction: column;
  font-family: 'Inter', sans-serif;
}

/* ===================================================== */
/* TOPBAR */
/* ===================================================== */
.topbar {
  position: sticky;
  top: 0;
  z-index: 50;
  background: #FFFFFF;
  border-bottom: 1px solid #E5E7EB;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 28px;
}

.top-left {
  display: flex;
  align-items: center;
  gap: 15px;
}

/* 🔥 Brand color changed (neutral dark slate) */
.logo {
  font-size: 20px;
  font-weight: 700;
  letter-spacing: 0.6px;

  /* Professional elevated brand color */
  color: #1E40AF;     /* deep slate navy */

  /* subtle visual emphasis */
  text-transform: none;
}

/* MENU TOGGLE */
.menu-toggle {
  display: none;
  font-size: 20px;
  background: none;
  border: none;
  color: #374151;
  cursor: pointer;
}

/* LOGOUT BUTTON */
.logout-btn {
  background: #DC3545;
  border: none;
  padding: 8px 18px;
  border-radius: 8px;
  font-weight: 500;
  color: white;
  cursor: pointer;
  transition: 0.2s ease;
}

.logout-btn:hover {
  background: #BB2D3B;
}

/* ===================================================== */
/* MAIN STRUCTURE */
/* ===================================================== */
.main {
  display: flex;
  flex: 1;
}

/* ===================================================== */
/* SIDEBAR */
/* ===================================================== */
.sidebar {
  width: 240px;
  background: #FFFFFF;
  border-right: 1px solid #E5E7EB;
  padding: 25px 18px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  overflow-y: auto;
  transition: transform 0.3s ease;
}

/* MENU ITEMS */
.menu-item {
  text-decoration: none;
  padding: 12px 16px;
  border-radius: 10px;
  font-weight: 500;
  font-size: 14px;
  color: #374151;
  transition: 0.2s ease;
  position: relative;
}

.menu-item:hover {
  background: #F1F5F9;
  color: #111827;
}

/* ✅ ACTIVE LINK (Controlled via active-class) */
.active-link {
  background: #E6F0FF;
  color: #0B5ED7 !important;
  font-weight: 600;
}

/* Left indicator */
.active-link::before {
  content: "";
  position: absolute;
  left: -18px;
  top: 8px;
  bottom: 8px;
  width: 4px;
  background: #0B5ED7;
  border-radius: 4px;
}

/* ===================================================== */
/* CONTENT */
/* ===================================================== */
.content {
  flex: 1;
  padding: 40px;
  background: #F7FAFC;
}

/* ===================================================== */
/* FOOTER */
/* ===================================================== */
.footer {
  background: #FFFFFF;
  border-top: 1px solid #E5E7EB;
  color: #6B7280;
  text-align: center;
  padding: 18px;
  font-size: 14px;
}

.footer-sub {
  font-size: 12px;
  opacity: 0.7;
  margin-top: 4px;
}

/* ===================================================== */
/* OVERLAY */
/* ===================================================== */
.overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.35);
  z-index: 20;
}

/* ===================================================== */
/* RESPONSIVE */
/* ===================================================== */
@media (max-width: 768px) {

  .menu-toggle {
    display: block;
  }

  .sidebar {
    position: fixed;
    height: 100%;
    left: 0;
    top: 0;
    transform: translateX(-100%);
    z-index: 30;
    box-shadow: 5px 0 20px rgba(0,0,0,0.1);
  }

  .sidebar.open {
    transform: translateX(0);
  }

  .content {
    padding: 25px;
  }
}

/* ===================================================== */
/* MODAL */
/* ===================================================== */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(15, 23, 42, 0.45);
  backdrop-filter: blur(3px);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal {
  background: white;
  padding: 28px;
  border-radius: 16px;
  width: 320px;
  text-align: center;
  box-shadow: 0 15px 40px rgba(0,0,0,0.15);
  animation: scaleIn 0.2s ease;
}

.modal-actions {
  display: flex;
  gap: 10px;
}

.cancel-btn {
  flex: 1;
  background: #E5E7EB;
  border: none;
  padding: 10px;
  border-radius: 8px;
  cursor: pointer;
}

.confirm-btn {
  flex: 1;
  background: #DC3545;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 8px;
  cursor: pointer;
}

@keyframes scaleIn {
  from { transform: scale(0.95); opacity: 0 }
  to { transform: scale(1); opacity: 1 }
}

</style>
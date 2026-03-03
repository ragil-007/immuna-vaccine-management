import { createRouter, createWebHistory } from "vue-router"

const routes = [
  { path: "/", redirect: "/login" },

  {
    path: "/register",
    component: () => import("../views/auth/Register.vue")
  },

  {
    path: "/login",
    component: () => import("../views/auth/Login.vue")
  },

  // =========================
  // ADMIN ROUTES
  // =========================
  {
    path: "/admin",
    component: () => import("../layouts/AdminLayout.vue"),
    meta: { requiresAuth: true, role: "ADMIN" },
    children: [
      {
        path: "",
        component: () => import("../views/admin/Dashboard.vue")
      },
      {
        path: "diseases",
        children: [
          {
            path: "",
            component: () => import("../views/admin/DiseasesList.vue")
          },
          {
            path: "add",
            component: () => import("../views/admin/AddDisease.vue")
          },
          {
            path: "edit/:id",
            component: () => import("../views/admin/EditDisease.vue")
          }
        ]
      },
      {
        path: "vaccines",
        children: [
          {
            path: "",
            component: () => import("../views/admin/VaccinesList.vue")
          },
          {
            path: "add",
            component: () => import("../views/admin/AddVaccine.vue")
          },
          {
            path: "edit/:id",
            component: () => import("../views/admin/EditVaccine.vue")
          }
        ]
      },
      {
        path: "schedules",
        children: [
          {
            path: "",
            component: () => import("../views/admin/ScheduleList.vue")
          },
          {
            path: "add",
            component: () => import("../views/admin/AddSchedule.vue")
          },
          {
            path: "edit/:id",
            component: () => import("../views/admin/EditSchedule.vue")
          }
        ]
      },
      {
        path: "immunizations",
        children: [
          {
            path: "",
            component: () => import("../views/admin/ImmunizationList.vue")
          },
          {
            path: "add",
            component: () => import("../views/admin/AddImmunization.vue")
          },
          {
            path: "edit/:id",
            component: () => import("../views/admin/EditImmunization.vue")
          }
        ]
      },
      {
        path: "users",
        component: () => import("../views/admin/UsersList.vue")
      }
    ]
  },

  // =========================
  // USER ROUTES
  // =========================
  {
    path: "/user",
    component: () => import("../layouts/UserLayout.vue"),
    meta: { requiresAuth: true, role: "USER" },
    children: [
      {
        path: "",
        component: () => import("../views/user/Dashboard.vue")
      },
      {
        path: "history",
        component: () => import("../views/user/History.vue")
      },
      {
        path: "library",
        component: () => import("../views/user/Library.vue")
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// =========================
// GLOBAL ROUTE GUARD
// =========================
router.beforeEach((to) => {
  const token = localStorage.getItem("token")
  const role = localStorage.getItem("role")

  if (to.meta.requiresAuth && !token) {
    return "/login"
  }

  if (to.meta.role && to.meta.role !== role) {
    return "/login"
  }

  return true
})

export default router
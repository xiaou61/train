import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/',
    component: () => import('../views/main.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

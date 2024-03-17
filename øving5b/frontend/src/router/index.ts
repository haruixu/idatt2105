import { createRouter, createWebHistory } from 'vue-router'
import { useTokenStore } from '@/stores/tokenstore'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/forms',
      name: 'forms',
      component: import("@/views/FormsView.vue")
    },
    {
      path: '/calculator',
      name: 'calculator',
      component: import ("../views/CalculatorView.vue")
    },
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('../views/SignupView.vue')
    }
  ]
})

router.beforeEach(async (to) => {
  const token = useTokenStore().state.persist.storage;
  if (!token && (to.name != 'login' && to.name != 'signup')) return '/login'
})

export default router

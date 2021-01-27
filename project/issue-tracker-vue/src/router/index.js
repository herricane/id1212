import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/Login'
import Index from '@/components/Index'
import Register from '@/components/Register'

Vue.use(VueRouter)

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'Index',
      component: Index,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/',
      redirect: {
        name: 'Index'
      }
    }
  ]
})
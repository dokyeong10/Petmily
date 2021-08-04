import { createRouter, createWebHistory } from 'vue-router'

import Home from '@/views/home/Home'
import Login from '@/views/accounts/Login'
import SignupTerms from '@/views/accounts/SignupTerms'
import AgencyForm from '@/views/accounts/AgencyForm.vue'
import PrivateForm from '@/views/accounts/PrivateForm.vue'
import SignupSuccess from '@/views/accounts/SignupSuccess.vue'
import FindPassword from '@/views/accounts/FindPassword.vue'

const routes = [
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/signupterms',
    name: 'signupterms',
    component: SignupTerms
  },
  {
    path: '/agencyform',
    name: 'agencyform',
    component: AgencyForm
  },
  {
    path: '/privateform',
    name: 'privateform',
    component: PrivateForm
  },
  {
    path: '/signupsuccess',
    name: 'signupsuccess',
    component: SignupSuccess
  },
  {
    path: '/findpassword',
    name: 'findpassword',
    component: FindPassword
  },
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

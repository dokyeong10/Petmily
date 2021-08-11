import { createRouter, createWebHistory } from 'vue-router'

// main
import Home from '@/views/home/Home'


// Login & Signup & ChangePassword
import Login from '@/views/accounts/Login'
import SignupTerms from '@/views/accounts/SignupTerms'
import AgencyForm from '@/views/accounts/AgencyForm.vue'
import PrivateForm from '@/views/accounts/PrivateForm.vue'
import SignupSuccess from '@/views/accounts/SignupSuccess.vue'
import FindPassword from '@/views/accounts/FindPassword.vue'
import ChangePassword from '@/views/accounts/ChangePassword.vue'

// Profile
import ProfilePrivate from '@/views/accounts/ProfilePrivate.vue'
import ProfileAgency from '@/views/accounts/ProfileAgency.vue'
import ConfirmPassword from '@/views/accounts/ConfirmPassword.vue'
import PrivateModify from '@/views/accounts/PrivateModify.vue'
import AgencyModify from '@/views/accounts/AgencyModify.vue'

// Animal
import AnimalList from '@/views/animal/AnimalList.vue'
import AnimalRegister from '@/views/animal/AnimalRegister.vue'
import AnimalRegisterSuccess from '@/views/animal/AnimalRegisterSuccess.vue'

//live
import Live from '@/views/live/Live'

import ShelterDetail from '@/views/shelter/ShelterDetail.vue'

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
  {
    path: '/changepassword',
    name: 'changepassword',
    component: ChangePassword
  },
  {
    path: '/animallist',
    name: 'animallist',
    component: AnimalList
  },
  {
    path: '/animalregister',
    name: 'animalregister',
    component: AnimalRegister
  },
  {
    path: '/animalregistersuccess',
    name: 'animalregistersuccess',
    component: AnimalRegisterSuccess
  },
  {
    path: '/profileprivate',
    name: 'profileprivate',
    component: ProfilePrivate
  },
  {
    path: '/profileagency',
    name: 'profileagency',
    component: ProfileAgency
  },
  {
    path: '/confirmpassword',
    name: 'confirmpassword',
    component: ConfirmPassword
  },
  {
    path: '/privatemodify',
    name: 'privatemodify',
    component: PrivateModify
  },
  {
    path: '/agencymodify',
    name: 'agencymodify',
    component: AgencyModify
  },
  {
    path: '/live',
    name: 'live',
    component: Live
  },
  {
    path: '/shelterdetail/:agencycode',
    name: 'shelterdetail',
    component: ShelterDetail
  }
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

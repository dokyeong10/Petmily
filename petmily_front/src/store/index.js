
import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate"
// import axios from 'axios'

export default createStore({
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      })
    ],
  state: {
    email: "",
    findEmail: "",
    isLogin: false,
    isUser: false,
    isAgency: false,
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
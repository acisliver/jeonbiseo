import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router"
import axios from'axios'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: null,
    isLogin: false,
    isLoginError: false,
  },

  getters:{

  },

  mutations: {
    loginSuccess(state, payload) {
      state.isLogin = true
      state.isLoginError = false
      state.userInfo = payload
    },
    loginError(state) {
      state.isLoginError = true
      state.isLogin = false
    },
    logout(state) {
      state.isLogin = false
      state.isLoginError = false
      state.userInfo = null
    },
  },

  actions: {
    login(){
      axios
        .post("/login", {userName:"a",password:"c"})
          .then(res =>{
              console.log(res)
            // let config = {
            //   headers : {
            //     'access-token' : res.data.token
            //   }
            // }
            // axios
            //     .get("https://reqres.in/api/users/2", config)
            //     .then(response => {
            //       console.log(response)
            //     })
            //     .catch(error => {
            //       console.log(error)
            //     })

          })
          .catch(err=> {
            console.log(err)
          })
    },
    logout({commit}) {
      commit('logout')
      router.push({name: "Home"})
    },

  },

  modules: {
  }
})

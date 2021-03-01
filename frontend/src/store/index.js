import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router"
import axios from'axios'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: null,
    allUsers: [
      { id: 1, userName: "hoza", email: "hoza@naver.com", password: "1234"},
      { id: 2, userName: "lego", email: "lego@naver.com", password: "1234"}
    ],
    isLogin: false,
    isLoginError: false,
  },

  getters:{
    allUsersCount: state => state.allUsers.length
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
    login(loginObj){
      axios
        .post("/login", loginObj)
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
   signup_summit(signupObj) {
        console.log(signupObj)
      axios
          .post("/signup",{email:"b",password:"c"})
          .then(res => {
            console.log(res)
          })
          .catch(err => {
              console.log(err)
          })
       router.push({ name: 'Login'})
   }
  },

  modules: {
  }
})

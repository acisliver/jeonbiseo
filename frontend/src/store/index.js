import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router"
import axios from'axios'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: null,
    allUsers: [
      { id: 1, name: "hoza", email: "hoza@naver.com", password: "1234"},
      { id: 2, name: "lego", email: "lego@naver.com", password: "1234"}
    ],
    isLogin: false,
    isLoginError: false,
    testpostval: 'POST',
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
    }
  },

  actions: {
    login(){
      // let selecetd_user = null
      // state.allUsers.forEach(user => {
      //   if(user.email === loginObj.email) selecetd_user = user
      // })
      // if(!selecetd_user || selecetd_user.password !== loginObj.password)
      //     commit('loginError')
      // else{
      //   commit('loginSuccess', selecetd_user)
      //   router.push({name: 'Mypage'})
      // }
      axios
        .post("https://reqres.in/api/login", {
          "email": "eve.holt@reqres.in",
          "password": "cityslicka"
        })
          .then(res =>{
            let config = {
              headers : {
                'access-token' : res.data.token
              }
            }
            axios
                .get("https://reqres.in/api/users/2", config)
                .then(response => {
                  console.log(response)
                })
                .catch(error => {
                  console.log(error)
                })
          })
          .catch(err=> {
            console.log(err)
          })
    },
    logout({commit}) {
      commit('logout')
      router.push({name: "Home"})
    },
    TestPost(state){
      axios
          .post('/test',{'test': 'test1'})
          .then(res => {
            console.log(res)
            state.testpostval = res.data
          })
          .catch(err =>{
            console.log(err)
          })
    }
  },

  modules: {
  }
})

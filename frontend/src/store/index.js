import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router"
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: {
      userName: "Test1",
      password: "1234",
      nickName: "TestNick1"
    },
    freeHeader: null,
    freeBody: null,
    debateHeader: null,
    debateBody: null,
  },

  getters:{

  },

  mutations: {
    loginSuccess(state) {
      state.isLogin = true
      state.isLoginError = false
    },
    loginError(state) {
      state.isLoginError = true
      state.isLogin = false
    },
    logout(state) {
      state.isLogin = false
      state.isLoginError = false
    },
    setUserInfo(state, userInfoObj) {
      state.userInfo = userInfoObj
    },
    setFreeHeader(state, freeHeader) {
      state.freeHeader = freeHeader
    },
    setDebateHeader(state, debateHeader) {
      state.debateHeader = debateHeader
    },
  },

  actions: {
    getUserInfo({commit}){
      axios
          .get('/api/user')
          .then(res => {
            commit('setUserInfo', res.data)
            console.log(res.data)
          })
          .catch(err => {
            console.log(err)
      })
    },
    loginAction({commit}, statusOk){
      if(statusOk === 200){
        commit("loginSuccess")
        router.push({ name: "Home" })
      }
      else if(statusOk === 204 ){
        commit('loginError')
      }
    },
    logoutAction({commit}) {
      commit('logout')
      // router.push({ name: "Home" })
    },
    getBoardHeader({commit}) {
      axios
          .get('/api/free-header')
          .then(res => {
            commit('setFreeHeader', res.data.content)
            console.log(res.data);
          })
          .catch(err => {
            console.log(err)
          })
    },
    getDebateHeader({commit}) {
      axios
          .get('/api/debate-header')
          .then(res => {
            commit('setDebateHeader', res.data.content)
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
    },
  },

  modules: {
  }
})

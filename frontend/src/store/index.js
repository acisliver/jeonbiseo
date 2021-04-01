import Vue from 'vue'
import Vuex from 'vuex'
import boardStore from "@/store/modules/boardStore"
import userStore from "@/store/modules/userStore";

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    boardStore, userStore
  },
  state: {
    isLogin: false,
    isLoginError: false,
    // userInfo: {
    //   userName: "Test1",
    //   password: "1234",
    //   nickName: "TestNick1"
    // },
    // freeHeader: null,
    // debateHeader: null,
    // detailBoard: null,
    // replys: null
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
      localStorage.removeItem("token")
    },
    // setUserInfo(state, userInfoObj) {
    //   state.userInfo = userInfoObj
    // },
    // setFreeHeader(state, freeHeader) {
    //   state.freeHeader = freeHeader
    // },
    // setDebateHeader(state, debateHeader) {
    //   state.debateHeader = debateHeader
    // },
    // setDetailBoard(state, detailBoard) {
    //   state.detailBoard = detailBoard
    //   console.log('setDetailBoard')
    //   console.log(state.detailBoard)
    // }
  },

  actions: {
    // getUserInfo({commit}){
    //   axios
    //       .get('/api/user')
    //       .then(res => {
    //         commit('setUserInfo', res.data)
    //         console.log(res.data)
    //       })
    //       .catch(err => {
    //         console.log(err)
    //   })
    // },
    loginAction({commit}, statusOk){
      if(statusOk === 200){
        commit("loginSuccess")
       }
      else if(statusOk === 204 ){
        commit('loginError')
      }
    },
    logoutAction({commit}) {
      commit('logout')
      // router.push({ name: "Home" })
    },
    // getFreeHeader({commit}) {
    //   axios
    //       .get('/api/free-header')
    //       .then(res => {
    //         commit('setFreeHeader', res.data.reverse())
    //         console.log(res.data);
    //       })
    //       .catch(err => {
    //         console.log(err)
    //       })
    // },
    // getDebateHeader({commit}) {
    //   axios
    //       .get('/api/debate-header')
    //       .then(res => {
    //         commit('setDebateHeader', res.data)
    //         console.log(res)
    //       })
    //       .catch(err => {
    //         console.log(err)
    //       })
    // },
    // getBoardContent({commit, state}, boardId){
    //   const url = '/api/free-board/' + boardId
    //   console.log(url)
    //   axios
    //       .get(url)
    //       .then(res => {
    //         commit('setDetailBoard', res.data.detailBoard)
    //         console.log(res.data.detailBoard)
    //         console.log(state.detailBoard)
    //       })
    //       .catch(err => {
    //         console.log(err)
    //       })
    // },
  },
})

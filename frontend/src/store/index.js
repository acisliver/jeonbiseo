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
    board: {
      writer: null,
      title: null,
      content: null,
      boardId: null
    },
    boardHeader: [
      {
        writer: '대은',
        title: '빅데이터란',
        writeTime: 1,       //Date() 반환좀
        content: '공부해야됨?야팔',
        boardId: 1
      },
      {
        writer: "성범",
        title: '존잘이란',
        writeTime: 2,
        content: '강성범존잘',
        boardId: 2
      },
      {
        writer: '지훈',
        title: '토비란',
        writeTime: 3,
        content: '스프링 설명개꿀',
        boardId: 3
      }
    ],
    boardBody: [
      {

      }
    ]
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
    }
  },

  actions: {
    getUserInfo({commit}){
      axios
          .get('api/user')
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

  },

  modules: {
  }
})

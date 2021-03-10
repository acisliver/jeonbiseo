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
    boardHeader: [
      {
        writer: '대은',
        title: '빅데이터란',
        id: 1
      },
      {
        writer: "성범",
        title: '존잘이란',
        id: 2
      },
      {
        writer: '지훈',
        title: '토비란',
        id: 3
      }
    ],
    boardBody: [
      {

      }
    ],
    debateHeader: [
      {
        writer: '대은',
        title: '캡스톤주제뭐할까',
        id: 1
      },
      {
        writer: "성범",
        title: '잘생겨지기위해필요한것',
        id: 2
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 3
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 4
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 5
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 6
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 7
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 8
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 9
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 10
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 11
      },{
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 12
      },{
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 13
      },{
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 14
      },
      {
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 15
      },{
        writer: '지훈',
        title: '싱글턴어캐쓸까',
        id: 16
      },




    ],
    debateBody: [
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
    },
    setBoardHeader(state, boardHeader) {
      state.boardHeader = boardHeader
    },
    setDebateHeader(state, debateHeader) {
      state.debateHeader = debateHeader
    }
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
          .get('/api/boardHeader')
          .then(res => {
            commit('setBoardHeader', res.data)
          })
          .catch(err => {
            console.log(err)
          })
    },
    getDebateHeader({commit}) {
      axios
          .get('/api/debateHeader')
          .then(res => {
            commit('setdebateHeader', res.data)
          })
          .catch(err => {
            console.log(err)
          })
    },
  },

  modules: {
  }
})

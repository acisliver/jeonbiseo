import axios from "axios";

export default {
    namespaced: true,
    state: {
        isLogin: false,
        isLoginError: false,
        userInfo: {
            userName: "Test1",
            password: "1234",
            nickName: "TestNick1"
        },
    },
    getters: {

    },
    mutations: {
        setUserInfo: (state, userInfoObj) => state.userInfo = userInfoObj,
        loginSuccess(state, userName) {
            state.isLogin = true
            state.isLoginError = false
            state.userInfo.userName = userName
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
        loginAction({commit}, statusOk, userName){
            if(statusOk === 200){
                commit("loginSuccess", userName)
            }
            else if(statusOk === 204 ){
                commit('loginError')
            }
        },
        logoutAction({commit}) {
            commit('logout')
            // router.push({ name: "Home" })
        },
    }
}
import axios from "axios";

export default {
    namespaced: true,
    state: {
        userInfo: {
            userName: "Test1",
            password: "1234",
            nickName: "TestNick1"
        },
    },
    getters: {

    },
    mutations: {
        setUserInfo: (state, userInfoObj) => state.userInfo = userInfoObj
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
    }
}
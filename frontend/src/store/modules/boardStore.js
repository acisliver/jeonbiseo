import axios from "axios";

export default {
    namespaced: true,
    state: {
        freeHeader: null,
        debateHeader: null,
        detailBoard: null,
        replys: null,
        chartData: null
    },

    getters: {
        getReplys({state}){
            return state.boardStore.replys
        }
    },

    mutations: {
        setFreeHeader: (state, freeHeader) => state.freeHeader = freeHeader,
        setDebateHeader: (state, debateHeader) => state.debateHeader = debateHeader,
        setDetailBoard: (state, detailBoard) => state.detailBoard = detailBoard,
        setReplys: (state, replys) => state.replys = replys,
        setChartData: (state, chartDataObj) => state.chartData = chartDataObj
    },

    actions: {
        getFreeHeader({commit}) {
            axios
                .get('/api/free-header')
                .then(res => {
                    commit('setFreeHeader', res.data.reverse())
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
                    commit('setDebateHeader', res.data)
                    console.log(res)
                })
                .catch(err => {
                    console.log(err)
                })
        },
        getBoardContent({commit}, boardId){
            const url = '/api/free-board/' + boardId
            console.log(url)
            axios
                .get(url)
                .then(res => {
                    commit('setDetailBoard', res.data.detailBoard)
                    commit('setReplys', res.data.detailBoard.replys)
                })
                .catch(err => {
                    console.log(err)
                })
        },
        getDebateContent({commit}, boardId){
            const url = '/api/debate/' + boardId
            console.log(url)
            axios
                .get(url)
                .then(res => {
                    commit('setDetailBoard', res.data.detailBoard)
                    commit('setReplys', res.data.detailBoard.debateReplies)
                    commit('setChartData', res.data.percentageDto)
                    console.log(res.data.detailBoard)
                    console.log(res.data.percentageDto)
                })
                .catch(err => {
                    console.log(err)
                })
        }
    }
}
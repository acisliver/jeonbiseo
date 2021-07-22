import axios from "axios";

export default {
    namespaced: true,
    state:{
        searchResult: null,
        selectedDevice: []
    },
    getter: {
        getSearchResult({state}){
            return state.searchResult
        }
    },
    mutations: {
        setSearchResult: (state, searchResult) => state.searchResult = searchResult,
        setSelectedDevice: (state, device) => state.selectedDevice.push(device)
    },
    actions: {
        searchApplication({commit}, searchWord){
            console.log(searchWord)
            const url = "/api/compare/search"
            const params = `?searchWord=${searchWord}`
            axios
                .get(url + params)
                .then(res => {
                    console.log(res)
                    commit("setSearchResult", res.data)
                    console.log(res.data)
                })
                .catch(e => console.log(e))
        },
        selectDevice({commit}, device) {
            console.log(2)
            commit("setSelectedDevice", device)
        },
        deleteDevice({state}, devcie){
            state.selectedDevice.remove(devcie)
        }
    }
}
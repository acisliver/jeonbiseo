import axios from "axios";

export default {
    namespaced: true,
    state:{
        searchResult: null
    },
    getter: {
        getSearchResult({state}){
            return state.searchResult
        }
    },
    mutations: {
        setSearchResult: (state, searchResult) => state.searchResult = searchResult
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
    }
}
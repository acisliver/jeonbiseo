import Vue from 'vue'
import Vuex from 'vuex'
import boardStore from "@/store/modules/boardStore"
import userStore from "@/store/modules/userStore";
import applicationStore from "@/store/modules/applicationStore";

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    boardStore, userStore, applicationStore
  },
  state: {

  },

  getters:{

  },

  mutations: {

  },

  actions: {

  },
})

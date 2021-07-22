<template>
  <div class="compare">
    <v-container class="align-stretch">
      <h2 style="margin: 1rem">전자기기 비교</h2>
      <v-row>
        <v-col
          cols="9"
        >
          <comparing-appliaction :comparing-products="comparingProducts"/>
        </v-col>
        <v-col
          cols="3"
        >
          <search-box @search="getSearchResult"/>
        </v-col>
        <v-col
            cols="12"
        >
          <search-result :search-result="searchResult" @comparing="getProduct"/>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>

export default {
  name: "Compare",
  data(){
    return {
      comparingProducts: [],
      searchResult: [],
    }
  },
  components:{
    SearchBox: () => import("../components/Compare/SearchBox"),
    ComparingAppliaction: () => import("../components/Compare/ComparingApplication"),
    SearchResult: () => import("../components/Compare/SearchResult")
  },
  methods: {
    getProduct(data) {
      this.comparingProducts.push(data)
      console.log(data)
    },
    getSearchResult(data) {
      this.searchResult = data
    }
  }
}
</script>

<style scoped>
.compare{
  text-align: center;
}
</style>

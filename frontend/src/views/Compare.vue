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
          <search-box/>
        </v-col>
        <v-col
            cols="12"
        >
          <search-application-result @comparing="getProduct"/>
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
      comparingProducts: []
    }
  },
  components:{
    SearchBox: () => import("../components/Compare/SearchBox"),
    ComparingAppliaction: () => import("../components/Compare/ComparingApplication"),
    SearchApplicationResult: () => import("../components/Compare/SearchApplicationResult")
  },
  methods: {
    getProduct(data) {
      this.comparingProducts.push(data)
      console.log(data)
    }
  }
}
</script>

<style scoped>
.compare{
  text-align: center;
}
</style>

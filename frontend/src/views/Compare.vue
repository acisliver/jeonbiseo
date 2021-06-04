<template>
  <div class="compare">
    <v-container>
      <h2>전자기기 비교</h2>
      <v-row>
        <v-col
          cols="9"
        >
          <comparing-appliaction></comparing-appliaction>
        </v-col>
        <v-col
          cols="3"
        >
          <search-box/>
        </v-col>
        <search-application-result/>
      </v-row>
    </v-container>


  </div>
</template>

<script>

export default {
  name: "Compare",
  components:{
    SearchBox: () => import("../components/Compare/SearchBox"),
    ComparingAppliaction: () => import("../components/Compare/ComparingApplication"),
    SearchApplicationResult: () => import("../components/Compare/SearchApplicationResult")
  },
  data() {
    return {

    };
  }
}
</script>

<style scoped>
.compare{
  text-align: center;
}
</style>

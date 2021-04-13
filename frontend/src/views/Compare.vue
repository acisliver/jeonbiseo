<template>
  <div class="compare">
    <h2>비교</h2>
    <PieChart/>
  </div>
</template>

<script>
  import PieChart from "../components/PieChart.vue"

  export default {
    name: "Compare",
    components:{
      PieChart
    },
    data() {
      return {
        chartData: {
          Books: 24,
          Magazine: 30,
          Newspapers: 10
        }
      };
    }
  }
</script>

<style scoped>
.compare{
  text-align: center;
}
</style>

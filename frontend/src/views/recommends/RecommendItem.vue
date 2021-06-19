<template>
  <div class="recommend-item">
    <h2>당신을 위한 전자기기</h2>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RecommendItem",
  data(){
    return {
      recommendItems: [],
    }
  },
  mounted() {
    this.getReccomend()
  },
  methods: {
    getReccomend(){
      axios.post("/api/item-recommend")
          .then(res => this.recommendItems = res.data)
          .catch(e => console.log(e))
    }
  }
}
</script>

<style scoped>
.recommend-item{
  text-align: center;
}
</style>
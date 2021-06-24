<template>
  <div class="recommend-result container">
    <div class="card-group container">

      <div
          v-for="item in result"
          :key="item"
          class="card"
          type="button"
          style="font-size: 1.5rem;"
      >
        <v-card>
          <img width="200px" height="auto" :src="item.url">
        </v-card>
        {{item.productName}}
      </div>
    </div>
    <div class="next-btn">
      <v-btn
          color="primary"
          @click="$router.push({name:'Compare'})"
      >
        비교하러가기
      </v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RecommendResult",
  data(){
    return {
      result: [
        {
          url: "",
          productName: "dfs"
        },
      ]
    }
  },
  methods: {

  },
  mounted() {
    let config = {
      headers: {
        token: localStorage.getItem('token')
      }
    }
    axios.get("/api/recommend-result", config)
        .then(res => {
          this.result = res.data
        })
        .catch(e =>console.log(e))
  }
}
</script>

<style scoped>
.container h2{
  padding-bottom: 8rem;
}
.card-group{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.card{
  width: 15rem;
  height: 15rem;
}
.card:hover{
}
.card-group img{
  width: 15rem;
  height: 15rem;
}
.next-btn{
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  padding: 2rem;
}
</style>
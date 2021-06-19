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
          <img :src="require(`../../assets/5g.png`)">
        </v-card>
        {{item.name}}
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
          name: '1'
        },
        {
          name: '2'
        }
      ]
    }
  },
  methods: {

  },
  mounted() {
    axios.get("/api/recommend-result")
        .then(res => this.result = res)
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
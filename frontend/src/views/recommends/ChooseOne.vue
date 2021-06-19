<template>
  <div class="chose-one container">
    <h2>어떤 {{categoryName}}를 선호하시나요?</h2>
    <div class="card-group container">
      <div
        v-for="item in categoryObj"
        :key="item"
        class="card"
        type="button"
        style="font-size: 1.5rem;"
      >
        <v-card
            :color="item.backgroundColor"
            @click="chooseOne(item.name)"
        >
          <img :src="require(`../../assets/${item.img}`)">
        </v-card>
        {{item.name}}
      </div>
    </div>
    <div class="next-btn">
      <v-btn
          v-if="nextPageName === 'End'"
          color="primary"
          @click="$router.push({name:'End'})"
      >
        마치기
      </v-btn>
      <v-btn
          v-else
          color="primary"
          @click="$router.push({name:nextPageName})"
      >
        건너뛰기
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChooseOne",
  props:{
    categoryName: {
      type:String,
      required: true
    },
    //이미지, 카테고리 별 이름 넣기
    categoryObj: {
      type: Object,
      required: true
    },
    nextPageName: {
      type: String,
      required: true
    }
  },
  methods: {
    chooseOne(choosedOne){
      let recObj = localStorage.getItem('recommendObj')

      recObj = recObj ? JSON.parse(recObj) : {}
      recObj[this.categoryName] = choosedOne
      localStorage.setItem('recommendObj', JSON.stringify(recObj))

      if(this.nextPageName === "End") return
      else this.$router.push({name: this.nextPageName})
    }
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
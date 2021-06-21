<template>
  <div class="network container">
    <chose-one category-name="portability" :category-obj="networkObj" next-page-name="RecommendResult"></chose-one>
  </div>
</template>

<script>
export default {
  name: "Network",
  components: {
    ChoseOne: ()=> import("./ChooseOne")
  },
  data(){
    return {
      networkObj: {
        small: {
          name: '휴대용',
          img: "out.jpg",
          backgroundColor: "black"
        },
        middle: {
          name: '집콕',
          img: "in.jpg",
          backgroundColor: "black"
        },
      }
    }
  }
}
</script>

<style scoped>
.network{
  text-align: center;
}
</style>
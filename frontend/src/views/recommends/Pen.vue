<template>
  <div class="network container">
    <chose-one category-name="pen" :category-obj="networkObj" next-page-name="Portability"></chose-one>
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
          name: '필기용',
          img: "apple.png",
          backgroundColor: "black"
        },
        middle: {
          name: '영상시청용',
          img: "apple.png",
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
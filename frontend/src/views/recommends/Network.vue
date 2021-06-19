<template>
  <div class="network container">
    <chose-one category-name="network" :category-obj="networkObj" next-page-name="Pen"></chose-one>
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
          name: 'Wi-Fi',
          img: "wifi.png",
          backgroundColor: "white"
        },
        middle: {
          name: '5G',
          img: "5g.png",
          backgroundColor: "white"
        },
        large: {
          name: 'LTE',
          img: "lte.png",
          backgroundColor: "white"
        }
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
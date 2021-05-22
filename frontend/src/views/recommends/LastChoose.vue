<template>
  <div class="size container">
    <chose-one category-name="lastChose" :category-obj="sizeObj" next-page-name="End"></chose-one>
  </div>
</template>

<script>
export default {
  name: "LastChoose",
  components: {
    ChoseOne: ()=> import("./ChooseOne")
  },
  data(){
    return {
      sizeObj: {
        small: {
          name: '작은 사이즈',
          img: "apple.png",
          backgroundColor: "black"
        },
        middle: {
          name: '중간 사이즈',
          img: "apple.png",
          backgroundColor: "black"
        },
        large: {
          name: '큰 사이즈',
          img: "apple.png",
          backgroundColor: "black"
        }
      }
    }
  }
}
</script>

<style scoped>
.size{
  text-align: center;
}
</style>
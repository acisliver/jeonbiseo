<template>
  <div class="os container">
    <chose-one category-name="os" :category-obj="osObj" next-page-name="Network"></chose-one>
  </div>
</template>

<script>
export default {
  name: "OS",
  components: {
    ChoseOne: ()=> import("./ChooseOne")
  },
  data(){
    return {
      osObj: {
        iosObj: {
          name: 'ios',
          img: "apple.png",
          backgroundColor: "black"
        },
        androidObj: {
          name: '안드로이드',
          img: "android.png",
          backgroundColor: "white"
        }
      }
      }
    }
}
</script>
<style scoped>
  .os{
    text-align: center;
  }
</style>
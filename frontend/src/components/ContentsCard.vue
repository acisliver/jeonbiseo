<template>
  <div class="ContentsCard">
    <v-card
        width="400px"
        height="400px"
        class="white--text blue lighten-3 d-flex align-center justify-center"
    >
      {{ title }}
    </v-card>
  </div>
</template>

<script>
export default {
  name: "ContentsCard",
  props:{
    customColor: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: true
    }
  },
  data() {
    return {

    }
  },
  // methods: {
  //   setCardColor(customColor){
  //     const card = document.getElementsByClassName('ContentsCard');
  //     card.style.color = customColor;
  //     console.log(customColor)
  //     return 0;
  //   }
  // },
  // created() {
  //   this.setCardColor(this.customColor);
  // }
}
</script>

<style scoped>

</style>

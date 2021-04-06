<template>
  <v-container
      class="Home"
  >
    <v-col
    >
      <v-parallax
          dark
          src="../assets/home_img.jpg"
          height="800"
      >
        <v-row
            align="center"
            justify="center"
        >
          <v-col
              class="text-center"
              cols="12"
          >
            <h1 class="display-1 font-weight-thin mb-4">
              전비서
            </h1>
            <h4 class="subheading">
              전자기기 비교는 여기서
            </h4>
          </v-col>
        </v-row>
      </v-parallax>



      <v-row class="justify-space-around">
        <ContentsCard :title="compareTitle" :customColor="compareColor"></ContentsCard>
        <ContentsCard :title="recommandTitle" :customColor="recommendColor"></ContentsCard>
      </v-row>

    </v-col>
  </v-container>
</template>

<script>


import ContentsCard from "@/components/ContentsCard";

export default {
  name: 'Home',
  data() {
    return {
      compareTitle: '전자기기비교',
      recommandTitle: '전자기기추천',
      compareColor: '#2196F3',
      recommendColor: '#673AB7'
    }
  },
  components: {
    ContentsCard: ContentsCard
  }
}
</script>

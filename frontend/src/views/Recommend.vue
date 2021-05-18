<template>
  <div class="recommend">
    <h2>당신의 취향에 맞는 전자기기 추천</h2>
    <router-link :to="{ name: 'OS' }" style="text-decoration: none"><v-btn @click="initRecommendObj">시작하기</v-btn></router-link>
  </div>
</template>

<script>
export default {
  name: "Recommend",
  methods: {
    initRecommendObj(){
      const recommendObj = {}
      localStorage.setItem('recommendObj', JSON.stringify(recommendObj))
    }
  }
}
</script>

<style scoped>
  .recommend{
    text-align: center;
  }
</style>

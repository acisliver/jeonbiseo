<template>
  <div>
    <h2>
      {{ userInfo.name }}님 환영합니다!
    </h2>
  </div>
</template>

<script>
import{ mapState } from 'vuex'

export default {
  name: "MyPage",
  computed: {
    ...mapState(['userInfo'])
  }
}
</script>

<style scoped>

</style>

<template>
  <div>
    <h2>
      환영합니다!
    </h2>
    <p>userName: {{userInfo.userName}}</p>
    <p>password: {{userInfo.password}}</p>
    <p>nickName:{{userInfo.nickName}}</p>
    <v-btn>수정</v-btn>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "MyPage",
  computed: {
    ...mapState(['userInfo'])
  }
}
</script>

<style scoped>

</style>

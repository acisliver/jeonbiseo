<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <h2>홈입니다</h2>
    <v-btn @click="TestPost">Post</v-btn>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'Home',
  methods: {
    ...mapActions(['TestPost'])
  }
}
</script>

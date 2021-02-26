<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <h2>홈입니다</h2>
    <v-btn @click="TestPost">{{ testpostval }}</v-btn>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  name: 'Home',
  data() {
    return {

    }
  },
  computed: {
    ...mapState(['testpostval'])
  },
  methods: {
    ...mapActions(['TestPost'])
  }
}
</script>

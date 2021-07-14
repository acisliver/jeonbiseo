<template>
  <v-app>
    <app-bar />

    <view />

    <footer />

  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {
    AppBar: () => import('@/layout/AppBar'),
    View: () => import('@/layout/View'),
    Footer: () => import('@/layout/Footer'),
  }
}
</script>

<style lang="scss">
#app {

}

</style>

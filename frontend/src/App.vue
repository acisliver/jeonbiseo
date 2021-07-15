<template>
  <v-app>
    <core-app-bar />

    <core-view />

    <core-footer />
  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {
    CoreAppBar: () => import('@/layout/AppBar'),
    CoreView: () => import('@/layout/View'),
    CoreFooter: () => import('@/layout/Footer'),
  }
}
</script>

<style lang="scss">
#app {

}

</style>

<template>
  <v-app>
    <core-app-bar />

    <core-view />

    <pie-chart/>

    <core-footer />


  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {
    CoreAppBar: () => import('@/core/AppBar'),
    CoreView: () => import('@/core/View'),
    CoreFooter: () => import('@/core/Footer'),
    PieChart: () => import('@/components/PieChart')
  }
}
</script>

<style lang="scss">
#app {

}


</style>

<template>
  <v-footer
      color= "#42b983"
      padless
  >
    <v-row
        justify="center"
        no-gutters
    >
      <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 white--text"
          icon
      >
        <v-icon size="24px">
          {{ icon }}
        </v-icon>
      </v-btn>

      <v-col
          class="#42b983 py-4 text-center white--text"
          cols="12"
      >
        {{ new Date().getFullYear() }} — <strong>전자기기 비교는 여기서</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  name: "Footer",
  data: () => ({
    icons: [
      'mdi-facebook',
      'mdi-twitter',
      'mdi-linkedin',
      'mdi-instagram',
    ],
  }),
}
</script>

<style scoped>

</style>

<template>
  <v-footer
      color= "#1976d2"
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
        {{ new Date().getFullYear() }} — <strong><span>전</span>자기기 <span>비</span>교는 여기<span>서</span></strong>
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

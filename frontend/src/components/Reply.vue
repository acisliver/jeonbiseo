<template>
  <div class="Reply">
    <v-container>
      <v-card
          class="elevation-1 ma-1"
          v-for="reply in replys"
          :key="reply.id"
      >
        <v-card-text>
          <v-row
              class="mb-4"
              align="center"
          >
            <div>
              <v-icon>mdi-account</v-icon>
              {{ reply.user.nickName }}
            </div>
          </v-row>
          {{ reply.content }}
        </v-card-text>
      </v-card>

    </v-container>
  </div>

</template>

<script>

export default {
  name: "Reply",
  props: {
    replys: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>

</style>

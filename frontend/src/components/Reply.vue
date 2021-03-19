<template>
  <div class="Reply">
    <WriteReply></WriteReply>
    <v-container
        class="elevation-1"
        v-for="reply in replys"
        :key="reply.id"
    >
    {{ reply.content }}
    </v-container>
  </div>

</template>

<script>

export default {
  name: "Reply",
  components: {
    WriteReply: import('@/components/WriteReply')
  },
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

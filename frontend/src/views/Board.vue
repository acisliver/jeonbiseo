<template>
<div class="board">
  <h2 class="pa-5">게시판</h2>
    <div class="d-flex flex-row mb-6 justify-space-around">
    <v-card width="600" height="500">
      <h2>자유게시판</h2>
      <FreeBoard/>
    </v-card>
    <v-card width="600" height="500">
      <h2>토론게시판</h2>
      <DebateBoard/>
    </v-card>
  </div>
</div>
</template>

<script>

export default {
  name: "Board",
  components: {
    FreeBoard : () => import('@/views/FreeBoard'),
    DebateBoard: () => import('@/views/DebateBoard')
  }
}
</script>

<style scoped>

</style>

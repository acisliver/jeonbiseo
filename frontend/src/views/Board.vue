<template>
<div class="board">
  <h2>게시판</h2>
  <ViewBoard></ViewBoard>
</div>
</template>

<script>

export default {
  name: "Board",
  components: {
    ViewBoard : () => import('@/components/ViewBoard'),
  }
}
</script>

<style scoped>

</style>

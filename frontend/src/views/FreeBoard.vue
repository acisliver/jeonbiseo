<template>
  <Pagination :title = "title" :header="freeHeader" :board-name="boardName"></Pagination>
</template>

<script>
import { mapState } from 'vuex'


export default {
  name: "FreeBoard",
  data () {
    return {
      title: '제목',
      boardName: 'FreeBoard'
    }
  },
  components: {
    Pagination: () => import('@/components/Board/Pagination')
  },
  computed: {
    ...mapState({
      freeHeader: state => state.boardStore.freeHeader
    })
  },
  created() {
    this.$store.dispatch("boardStore/getFreeHeader")
  }
}
</script>

<style scoped>

</style>

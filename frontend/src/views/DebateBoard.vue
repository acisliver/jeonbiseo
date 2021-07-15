<template>
  <Pagination :title = "title" :header="debateHeader" :board-name="boardName"></Pagination>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "DebateBoard",
  data () {
    return {
      title: "토론주제",
      boardName: 'DebateBoard'
    }
  },
  components: {
    Pagination: () => import('@/components/Board/Pagination')
  },
  computed: {
    ...mapState({
      debateHeader: state => state.boardStore.debateHeader
    })
  },
  created() {
    this.$store.dispatch('boardStore/getDebateHeader')
  }
}
</script>

<style scoped>

</style>

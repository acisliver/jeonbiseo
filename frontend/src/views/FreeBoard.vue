<template>
  <Pagination :title = "title" :header="boardHeader" :board-name="boardName"></Pagination>
</template>

<script>
import { mapState,mapActions } from 'vuex'

export default {
  name: "FreeBoard",
  data () {
    return {
      title: '제목',
      boardName: 'FreeBoard'
    }
  },
  components: {
    Pagination: () => import('@/components/Pagination')
  },
  computed: {
    ...mapState(['boardHeader'])
  },
  methods: {
    ...mapActions(['getBoardHeader'])
  },
  created() {
    this.getBoardHeader()
  }
}
</script>

<style scoped>

</style>

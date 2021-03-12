<template>
  <Pagination :title = "title" :header="freeHeader" :board-name="boardName"></Pagination>
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
    ...mapState(['freeHeader'])
  },
  methods: {
    ...mapActions(['getFreedHeader'])
  },
  created() {
    this.getBoardHeader()
  }
}
</script>

<style scoped>

</style>

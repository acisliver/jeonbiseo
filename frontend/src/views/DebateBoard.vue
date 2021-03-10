<template>
  <Pagination :title = "title" :header="debateHeader"></Pagination>
</template>

<script>
import { mapState,mapActions } from 'vuex'

export default {
  name: "DebateBoard",
  data () {
    return {
      title: "토론주제"
    }
  },
  components: {
    Pagination: () => import('@/components/Pagination')
  },
  computed: {
    ...mapState(['debateHeader'])
  },
  methods: {
    ...mapActions(['getDebateHeader'])
  },
  created() {
    this.getDebateHeader()
  }
}
</script>

<style scoped>

</style>

<template>
  <Pagination :title = "title" :header="boardHeader"></Pagination>
</template>

<script>
// import axios from "axios";
import { mapState,mapActions } from 'vuex'

export default {
  name: "ViewBoard",
  data () {
    return {
      title: '제목'
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

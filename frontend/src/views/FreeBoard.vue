<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
      <tr>
        <th class="text-left">
          제목
        </th>
        <th class="text-right">
          작성자
        </th>
<!--        <th class="text-right">-->
<!--          작성일자-->
<!--        </th>-->
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="board in boardHeader"
          :key="board.title"
      >
        <td class="text-left">{{ board.title }}</td>
        <td class="text-right">{{ board.writer }}</td>
<!--        <td class="text-right">{{ board.writeTime}}</td>-->
      </tr>
      </tbody>
    </template>
  </v-simple-table>

</template>

<script>
// import axios from "axios";
import { mapState,mapActions } from 'vuex'

export default {
  name: "ViewBoard",
  data () {
    return {

    }
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

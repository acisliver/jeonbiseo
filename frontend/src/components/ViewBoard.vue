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
        <th class="text-right">
          작성일자
        </th>
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
import axios from "axios";

export default {
  name: "ViewBoard",
  data () {
    return {
      boardHeader: null,
    }
  },
  methods: {
    // getBoardBody(boardId){
    //   let id = boardId
    //   axios
    //   .post('api/:id')
    //   .then(res => {
    //
    //   })
    // }
  },
  created() {
    axios
        .get('/api/boardHeader')
        .then(res => {
          this.boardHeader = res.data
        })
        .catch(err => {
          console.log(err)
        })
  }
}
</script>

<style scoped>

</style>

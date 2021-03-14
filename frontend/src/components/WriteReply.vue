<template>
  <div class="WriteReply">
    <v-textarea
        v-model="replys.content"
        filled
        rows="4"
        height="400"
    ></v-textarea>
    <v-btn @click="saveReply(replys)">댓글쓰기</v-btn>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "WriteReply",
  data() {
    return {
      replys: {
        content: null,
        boardId: null
      }
    }
  },
  methods: {
    saveReply(replyObj){
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      this.replys.boardId = this.$route.params
      console.log(replyObj)
      axios
          .post('/api/free-board/' + this.replys.boardId + '/reply', replyObj, config)
          .then(res => {
            alert('저장 완료')
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
    }
  }
}
</script>

<style scoped>

</style>

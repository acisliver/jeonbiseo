<template>
  <div class="WriteReply">
    <v-textarea
        v-model="reply.content"
        filled
        rows="4"
        height="100"
    ></v-textarea>
    <v-btn @click="saveReply(reply)">댓글쓰기</v-btn>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "WriteReply",
  data() {
    return {
      reply: {
        id: null,
        content: null,
        boardId: null,
        userId: null,
        createDate: null,
        reparent: 0
      },
      replys: null
    }
  },
  methods: {
    saveReply(replyObj){
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      this.reply.boardId = this.$route.params.boardId
      axios
          .post('/api/free-board/' + this.reply.boardId + '/reply', replyObj, config)
          .then(res => {
            alert('저장 완료')
            this.$emit("updateReplys", res.data.detailBoard.replys)
            console.log(res)
          })
          .catch(err => {
            alert('저장 실패')
            console.log(err)
          })
    }
  },
}
</script>

<style scoped>

</style>

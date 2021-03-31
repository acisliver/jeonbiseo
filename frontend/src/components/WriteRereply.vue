<template>
  <div class="WriteRereply">
    <v-textarea
        v-model="rereply.content"
        filled
        rows="4"
        height="100"
    ></v-textarea>
    <v-btn @click="saveRereply(reply, reply.id)">저장</v-btn>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "WriteRereply",
  props: {
    reply: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      rereply: {
        id: null,
        content: null,
        boardId: null,
        userId: null,
        createDate: null,
        reparent: 0
      }
    }
  },
  computed: {
    ...mapState(['replys'])
  },
  methods: {
    saveRereply(rereplyObj, parentId){
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      this.replys.boardId = this.$route.params.boardId
      rereplyObj.reparent = parentId
      console.log(rereplyObj)
      axios
          .post('/api/free-board/' + this.replys.boardId + '/reply', rereplyObj, config)
          .then(res => {
            alert('저장 완료')
            this.replys = res.data.detailBoard.replys
            console.log(res)
          })
          .catch(err => {
            alert('저장 실패')
            console.log(err)
          })
    }
  }
}
</script>

<style scoped>

</style>

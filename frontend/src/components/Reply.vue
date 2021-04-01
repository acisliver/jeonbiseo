<template>
  <div class="Reply">
    <v-container>
      <v-card
          class="elevation-1 ma-1"
          v-for="reply in replys"
          :key="reply.id"
          @updateReplys = "fromWriteReply"
      >
        <v-card-text>
          <v-row class="mb-4" align="center">
            <div>
              <v-icon>mdi-account</v-icon>
              {{ reply.user.nickName }}
            </div>
          </v-row>
          <v-col>
            {{ reply.content }}
          </v-col>
          <v-btn @click="isRereplyClick()" class="float-right">대댓글</v-btn>
        </v-card-text>
        <WriteRereply :reply="reply"></WriteRereply>
      </v-card>

    </v-container>
  </div>

</template>

<script>

export default {
  name: "Reply",
  props: {
    replys: {
      type: Array,
      required: true
    }
  },
  data(){
    return {
      rereply: {
        id: null,
        content: null,
        boardId: null,
        userId: null,
        createDate: null,
        reparent: 0
      },
      isBtnClick: false,
    }
  },
  components: {
    WriteRereply: import('@/components/WriteRereply'),
  },
  computed: {

  },
  methods: {
    fromWriteReply(newRelpys){
      this.$emit("updateReplys", newRelpys)
      this.replys = newRelpys
      console.log(newRelpys)
    },
    isRereplyClick(){
      if(this.isBtnClick === false) this.isBtnClick = true
      else this.isBtnClick = false
    },

  }
}
</script>

<style scoped>

</style>

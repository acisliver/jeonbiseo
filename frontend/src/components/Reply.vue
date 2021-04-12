<template>
  <div class="Reply">
    <v-container>
      <v-card
          class="elevation-1 ma-1"
          v-for="reply in replys"
          :key="reply.id"
      >
        <v-card-text>
          <v-row class="mb-4" align="center">
            <div>
              <v-icon>mdi-account</v-icon>
              {{ reply.user.nickName }}
            </div>
          </v-row>
          <v-row class="ma-2">
            {{ reply.content }}
          </v-row>
          <v-row class="save-btn-parent">
            <v-btn class="ma-3" @click="reply.isrereply = true">대댓글</v-btn>
          </v-row>

        </v-card-text>
        <WriteRereply v-if="reply.isrereply" :reply="reply"></WriteRereply>
<!--        <component :is="reply.rereplyComponent" :reply="reply"></component>-->
      </v-card>
    </v-container>
  </div>

</template>

<script>
import { mapState } from "vuex"
// import WriteRereply from "@/components/WriteRereply"

export default {
  name: "Reply",
  data(){
    return {
      previousRereplyObj: null,
      rereplys: null,
      rereply: {
        id: null,
        content: null,
        boardId: null,
        userId: null,
        createDate: null,
        reparent: 0
      },

    }
  },
  components: {
    WriteRereply: import('@/components/WriteRereply'),
  },
  computed: {
    ...mapState({
      replys: state => state.boardStore.replys,
    }),
  },
  methods: {
  //   addRereplyArea(replyObj){
  //     // const pro = this.previousRereplyObj
  //     //클릭된 reply 객체에 컴포넌트 추가
  //     replyObj.rereplyComponent = WriteRereply
  //     console.log(replyObj.rereplyComponent)
  //     //
  //     // if(pro === null){
  //     //
  //     // }
  //     // else{
  //     //
  //     // }
  //     //다른 대댓글 클릭시 이전 컴포넌트 삭제를 위한 객체 저장
  //     this.previousRereplyObj = replyObj
  //   },
  },
  created() {

  }
}
</script>

<style scoped>
  .save-btn-parent{
    display: flex;
    justify-content: flex-end;
  }
</style>
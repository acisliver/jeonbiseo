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
          <v-col>
            {{ reply.content }}
          </v-col>
          <v-btn @click="addRereplyArea()" class="">대댓글</v-btn>
        </v-card-text>
        <component v-bind:is="textArea"></component>
        <WriteRereply :reply="reply"></WriteRereply>
      </v-card>

    </v-container>
  </div>

</template>

<script>
import { mapState } from "vuex"
import WriteRereply from "@/components/WriteRereply";

export default {
  name: "Reply",
  data(){
    return {
      textArea: null,
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
    // WriteRereply: import('@/components/WriteRereply'),
  },
  computed: {
    ...mapState({
      replys: state => state.boardStore.replys,
    })
  },
  methods: {
    addRereplyArea(){
      this.textArea = WriteRereply
    },

  }
}
</script>

<style scoped>

</style>

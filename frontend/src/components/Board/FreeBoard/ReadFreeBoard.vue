<template>
  <div class="wrap">
    <div class="ReadFreeBoard container elevation-1 pa-2 ma-2" :key="detailBoard">
      <v-card flat>
        <v-card-text>
          <v-row
              class="mb-4"
              align="center"
          >
            <v-avatar
                color="grey"
                class="mr-4"
            ></v-avatar>
            <strong class="title">주제: {{ detailBoard.title }}</strong>
            <v-spacer></v-spacer>
            <div>
              <v-icon>mdi-account</v-icon>
              {{ detailBoard.user.nickName }}
            </div>
          </v-row>
          {{ detailBoard.content }}
        </v-card-text>
      </v-card>
      <v-col>
        <WriteReply></WriteReply>
      </v-col>
      <v-col>
        <Reply></Reply>
      </v-col>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: "ReadFreeBoard",
  data (){
    return{
      replys: null
    }
  },
  components: {
    Reply: () =>import('@/components/Reply'),
    WriteReply: () => import('@/components/Board/FreeBoard/WriteReply')
  },
  computed: {
    ...mapState({
      detailBoard: state => state.boardStore.detailBoard
    })
  },
  methods: {
    // replaceContent(content){
    //   let contentArray = content.split('\n')
    //   console.log(contentArray)
    //   let htmls = ''
    //   contentArray.forEach( content => {
    //     htmls += <p>content</p>
    //     console.log(content)
    //   })
    //
    //   return this.stringToHTML(htmls)
    // },
    // stringToHTML(str) {
    //   let dom = document.createElement('div');
    //   dom.innerHTML = str;
    //   return dom;
    // }
  },
  created() {
    this.$store.dispatch("boardStore/getBoardContent", this.$route.params.boardId)
  },
}
</script>

<style scoped>
.wrap{
  display: flex;
  justify-content: center;
}
.ReadFreeBoard{
  /*text-align: center;*/
}
</style>

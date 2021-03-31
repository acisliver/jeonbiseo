<template>
  <div class="ReadFreeBoard">
    <v-container class="elevation-1 pa-2" :key="detailBoard">
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
            <strong class="title">제목: {{ detailBoard.title }}</strong>
            <v-spacer></v-spacer>
            <div>
              <v-icon>mdi-account</v-icon>
              {{detailBoard.user.nickName}}
            </div>
          </v-row>
          {{detailBoard.content}}
        </v-card-text>
      </v-card>
      <WriteReply></WriteReply>
      <Reply :replys="detailBoard.replys"></Reply>
    </v-container>
  </div>

</template>

<script>
import { mapState, mapActions } from "vuex"

export default {
  name: "ReadFreeBoard",
  data (){
    return{
      replys: null
    }
  },
  components: {
    Reply: () =>import('@/components/Reply'),
    WriteReply: () => import('@/components/WriteReply')
  },
  computed: {
    ...mapState(['detailBoard'])
  },
  methods: {
    ...mapActions(['getBoardContent']),
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
    this.getBoardContent(this.$route.params.boardId)
  },
}
</script>

<style scoped>

</style>

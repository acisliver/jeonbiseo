<template>
  <div class="ReadFreeBoard">
    <v-container class="elevation-1 pa-2">
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
            <strong class="title">제목: {{ boardData.title }}</strong>
            <v-spacer></v-spacer>
            <div>
              <v-icon>mdi-account</v-icon>
              {{boardData.user.nickName}}
            </div>
          </v-row>
          {{boardData.content.replace(/\n/ig, '')}}
        </v-card-text>
      </v-card>
    </v-container>
    <Reply></Reply>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "ReadFreeBoard",
  data (){
    return{
      boardData: null
    }
  },
  components: {
    Reply: () =>import('@/components/Reply')
  },
  computed: {

  },
  methods: {
    getBaordContent(){
      const url = '/api/free-board/' + this.$route.params.boardId
      console.log(url)
      axios
          .get(url)
          .then(res => {
            console.log(res)
            this.boardData = res.data.detailBoard
          })
          .catch(err => {
            console.log(err)
          })
    }
  },
  created() {
    this.getBaordContent()
  }
}
</script>

<style scoped>

</style>

<template>
  <div class="ReadDebateBoard container elevation-1 pa-2 ma-2">
<!--    토론 내용-->
    <div class="debatesummary">
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
              {{ detailBoard.user.nickName }}
            </div>
          </v-row>
          {{detailBoard.content}}
        </v-card-text>
      </v-card>
    </div>

    <v-divider/>

<!--    의견쓰기-->
    <div class="WriteOpinion">
      <v-radio-group
          v-model="reply.debateside"
          row
      >
        <v-radio
            value="pro"
            color="blue"
        >
          <template v-slot:label>
            <p class="blue--text ma-0">찬성</p>
          </template>
        </v-radio>
        <v-radio
            label="중립"
            value="negetive"
            color="black"
        >
          <template v-slot:label>
            <p class="black--text ma-0">중립</p>
          </template>
        </v-radio>
        <v-radio
            label="반대"
            value="con"
            color="red"
        >
          <template v-slot:label>
            <p class="red--text ma-0">반대</p>
          </template>
        </v-radio>
      </v-radio-group>
      <v-textarea
          v-model="reply.content"
          filled
          rows="4"
          height="100"
          class="mb-3 mt-3"
      ></v-textarea>
      <div class="BtnGroup">
        <v-btn @click="saveReply(reply)" class="mb-2">의견저장</v-btn>
      </div>
    </div>

    <v-divider/>

    <!--    통계-->
    <div class="DebateStatistics">
      <chart :chartdata=""></chart>
    </div>
    <v-divider/>

    <!--    의견들-->
    <div class="DebateOpinion">
      <v-timeline
          align-top
          :dense="$vuetify.breakpoint.smAndDown"
      >
        <v-timeline-item
            v-for="reply in replys"
            :key="reply.replyId"
            :color="reply.side === 'pros' ? 'blue' : 'red'"
            :left="reply.side === 'pros'"
            :right="reply.side === 'cons'"
            fill-dot
        >
          <v-card
              :color="reply.side === 'pros' ? 'blue' : 'red'"
              dark
          >
            <v-card-title class="title">
              {{ reply.title }}
            </v-card-title>
            <v-card-text class="white text--primary">
              <p> {{ reply.content }}</p>
              <v-btn
                  :color="reply.side === 'pros' ? 'blue' : 'red'"
                  class="mx-0"
                  outlined
              >
                의견쓰기
              </v-btn>
            </v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";

export default {
  name: "ReadDebateBoard",
  components: {
    chart: import("@/components/chart")
  },
  data: () => ({
    chartData: {
      type: 'doughnut',
      data: {

      }
    },
    reply: {
      content: '',
      debateside: null,
    },
    replys: [
      {
        title: '제목1',
        content: '의견의 내용들1',
        user: {},
        side: 'pros',
        parentId: null,
        replyId: 1,
        goodNum: 0,
        badNum: 0
      },
      {
        title: '제목2',
        content: '의견의 내용들2',
        user: {},
        side: 'pros',
        parentId: null,
        replyId: 2,
        goodNum: 0,
        badNum: 0
      },
      {
        title: '제목3',
        content: '의견의 내용들3',
        user: {},
        side: 'cons',
        parentId: null,
        replyId: 3,
        goodNum: 0,
        badNum: 0
      },
      {
        title: '제목4',
        content: '의견의 내용들4',
        user: {},
        side: 'cons',
        parentId: null,
        replyId: 4,
        goodNum: 0,
        badNum: 0
      },
    ],
  }),
  computed: {
    ...mapState({
      detailBoard: state => state.boardStore.detailBoard
    })
  },
  methods: {
    saveReply(replyObj){
      if(replyObj.content === '' || replyObj.content === null) {
        alert("내용을 입력해주세요")
        return;
      }
      else if(replyObj.debateside === null) {
        alert("찬/반을 선택해주세요")
        return;
      }
      console.log(replyObj)
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      this.reply.boardId = this.$route.params.boardId
      axios
          .post('/api/debate/' + this.reply.boardId + '/reply', replyObj, config)
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
  created() {
    this.$store.dispatch("boardStore/getDebateContent", this.$route.params.boardId)
  },
}
</script>

<style scoped>
  .v-input--radio-group--row .v-input--radio-group__input{
    justify-content: center;
  }
</style>
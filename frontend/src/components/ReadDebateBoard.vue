<template>
  <div class="wrap">
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
            <v-row>
              {{ detailBoard.content }}
            </v-row>
            <v-row class="modify-btn-group">
              <v-btn
                  @click="deleteDebate()"
                  class="ma-0 mb-2 white--text"
                  color="red"
              >삭제</v-btn>
            </v-row>
          </v-card-text>
        </v-card>
      </div>

      <v-divider/>

      <Timer :deadline="this.$store.state.boardStore.detailBoard.clearTime"></Timer>

      <v-divider/>

      <PieChart :chart-data-obj="this.$store.state.boardStore.chartData"></PieChart>

      <v-divider/>

  <!--    의견쓰기-->
      <div class="WriteOpinion">
        <v-radio-group
            v-model="reply.prosAndCons"
            row
            class="radio-group"
        >
          <v-radio
              value="pros"
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
              value="cons"
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
        <div class="btn-group">
          <v-btn @click="saveReply(reply)" class="mb-2">의견저장</v-btn>
        </div>
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
              :color="setReplyColor(reply.prosAndCons)"
              :left="reply.prosAndCons === 'pros'"
              :right="reply.prosAndCons === 'cons'"
              fill-dot
          >
            <span slot="opposite"> <v-icon>mdi-account</v-icon>{{reply.user.nickName}} </span>
            <v-card
                :color="setReplyColor(reply.prosAndCons)"
                dark
            >
              <v-card-title class="title" v-if="reply.prosAndCons==='pros'">
                찬성
              </v-card-title>
              <v-card-title class="title" v-if="reply.prosAndCons==='cons'">
                반대
              </v-card-title>
              <v-card-title class="title" v-if="reply.prosAndCons==='negetive'">
                중립
              </v-card-title>
              <v-card-text class="white text--primary">
                <v-row class="pa-2">
                  <p> {{ reply.content }}</p>
                </v-row>
                <v-row class="save-btn-parent">
                  <v-btn
                      :color="setReplyColor(reply.prosAndCons)"
                      class="mx-0"
                      outlined
                  >
                    의견쓰기
                  </v-btn>
                </v-row>

              </v-card-text>
            </v-card>
          </v-timeline-item>
        </v-timeline>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";
import PieChart from "./PieChart";
import Timer from  "./Timer"

export default {
  name: "ReadDebateBoard",
  components: {
    PieChart: PieChart,
    Timer: Timer
  },
  data: () => ({
    chartData: {
      type: 'doughnut',
      data: {

      }
    },
    reply: {
      content: '',
      prosAndCons: 'negetive',
      reparentId: 0,
      debateBoardId: null
    },
    testreplys: [
      {
        title: '제목1',
        content: '의견의 내용들1',
        user: {},
        prosAndCons: 'pros',
        reparentId: null,
        replyId: 1,
        goodNum: 0,
        badNum: 0
      },
      {
        title: '제목2',
        content: '의견의 내용들2',
        user: {},
        prosAndCons: 'pros',
        reparentId: null,
        replyId: 2,
        goodNum: 0,
        badNum: 0
      },
      {
        title: '제목3',
        content: '의견의 내용들3',
        user: {},
        prosAndCons: 'cons',
        reparentId: null,
        replyId: 3,
        goodNum: 0,
        badNum: 0
      },
      {
        title: '제목4',
        content: '의견의 내용들4',
        user: {},
        prosAndCons: 'cons',
        reparentId: null,
        replyId: 4,
        goodNum: 0,
        badNum: 0
      },
    ],
  }),
  computed: {
    ...mapState({
      detailBoard: state => state.boardStore.detailBoard,
      replys: state => state.boardStore.replys
    })
  },
  methods: {
    setReplyColor(prosAndCons){
      if(prosAndCons === 'pros') return 'blue';
      else if(prosAndCons === 'cons') return 'red';
      else return '#000000'
    },
    deleteDebate(){
      console.log("deleteDebate")
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      axios
          .delete("/api/debate/" + this.$route.params.boardId, config)
          .then(res => {
            this.$router.push({name: 'DebateBoard'})
            console.log(res)
          })
          .catch(err => {
            alert("작성자만 게시글을 삭제할 수 있습니다.")
            console.log(err)
          })
    },
    saveReply(replyObj){
      if(replyObj.content === '' || replyObj.content === null) {
        alert("내용을 입력해주세요")
        return;
      }
      else if(replyObj.prosAndCons === null) {
        alert("찬/반을 선택해주세요")
        return;
      }
      console.log(replyObj)
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      this.reply.debateBoardId = this.$route.params.boardId
      axios
          .post('/api/debate/' + this.reply.boardId + '/reply', replyObj, config)
          .then(res => {
            // alert('저장 완료')
            this.$emit("updateReplys", res.data.detailBoard.replys)
            console.log(res)
            this.$router.go()
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

<style lang="scss">
  .wrap{
    display: flex;
    justify-content: center;
  }
  .radio-group{
    display: flex;
    justify-content: center;
  }
  .v-input--radio-group__input{
    display: flex;
    justify-content: center;
  }
  .btn-group{
    display: flex;
    justify-content: flex-end;
  }
  .save-btn-parent{
    display: flex;
    justify-content: flex-end;
  }
  .modify-btn-group{
    display: flex;
    justify-content: flex-end;
  }
</style>
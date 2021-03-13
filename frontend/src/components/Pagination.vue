<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
      <tr>
        <th class="text-left">
          {{ title }}
        </th>
        <th class="text-right">
          작성자
        </th>
        <!--        <th class="text-right">-->
        <!--          작성일자-->
        <!--        </th>-->
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="board in paginatedData"
          :key="board.id"
          @click="$router.push({
          name: 'ReadFreeBaord',
          params: {
            boardId: board.id
          }
          })"
      >
        <td class="text-left">{{ board.title }}</td>
        <td class="text-right">{{ board.user.nickName }}</td>
        <!--        <td class="text-right">{{ board.writeTime}}</td>-->
      </tr>
      </tbody>
<!--      <div class="text-center">-->
<!--        <v-container>-->
<!--          <v-row justify="center">-->
<!--            <v-col cols="8">-->
<!--              <v-container class="max-width">-->
<!--                <v-pagination-->
<!--                    v-model="pageNum"-->
<!--                    class="my-4"-->
<!--                    :length="15"-->
<!--                ></v-pagination>-->
<!--              </v-container>-->
<!--            </v-col>-->
<!--          </v-row>-->
<!--        </v-container>-->
<!--      </div>-->
      <div class="align-end">
        <v-btn :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </v-btn>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <v-btn :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
          다음
        </v-btn>
        <v-btn @click="clickSaveBtn">
          글쓰기
        </v-btn>
      </div>
    </template>
  </v-simple-table>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "Pagination",
  data(){
    return {
      pageNum: 0
    }
  },
  props: {
    title: {
      type: String,
      required: true
    },
    header: {
      type: null,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 7
    },
    boardName: {
      type: String,
      required: true
    }
  },
  methods: {
    nextPage(){
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    clickSaveBtn(){
      if(this.isLogin){
        this.$router.push({name: 'Write'+this.boardName})
      }
      else{
        alert('로그인이 필요합니다')
      }
    }
  },
  computed: {
    ...mapState(['isLogin']),
    pageCount () {
      if (this.header === null ){
        return 1;
      }
      let listLeng = this.header.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
          end = start + this.pageSize;
      if (this.header === null){
        return this.header;
      }
      return this.header.slice(start, end);
    }
  }
}
</script>

<style scoped>
a{
  text-decoration: none;
  color: black;
}
</style>

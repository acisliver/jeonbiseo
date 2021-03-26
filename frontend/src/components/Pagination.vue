<template>
  <div class="pagination">
    <v-simple-table>
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
            @click="clickReadBtn(board.id)"
        >
          <td class="text-left">{{ board.title }}</td>
          <td class="text-right">{{ board.user.nickName }}</td>
          <!--        <td class="text-right">{{ board.writeTime}}</td>-->
        </tr>
        </tbody>
    </v-simple-table>
      <div class="text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
              <v-container class="max-width">
                <v-pagination
                    v-model="pageNum"
                    :length="pageCount"
                ></v-pagination>
              </v-container>
            </v-col>
          </v-row>
        </v-container>
        <v-btn @click="clickSaveBtn" class="black--text white justify-end align-center">
          글쓰기
        </v-btn>
      </div>

  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "Pagination",
  data(){
    return {
      pageNum: 1
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
      default: 10
    },
    boardName: {
      type: String,
      required: true
    }
  },
  methods: {
    clickSaveBtn(){
      if(this.isLogin){
        this.$router.push({ name: 'Write'+this.boardName })
      }
      else{
        alert('로그인이 필요합니다')
      }
    },
    clickReadBtn(id){
      this.$router.push({ name: 'ReadFreeBoard' ,params: { boardId: id}})
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
      return page;
    },
    paginatedData () {
      const start = (this.pageNum-1) * this.pageSize,
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

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
      >
        <td class="text-left">{{ board.title }}</td>
        <td class="text-right">{{ board.writer }}</td>
        <!--        <td class="text-right">{{ board.writeTime}}</td>-->
      </tr>
      </tbody>
      <v-spacer/>
      <div class="btn-cover">
        <v-btn :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </v-btn>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <v-btn :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
          다음
        </v-btn>
      </div>
    </template>
  </v-simple-table>
</template>

<script>
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
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 7
    }
  },
  methods: {
    nextPage(){
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    }
  },
  computed: {
    pageCount () {
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
      return this.header.slice(start, end);
    }
  }
}
</script>

<style scoped>

</style>

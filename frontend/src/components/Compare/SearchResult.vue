<template>
  <div class="search-application-result mb-2">
    <v-card
        class="container"
        style="color: gray;"
        height="100%"
    >
      <v-row  v-if="searchResult.length > 0" class="justify-space-between">

        <v-icon @click="prevPage">mdi-arrow-left-circle-outline</v-icon>

        <!--검색결과 이미지 카드-->
        <v-card
            v-for="result in paginatedData"
            :key="result.id"
            class="d-flex align-content-space-between flex-wrap justify-center"
            width="17%"
            @click="inCompare"
        >

          <!--이미지 래퍼-->
          <div style="width: 100%;">
            <img
                width="100%"
                height="auto"
                :src="result.url"
                @click="inCompare"
                style="pointer-events: none"
            >
          </div>

          <!--제품명-->
          <div @click="inCompare">{{result.productName}}</div>

          <!--비교중 오버레이-->
          <v-overlay
              absolute
              :value="false"
              style="pointer-events: none"
            >
            <v-icon x-large>mdi-check-circle</v-icon>
          </v-overlay>

        </v-card>

        <v-icon @click="nextPage">mdi-arrow-right-circle-outline</v-icon>

      </v-row>
      <div
          v-else
          class="container"
          style="color: gray;"
      >
        검색 결과가 없습니다.
      </div>
    </v-card>

<!--결과 페이지 마지막 알림-->
    <v-snackbar
        v-model="snackbar"
    >
      {{ pageText }} 페이지입니다.

      <template v-slot:action="{ attrs }">
        <v-btn
            color="red"
            text
            v-bind="attrs"
            @click="snackbar = false"
        >
          닫기
        </v-btn>
      </template>
    </v-snackbar>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchResult",
  data() {
    return {
      pageSize: 5,
      pageNum: 1,
      pageText: '첫',
      snackbar: false,
    }
  },
  props: {
    searchResult: {
      type: Array,
    },
    comparingProducts: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  computed: {
    pageCount () {
      if (this.searchResult === null ){
        return 1;
      }
      let listLeng = this.searchResult.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData () {
      const start = (this.pageNum-1) * this.pageSize,
          end = start + this.pageSize;
      if (this.searchResult === null){
        return this.searchResult;
      }
      return this.searchResult.slice(start, end);
    },
  },
  methods: {
    prevPage(){
      if (this.pageNum > 1) this.pageNum -= 1
      else {
        this.pageText = "첫"
        this.snackbar = true
      }
    },
    nextPage(){
      if (this.pageNum < this.pageCount) this.pageNum += 1
      else {
        this.pageText = "마지막"
        this.snackbar = true
      }
    },
    //event propagation 활용
    //클릭 시 img나 div가 target이 됨
    //div만 target이 되어야 해서 최상위에서 데이터 처리
    inCompare(event) {
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }

      //productName에서 이벤트가 발생한게 아니라면 이 값은 ""이 된다.
      const target = event.currentTarget.innerText

      //텍스트에서 이벤트핸들링 함수가 발생했을 경우
      //overlay에서 이벤트가 발생할 경우 이벤트 버블링이 제대로 되지 않는다.
      //이유는 overlay의 사이즈가 최상단의 부모 노드의 사이즈와 같기 때문에 overlay에서만 이벤트가 발생하고 끝
      //따라서 캡처링을 추가해줄 필요가 있다.
      if (target !== ""){
        const selectedProduct = this.searchResult.find(el => el.productName === target)

        axios.get("/api/compare/select?sqlid=" + selectedProduct.sqlId, config)
            .then(res => this.$emit("comparing", res.data))
            .catch(e => console.log(e))
      }

    },
  }
}
</script>

<style scoped>

</style>
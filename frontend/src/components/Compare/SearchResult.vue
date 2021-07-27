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
            class="d-flex align-content-space-between flex-wrap justify-center pa-1"
            :class="['sqlId' + result.sqlId]"
            width="17%"
            @click="inCompare"
        >

          <!--이미지 래퍼-->
          <div style="width: 100%;">
            <img
                width="100%"
                height="auto"
                :src="result.url"
                style="pointer-events: none"
            >
          </div>

          <!--제품명-->
          <div>{{result.productName}}</div>

          <!--비교중 오버레이-->
          <v-overlay
              absolute
              :value="true"
              style="pointer-events: none; visibility: hidden"
          >
            <v-icon x-large>mdi-check-circle</v-icon>
          </v-overlay>

        </v-card>

        <v-icon @click="nextPage">mdi-arrow-right-circle-outline</v-icon>

      </v-row>

      <!--default 창-->
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
      {{ snackbarText }}

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
      snackbarText: '',
      snackbar: false,
      overlayList: []
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
        this.snackbarText = "첫 페이지입니다."
        this.snackbar = true
      }
    },
    nextPage(){
      if (this.pageNum < this.pageCount) this.pageNum += 1
      else {
        this.snackbarText = "마지막 페이지입니다."
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

      //productName or v-card 에서 이벤트가 발생한게 아니라면 이 값은 ""이 된다.
      const targetNode = event.currentTarget
      const targetInnerText = targetNode.innerText

      //클릭 시 오버레이 visible
      const overlay = targetNode.children[2]
      overlay.style.visibility = "visible"


      //이미지에서 이벤트 발생 시 이벤트 버블링을 통해 상위에서 처리
      if (targetInnerText !== ""){
        const selectedProduct = this.searchResult.find(el => el.productName === targetInnerText)

        axios.get("/api/compare/select?sqlid=" + selectedProduct.sqlId, config)
            .then(res => this.$emit("comparing", res.data))
            .catch(e => console.log(e))
      }
    },
    hiddenOverlay(id){
      document.querySelector('.sqlId' + id).children[2].style.visibility = "hidden"
    }
  }
}
</script>

<style scoped>

</style>
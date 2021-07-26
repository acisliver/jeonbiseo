<template>
  <div class="search-application-result mb-2">
    <v-card
        class="container"
        style="color: gray;"
        height="100%"
    >
      <v-row  v-if="searchResult.length > 0" class="justify-space-between">
        <v-icon @click="prevPage">mdi-arrow-left-circle-outline</v-icon>
        <v-card
            v-for="result in paginatedData"
            :key="result.id"
            class="d-flex align-content-space-between flex-wrap justify-center"
            width="17%"
            @click="inCompare"
        >
          <div style="width: 100%;">
            <img
                width="100%"
                height="auto"
                :src="result.url"
                @click="inCompare"
            >
          </div>

          <div
              @click="inCompare"
          >{{result.productName}}</div>
<!--          <v-overlay-->
<!--              :absolute="absolute"-->
<!--              :value="overlay"-->
<!--            >-->
<!--            <v-btn-->
<!--                color="success"-->
<!--                @click="overlay = false"-->
<!--            >-->
<!--              비교중-->
<!--            </v-btn>-->
<!--          </v-overlay>-->
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
import {mapState, mapActions} from "vuex"

export default {
  name: "SearchResult",
  data() {
    return {
      pageSize: 5,
      pageNum: 1,
      pageText: '첫',
      snackbar: false,
      absolute: true,
      overlay: true,
    }
  },
  props: {
    searchResult: {
      type: Array,
    },
    comparingProducts: {
      type: Array
    }
  },
  computed: {
    ...mapState({
      selectedDevice: state => state.selectedDevice
    }),
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
    compare() {
      return this.comparingProducts.map(el => el.value)
    }
  },
  methods: {
    ...mapActions({
      selectDevice: "applicationStore/selectDevice"
    }),
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
      const target = event.currentTarget.innerText
      //텍스트에서 이벤트핸들링 함수가 발생했을 경우
      if (target !== ""){
        console.log(target)
        const selectedProduct = this.searchResult.find(el => el.productName === target)
        // this.$emit("comparing", selectedProduct)
        console.log(selectedProduct)
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
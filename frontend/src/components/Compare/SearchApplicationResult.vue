<template>
  <div class="search-application-result">
    <v-card
        elevation="1"
    >
      <v-row>
        <v-card
            v-for="result in searchResult"
            :key="result.id"
            draggable="true"
            @click="inCompare"
        >
          <img
              width="200px"
              height="auto"
              :src="result.url"
              @click="inCompare"
          >
          <div
              @click="inCompare"
          >{{result.productName}}</div>
        </v-card>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchApplicationResult",
  data() {
    return {
      testResults: [
        {
          "id": "_646vXkBdB9dqcghTKrM",
          "sqlId": "11",
          "url": "http://img.danawa.com/prod_img/500000/183/857/img/10857183_1.jpg?shrink=160:160&_v=20210408134759",
          "brand": "Apple",
          "productName": "Ipad Pro 11 2세대"
        },
        {
          "id": "_q40vXkBdB9dqcghzqqU",
          "sqlId": "10",
          "url": "http://img.danawa.com/prod_img/500000/042/284/img/12284042_1.jpg?shrink=160:160&_v=20210408133815",
          "brand": "Apple",
          "productName": "Ipad Air 4"
        },
        {
          "id": "_a4rvXkBdB9dqcghvqo-",
          "sqlId": "2",
          "url": "http://img.danawa.com/prod_img/500000/042/284/img/12284042_1.jpg?shrink=160:160&_v=20210408133815",
          "brand": "Apple",
          "productName": "IPadAir4"
        },
        {
          "id": "5UkpvXkBCCBSTmfPm85R",
          "sqlId": "2",
          "url": "http://img.danawa.com/prod_img/500000/042/284/img/12284042_1.jpg?shrink=160:160&_v=20210408133815",
          "brand": "Apple",
          "productName": "IPadAir4"
        },
        {
          "id": "cUPUpXkBK2u0lVY2AuXw",
          "sqlId": "2",
          "url": "http://img.danawa.com/prod_img/500000/042/284/img/12284042_1.jpg?shrink=160:160&_v=20210408133815",
          "brand": "Apple",
          "productName": "IPadAir4"
        },
        {
          "id": "A65ZvXkBdB9dqcghz6tI",
          "sqlId": "12",
          "url": "http://img.danawa.com/prod_img/500000/717/863/img/10863717_1.jpg?shrink=160:160&_v=20210408134947",
          "brand": "Apple",
          "productName": "Ipad Pro 12.9 4세대"
        },
        {
          "id": "Aq5ZvXkBdB9dqcghz6tI",
          "sqlId": "13",
          "url": "http://img.danawa.com/prod_img/500000/916/485/img/7485916_1.jpg?shrink=160:160&_v=20201125173109",
          "brand": "Apple",
          "productName": "Ipad Mini 5세대"
        }
      ]
    }
  },
  props: {
    searchResult: {
      type: Array,
    }
  },
  methods: {
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
      //텍스트에세 이벤트핸들링 함수가 발생했을 경우
      if (target !== ""){
        const selectedProduct = this.searchResult.find(el => el.productName === target)
        // this.$emit("comparing", selectedProduct)
        console.log(selectedProduct)
        axios.get("/api/compare/select?sqlid=" + selectedProduct.sqlId, config)
            .then(res => this.$emit("comparing", res.data))
            .catch(e => console.log(e))
      }

    }
  }
}
</script>

<style scoped>

</style>
<template>
  <div class="search-box">
    <v-card elevation="1">
      <v-card-text>
        <div class="container">
          <div class="row flex-column">
            <v-text-field
                v-model="searchWord"
                label="검색어"
                filled
                dense
                rounded
                :append-icon="'mdi-magnify'"
                @click:append="searchApplication(searchWord)"
            ></v-text-field>
            <v-checkbox
                v-model="selectedList"
                label="운영체제"
                value="os"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="무게"
                value="appWeight"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="화면크기"
                value="appSize"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="전용터치펜"
                value="usePen"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="테블릿통신"
                value="network"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="RAM용량"
                value="ramSize"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="저장용량"
                value="hddSize"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="배터리용량"
                value="batterySize"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="전면카메라"
                value="frontCamera"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="후면카메라"
                value="backCamera"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="스피커개수"
                value="speakerNum"
                hide-details
            ></v-checkbox>
            <v-checkbox
                v-model="selectedList"
                label="화면비율"
                value="screenRatio"
                hide-details
            ></v-checkbox>
            <v-btn
                width="0.5em"
                class="ma-2"
                @click="sendSelectedList"
            >
              적용
            </v-btn>
          </div>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchBox",
  data() {
    return {
      selectedList: [],
      searchWord: "",
    }
  },
  methods: {
    //검색어로 검색
    searchApplication(searchWord){
      console.log(searchWord)
      const url = "/api/compare/search"
      const params = `?searchWord=${searchWord}`
      axios
          .get(url + params)
          .then(res => {
            console.log(res)
          })
          .catch(e => console.log(e))
    },
    //카테고리 선택
    sendSelectedList(){
      const url = "/api/compare/list"
      let config = {
        headers: {
          // token: localStorage.getItem('token')
        }
      }
      const body = this.selectedList
      console.log(body)
      axios
          .post(url, body, config)
          .then(res => console.log(res))
          .catch(e => console.log(e))
    },

  }
}
</script>

<style scoped>

</style>
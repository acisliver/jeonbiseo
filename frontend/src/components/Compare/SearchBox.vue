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
                v-model="selectAll"
                label="전체선택"
                :value="selectAll"
                color="success"
                hide-details
                @click="selectCategory"
            ></v-checkbox>

            <v-checkbox
              v-for="item in selectedList"
              :key="item.name"
              v-model="item.value"
              :label="item.label"
              :value="item.value"
              hide-details
              @click="selectCategory"
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
      selectedList: [
        {
          name: 'os',
          value: true,
          label: '운영체제'
        },
        {
          name: 'appWeight',
          value: true,
          label: '무게'
        },
        {
          name: 'appSize',
          value: true,
          label: '화면크기'
        },
        {
          name: 'usePen',
          value: true,
          label: '전용터치펜'
        },
        {
          name: 'network',
          value: true,
          label: '테블릿통신'
        },
      ],
      searchWord: "",
    }
  },
  computed: {
    selectAll: {
      get: function () {
        let flag = true
        this.selectedList.forEach(el => {
          if (!el.value) flag = null
        })
        return flag
      },
      set: function (newVal) {
        console.log('newVal: ' + newVal)
        this.selectedList.forEach(el => {
          el.value = newVal
        })
      }
    }
  },
  methods: {
    /**
     * 검색어로 전자기기 검색
     * @param {String} searchWord 검색어
     */
    searchApplication(searchWord){
      console.log(searchWord)
      const url = "/api/compare/search"
      const params = `?searchWord=${searchWord}`
      axios
          .get(url + params)
          .then(res => {
            console.log(res)
            this.$emit("search", res.data)
          })
          .catch(e => console.log(e))
    },
    /**
     * 카테고리 선택 시 해당 데이터를 서버로 전송하여 추천 정보로 이용
     */
    sendSelectedList(){
      const url = "/api/compare/list"
      // let config = {
      //   headers: {
      //     // token: localStorage.getItem('token')
      //   }
      // }

      //value만 추출하여 배열로 만든다
      const list = []
      this.selectedList.forEach(el => list.push(el.name))
      console.log(list)

      //서버로 전송
      const body = { "selectedList": JSON.stringify(this.selectedList)}
      axios
          .post(url, body)
          .then(res => console.log(res))
          .catch(e => console.log(e))
    },
    selectCategory() {
      console.log(this.selectedList)
      this.$emit('category', this.selectedList)
    }
  }
}
</script>

<style scoped>

</style>
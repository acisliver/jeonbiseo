<template>
  <div class="compare">
    <v-container class="align-stretch">
      <h2 style="margin: 1rem">전자기기 비교</h2>
      <v-row>
        <v-col
          cols="9"
        >
          <comparing-appliaction :comparing-products="comparingProducts" :category-list="categoryList" @out-compare="deleteProduct"/>
        </v-col>
        <v-col
          cols="3"
        >
          <search-box @search="getSearchResult" @category="getCategoryList"/>
        </v-col>
        <v-col
            cols="12"
        >
          <search-result :search-result="searchResult" :comparing-products="comparingProducts" @comparing="getProduct"/>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>

export default {
  name: "Compare",
  data(){
    return {
      comparingProducts: [],
      searchResult: [],
      categoryList: [
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
      ]
    }
  },
  components:{
    SearchBox: () => import("../components/Compare/SearchBox"),
    ComparingAppliaction: () => import("../components/Compare/ComparingApplication"),
    SearchResult: () => import("../components/Compare/SearchResult")
  },
  methods: {
    /**
     * SearchResult로 부터 제품정보를 받아오는 메소드
     * 이미 선택된 제품은 선택되지 않게 필터링
     * @param {Object} data application object
     */
    getProduct(data) {
      //비교중인 제품인지 확인
      const isCompare = this.comparingProducts.some(el => {
        if (el.id === data.id){
          alert("비교중인 제품입니다.")
          return true;
        }
      })
      //비교중이 아니라면 ComparingApplication에 제품정보를 추가
      if(!isCompare) this.comparingProducts.push(data)
    },
    /**
     * 비교 중 삭제한 제품 삭제
     * @param {Object} data applicaion object
     */
    deleteProduct(data){
      const itemToFind = this.comparingProducts.find(el => {return el.id === data.id})
      const idx = this.comparingProducts.indexOf(itemToFind)
      if(idx > -1) this.comparingProducts.splice(idx, 1)
    },
    getSearchResult(data) {
      this.searchResult = data
    },
    getCategoryList(list) {
      this.categoryList = list
    }
  }
}
</script>

<style scoped>
.compare{
  text-align: center;
}
</style>

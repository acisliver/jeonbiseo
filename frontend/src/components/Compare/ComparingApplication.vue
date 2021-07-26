<template>
  <div class="comparing-application" style="height: 100%;">
    <v-card
        class="container"
        style="color: gray;"
        height="100%"
    >
      <v-row v-if="comparingProducts.length > 0">
        <v-card
            v-for="result in comparingProducts"
            :key="result.id"
            class="d-flex flex-column justify-space-between flex-wrap"
            width="25%"
        >
          <div style="width: 100%;">
            <img
                width="100$"
                height="auto"
                :src="result.url"
            >
          </div>

          <div class="category">
            <div>{{result.productName}}</div>
            <div>{{result.brand}}</div>
            <div> {{result.os}}</div>
            <div v-if="isAppSize">사이즈 {{result.appSize}}</div>
            <div v-if="isAppWeight">무게 {{result.appWeight}}</div>
            <div v-if="isNetwork">{{result.network}}</div>
            <div v-if="isUsePen">펜사용 {{result.usePen}}</div>
          </div>
        </v-card>
      </v-row>
      <div v-else>비교할 제품을 클릭해주세요</div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "ComparingApplication",
  data() {
    return {

    }
  },
  props: {
    comparingProducts: {
      type: Array,
      required: true
    },
    categoryList: {
      type: Array,
      required: true
    }
  },
  computed: {
    isOs() {
      console.log(this.categoryList.find(el => el.name === 'os').value)
      return this.categoryList.find(el => el.name === 'os').value
    },
    isAppSize() {
      console.log(this.categoryList)
      return this.categoryList.find(el => el.name === 'appSize').value
    },
    isAppWeight() {
      return this.categoryList.find(el => el.name === 'appWeight').value
    },
    isNetwork() {
      return this.categoryList.find(el => el.name === 'network').value
    },
    isUsePen() {
      return this.categoryList.find(el => el.name === 'usePen').value
    }
  },
  methods: {
    selectDevice: function () {

    },
  }

}
</script>

<style scoped>

</style>
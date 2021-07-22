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
              width="25%"
              height="100%"
          >
            <img
                width="100%"
                height="auto"
                :src="result.url"
            >
            <div>{{result.productName}}</div>
            <div>{{result.brand}}</div>
            <div>{{result.os}}</div>
            <div>사이즈 {{result.appSize}}</div>
            <div>무게 {{result.appWeight}}</div>
            <div>{{result.network}}</div>
            <div>펜사용 {{result.usePen}}</div>
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
    }
  },
  methods: {
    selectDevice: function () {

    }
  }

}
</script>

<style scoped>

</style>
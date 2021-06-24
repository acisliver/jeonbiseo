<template>
  <div class="comparing-application">
    <v-card
        elevation="1"
    >
      <v-row>
        <v-card
            v-for="result in comparingProducts"
            :key="result.id"
            draggable="true"

        >
          <img
              width="300px"
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

  }

}
</script>

<style scoped>

</style>
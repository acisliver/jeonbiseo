<template>
  <div class="comparing-application">
    <v-card
        elevation="1"
        height="600px"
    >
      <v-row>
        <v-card
            v-for="result in comparingProducts"
            :key="result.id"
            draggable="true"

        >
          <img
              width="200px"
              height="auto"
              :src="result.url"
          >
          <div>{{result.productName}}</div>
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
<template>
  <div class="WriteFree container">
    <h2>토론게시판글쓰기</h2>
    <v-text-field
        v-model="contents.title"
        label="주제"
    ></v-text-field>
    <v-textarea
        v-model="contents.content"
        filled
        rows="4"
        height="600"
    ></v-textarea>
    <v-btn @click="saveContent(contents)">저장</v-btn>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "WriteDebate",
  data(){
    return {
      contents:{
        title:'',
        content:'',
      }
    }
  },
  methods: {
    saveContent(contentObj){
      let config = {
        headers: {
          token: localStorage.getItem('token')
        }
      }
      console.log(contentObj)
      axios
          .post('/api/debate/write/save', contentObj, config)
          .then(res => {
            alert('저장 완료')
            console.log(res)
            this.$router.back()
          })
          .catch(err => {
            console.log(err)
          })
    }
  }
}
</script>

<style scoped>

</style>

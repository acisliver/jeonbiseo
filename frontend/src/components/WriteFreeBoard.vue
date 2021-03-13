<template>
  <div class="writeBoard container">
    <h2>자유게시판글쓰기</h2>
    <v-text-field
        v-model="contents.title"
        label="제목"
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
  name: "WriteBoard",
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
          //token: localStorage.getItem('token')
        }
      }
      console.log(contentObj)
      axios
      .post('/api/free-board/write/save', contentObj, config)
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

<template>
  <div>
    <h2>
      환영합니다!
    </h2>
    <v-container class="fill-height" style="max-width: 500px;" >
      <v-layout row wrap align-center>
        <v-flex xs12>
          <v-card style="max-width: 500px;">
            <v-toolbar flat class="indigo lighten-5">
              <v-toolbar-title>개인정보</v-toolbar-title>
            </v-toolbar>
            <div class="pa-3">
              <v-text-field
                  v-model="username"
                  label="이메일"
              >
              </v-text-field>
              <v-text-field
                  v-model="userInfo.password"
                  label="비밀번호"
              >
              </v-text-field>
              <v-text-field
                  v-model="userInfo.nickName"
                  label="닉네임"
              >
              </v-text-field>
              <v-btn
                  class="editBtn"
                  large
              >수정</v-btn>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

const userStore = "userStore"

export default {
  name: "MyPage",
  computed: {
    ...mapState(userStore, ['userInfo']),
    username() {
      return this.$route.params.username
    }
  },
  methods: {
    ...mapActions(userStore, ['getUserInfo'])
  },
  created() {
    // this.getUserInfo()
  }
}
</script>

<style scoped>
.editBtn{
  text-decoration: none;
  font-weight: bold;
  color: #2c3e50;
  padding: 10px;
}
</style>

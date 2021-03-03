<template>
  <v-container class="fill-height" style="max-width: 500px;" >
    <v-layout row wrap align-center>
      <v-flex xs12>
        <v-alert
            :value="isLoginError"
            dense
            outlined
            type="error"
        >
          아이디와 비밀번호를 확인해주세요
        </v-alert>
        <v-alert
            :value="isLogin"
            dense
            outlined
            type="success"
        >
          로그인이 완료되었습니다
        </v-alert>
        <v-card style="max-width: 500px;">
          <v-toolbar flat class="indigo lighten-5">
            <v-toolbar-title>로그인</v-toolbar-title>
          </v-toolbar>
          <div class="pa-3">
            <v-text-field
                v-model="user_name"
                label="이메일"
            >
            </v-text-field>
            <v-text-field
                v-model="password"
                label="비밀번호"
            >
            </v-text-field>
            <v-btn
                color="primary"
                depressed
                large
                block
                @click="login({user_name, password})"
            >로그인
            </v-btn>
            <v-btn @click="$router.push({ name:'SignUp' })" >회원가입</v-btn>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
import axios from "axios";

export default {
  name: "Login",
  data(){
    return{
      user_name: null,
      password: null,
    }
  },
  computed: {
    ...mapState(['isLoginError', 'isLogin']),
  },
  methods: {
    login(loginObj) {
      console.log(loginObj)
      axios
          .post("/login",loginObj)
          .then(res => {
            console.log(res.data)
          })
          .catch(err => {
            console.log(err)
          })
    }

    }
}
</script>


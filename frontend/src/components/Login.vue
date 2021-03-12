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
        <v-card style="max-width: 500px;">
          <v-toolbar flat class="indigo lighten-5">
            <v-toolbar-title>로그인</v-toolbar-title>
          </v-toolbar>
          <div class="pa-3">
            <v-text-field
                v-model="userName"
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
                @click="login({userName, password})"
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
import { mapState, mapActions } from 'vuex'
import axios from "axios";

export default {
  name: "Login",
  data(){
    return{
      userName: null,
      password: null,
    }
  },
  computed: {
    ...mapState(['isLoginError', 'isLogin']),
  },
  methods: {
    ...mapActions(['loginAction', 'logoutAction']),
    login(loginObj) {
      console.log(loginObj)
      axios
          .post("/login",loginObj)
          .then(res => {
            console.log(res)
            let statusOk = res.data.status
            let token = res.headers.access_token
            localStorage.setItem("token",token);
            this.$store.dispatch('loginAction', statusOk)
          })
          .catch(err => {
            console.log(err)
          })
    },
    }
}
</script>


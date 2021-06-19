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
                :append-icon="isShow ? 'mdi-eye' : 'mdi-eye-off'"
                :type="isShow ? 'text' : 'password'"
                @click:append="isShow = !isShow"
                label="비밀번호"
            >
            </v-text-field>
            <v-btn
                color="primary"
                depressed
                large
                block
                class="mb-2"
                @click="login({userName, password})"
            >로그인
            </v-btn>
            <v-btn
                large
                block
                @click="$router.push({ name:'SignUp' })"
            >
              회원가입
            </v-btn>
          </div>
        </v-card>
        <v-alert
            dense
            outlined
            type="error"
            v-show="failLogin"
        >
          아이디나 비밀번호가 틀렸습니다
        </v-alert>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
import axios from "axios"

export default {
  name: "Login",
  data(){
    return{
      userName: null,
      password: null,
      isShow: false,
      failLogin: false
    }
  },
  computed: {
    ...mapState({
      isLogin: state => state.userStore.isLogin,
      isLoginError: state => state.userStore.isLoginError
    }),
  },
  methods: {
    login(loginObj) {
      console.log(loginObj)
      axios
          .post("/login",loginObj)
          .then(res => {
            let statusOk = res.status
            if(statusOk===200){
              localStorage.setItem("token", res.headers.token)
              this.$router.push({name:'Home'})
              this.$store.dispatch('userStore/loginAction', statusOk, loginObj.userName)
            }
            else if(statusOk===204){
              this.failLogin = true
            }
          })
          .catch(err => {
            console.log(err)
          })
    },
    }
}
</script>


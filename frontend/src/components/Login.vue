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
                v-model="email"
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
                @click="login({email, password})"
            >로그인
            </v-btn>
<!--            <v-btn @click="test">테스트</v-btn>-->
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "Login",
  data(){
    return{
      email: '',
      password: '',
    }
  },
  computed: {
    ...mapState(['isLoginError', 'isLogin']),
  },
  methods: {
    ...mapActions(['login']),
    }
}
</script>


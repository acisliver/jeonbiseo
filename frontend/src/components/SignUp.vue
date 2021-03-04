<template>
  <v-container class="fill-height" style="max-width: 700px">
    <v-row justify="center">
      <v-col
          cols="12"
          sm="10"
          md="8"
          lg="6"
      >
        <v-card>
          <v-card-text>
            <v-text-field
                v-model="userName"
                :rules="[() => !!userName || '필수입력입니다.']"
                label="이메일 혹은 전화번호"
                required
            ></v-text-field>
            <v-text-field
                v-model="password"
                :rules="[() => !!password || '필수입력입니다.']"
                label="비밀번호"
                maxlength="25"
                counter="25"
                required
            ></v-text-field>
            <v-text-field
                v-model="nickName"
                :rules="[
                () => !!nickName || '필수입력입니다.']"
                label="닉네임"
                maxlength="25"
                counter="25"
                required
            ></v-text-field>
          </v-card-text>

          <v-divider class="mt-12"></v-divider>

          <v-card-actions>
            <v-btn
                text
                @click="$router.back()"
            >
              Cancel
            </v-btn>
            <v-spacer></v-spacer>

            <v-btn
                color="primary"
                text
                @click="signup_summit({userName, password, nickName})"
            >
              Submit
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
// import router from "@/router";

export default {
  name: "SignUp",
  data () {
    return {
      userName: null,
      password: null,
      nickName: null,
      rules: [v => v.length <= 25 || 'Max 25 characters'],
      // wordsRules: [v => v.trim().split(' ').length <= 5 || 'Max 25 words'],
    }
  },
  methods: {
    signup_summit(signupObj) {
      console.log(signupObj)
      axios
          .post("/api/signup",signupObj)
          .then(res => {
            this.$router.push({ name: "Login"})
            console.log(res.data)
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

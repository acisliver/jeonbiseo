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
                v-model="user_name"
                :rules="[() => !!user_name || '필수입력입니다.']"
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
                v-model="nick_name"
                :rules="[
                () => !!nick_name || '필수입력입니다.']"
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
                @click="signup_summit({user_name, password, nick_name})"
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

export default {


  name: "SignUp",
  data () {
    return {
      user_name: null,
      password: null,
      nick_name: null,
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
